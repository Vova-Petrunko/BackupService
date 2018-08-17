package com.petrunko.backup.todo.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petrunko.backup.todo.adapter.BackupAdapter;
import com.petrunko.backup.todo.dao.BackupRepository;
import com.petrunko.backup.todo.model.Backup;
import com.petrunko.backup.todo.model.BackupStatus;
import com.petrunko.backup.todo.model.User;
import com.petrunko.backup.todo.service.util.exporter.BackupExporter;
import com.petrunko.backup.todo.task.BackupWriteTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BackupServiceImpl implements BackupService {
    @Autowired
    private UserService userService;

    @Autowired
    private BackupRepository backupRepository;

    @Autowired
    private BackupExporter backupExporter;

    @Value(value = "${backup.path}")
    private String backupPath;

    @Override
    public Backup initiateBackup() {
        List<User> users = userService.fetchAllFromServer();
        userService.saveUsersAndToDos(users);

        Backup backup = new Backup();
        backup.setDate(new Date());
        backup.setUsers(users);
        backup.setStatus(BackupStatus.IN_PROGRESS);
        backupRepository.save(backup);

        backup = writeBackup(new Backup[]{backup});

        return backup;
    }

    private Backup writeBackup(Backup[] backup){
        CompletableFuture<Backup> future = CompletableFuture.supplyAsync(() -> {
            BackupWriteTask task = new BackupWriteTask(backup[0], backupPath);
            backup[0] = task.call();

            return backup[0];
        });

        future.thenAccept(this::update);

        return backup[0];
    }

    @Override
    public Optional<Backup> findById(String id) {
        return backupRepository.findById(id);
    }

    @Override
    public List<Backup> findAll() {
        return backupRepository.findAll();
    }

    @Override
    public void save(Backup backup) {
        backupRepository.save(backup);
    }

    @Override
    public void update(Backup backup) {
        backupRepository.save(backup);
    }

    @Override
    public String convertToJson(List<Backup> backups) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Backup.class, new BackupAdapter()).create();
        return gson.toJson(backups);
    }

    @Override
    public String convertToCSV(Backup backup) {
        return backupExporter.convert(backup);
    }
}
