package com.elemroot.wordcloudcore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "textfiles")
public class TextFile {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String filename;

    @Column(name = "file_size", nullable = false)
    private long fileSize;

    @Column(name = "upload_time", nullable = false)
    private LocalDateTime uploadTime;

    public enum Status {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    @PrePersist
    protected void onPrePersist() {
        // Määratakse praegune aeg enne salvestamist
        this.uploadTime = LocalDateTime.now();

        // Genereeritakse UUID enne salvestamist
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}