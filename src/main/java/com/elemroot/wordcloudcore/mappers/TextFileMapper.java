package com.elemroot.wordcloudcore.mappers;

import com.elemroot.wordcloudcore.dtos.TextFileDTO;
import com.elemroot.wordcloudcore.models.TextFile;
import org.springframework.stereotype.Component;

@Component
public class TextFileMapper {

    public static TextFileDTO toDTO(TextFile textFile) {
        TextFileDTO textFileDTO = new TextFileDTO();
        textFileDTO.setId(textFile.getId());
        textFileDTO.setFilename(textFile.getFilename());
        textFileDTO.setFileSize(textFile.getFileSize());
        textFileDTO.setUploadTime(textFile.getUploadTime());
        textFileDTO.setStatus(textFile.getStatus());
        return textFileDTO;
    }

    public static TextFile toEntity(TextFileDTO textFileDTO) {
        TextFile textFile = new TextFile();
        textFile.setId(textFileDTO.getId());
        textFile.setFilename(textFileDTO.getFilename());
        textFile.setFileSize(textFileDTO.getFileSize());
        textFile.setUploadTime(textFileDTO.getUploadTime());
        textFile.setStatus(textFileDTO.getStatus());
        return textFile;
    }
}
