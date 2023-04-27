package com.elemroot.wordcloudcore.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TextFileMessage implements Serializable {
    private UUID id;
    private String content;
    private int chunkNumber;
    private int totalChunks;
}