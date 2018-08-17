package com.petrunko.backup.todo.controller;

import com.petrunko.backup.todo.model.Backup;
import com.petrunko.backup.todo.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BackupController {

    @Autowired
    private BackupService backupService;

    @GetMapping(value = "backups")
    public @ResponseBody
    ResponseEntity getAllBackups() {
        List<Backup> backups = backupService.findAll();
        String json = backupService.convertToJson(backups);
        return ResponseEntity.ok(json);
    }

    @PostMapping(value = "backups")
    public @ResponseBody
    ResponseEntity backupsAccounts() {
        Backup backup = backupService.initiateBackup();
        return ResponseEntity.ok("{backupId:" + backup.getId() + "}");
    }

    @GetMapping(value = "exports/{backupId}")
    public @ResponseBody
    ResponseEntity exportBackup(@PathVariable("backupId") String backupId) {
        Optional<Backup> backup = backupService.findById(backupId);
        return ResponseEntity.ok(backupService.convertToCSV(backup.orElse(null)));
    }
}
