package com.petrunko.backup.todo.service;

import com.petrunko.backup.todo.model.Backup;

import java.util.List;
import java.util.Optional;

public interface BackupService {
    Optional<Backup> findById(String id);

    List<Backup> findAll();

    void save(Backup backup);

    void update(Backup backup);

    Backup initiateBackup();

    String convertToJson(List<Backup> backups);

    String convertToCSV(Backup backup);
}
