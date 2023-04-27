package com.elemroot.wordcloudcore.dtos;

import com.elemroot.wordcloudcore.models.TextFile.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TextFileDTO {

    private UUID id;
    private String filename;
    private long fileSize;
    private LocalDateTime uploadTime;
    private Status status;
}
