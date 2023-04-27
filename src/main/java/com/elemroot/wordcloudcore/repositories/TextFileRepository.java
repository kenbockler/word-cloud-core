package com.elemroot.wordcloudcore.repositories;

import com.elemroot.wordcloudcore.models.TextFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TextFileRepository extends JpaRepository<TextFile, UUID> {
}
