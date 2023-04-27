package com.elemroot.wordcloudcore.controllers;

import com.elemroot.wordcloudcore.dtos.TextFileDTO;
import com.elemroot.wordcloudcore.dtos.WordCountDTO;
import com.elemroot.wordcloudcore.models.TextFile;
import com.elemroot.wordcloudcore.mappers.TextFileMapper;
import com.elemroot.wordcloudcore.services.TextFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/textfiles")
public class TextFileController {

    private final TextFileService textFileService;

    public TextFileController(TextFileService textFileService) {
        this.textFileService = textFileService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TextFileDTO> createTextFile(@RequestParam("file") MultipartFile file) {
        TextFile createdTextFile = textFileService.createTextFile(file);
        TextFileDTO textFileDTO = TextFileMapper.toDTO(createdTextFile);
        return ResponseEntity.ok(textFileDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TextFileDTO> getTextFileById(@PathVariable UUID id) {
        Optional<TextFile> textFile = textFileService.getTextFileById(id);
        return textFile.map(TextFileMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/wordcounts")
    public ResponseEntity<WordCountDTO> getWordCountsById(@PathVariable UUID id) {
        Optional<Map<String, Integer>> wordCounts = textFileService.getWordCountsById(id);
        return wordCounts.map(wordCountsMap -> new WordCountDTO(id, wordCountsMap))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
