package com.elemroot.wordcloudcore.services;

import com.elemroot.wordcloudcore.models.TextFile;
import com.elemroot.wordcloudcore.models.Word;
import com.elemroot.wordcloudcore.rabbitmq.RabbitMQSender;
import com.elemroot.wordcloudcore.repositories.TextFileRepository;
import com.elemroot.wordcloudcore.repositories.WordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TextFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextFileService.class);

    private final TextFileRepository textFileRepository;
    private final RabbitMQSender rabbitMQSender;
    private final WordRepository wordRepository;

    public TextFileService(TextFileRepository textFileRepository, RabbitMQSender rabbitMQSender, WordRepository wordRepository) {
        this.textFileRepository = textFileRepository;
        this.rabbitMQSender = rabbitMQSender;
        this.wordRepository = wordRepository;
    }

    public TextFile createTextFile(MultipartFile file) {
        TextFile textFile = new TextFile();
        textFile.setId(UUID.randomUUID());
        textFile.setFilename(file.getOriginalFilename());
        textFile.setFileSize(file.getSize());
        textFile.setStatus(TextFile.Status.PENDING);

        try {
            byte[] fileContent = file.getBytes();
            rabbitMQSender.sendTextFileChunks(textFile.getId(), fileContent);
        } catch (IOException e) {
            LOGGER.error("Error reading file content: {}", e.getMessage(), e);
            textFile.setStatus(TextFile.Status.FAILED);
        }
        return textFileRepository.save(textFile);
    }

    public Optional<Map<String, Integer>> getWordCountsById(UUID id) {
        Optional<TextFile> optionalTextFile = textFileRepository.findById(id);

        if (optionalTextFile.isPresent() && optionalTextFile.get().getStatus() == TextFile.Status.COMPLETED) {
            List<Word> words = wordRepository.findByTextFileId(id);
            Map<String, Integer> wordCounts = words.stream().collect(Collectors.toMap(Word::getWord, Word::getCount));

            // Sort the wordCounts
            Map<String, Integer> sortedWordCounts = wordCounts.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new));

            return Optional.of(sortedWordCounts);
        } else {
            return Optional.empty();
        }
    }

    // Add this new method
    public Optional<TextFile> getTextFileById(UUID id) {
        return textFileRepository.findById(id);
    }
}
