package com.petrunko.backup.todo.task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petrunko.backup.todo.model.Backup;
import com.petrunko.backup.todo.model.BackupStatus;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class BackupWriteTask implements Callable<Backup> {
    private Backup backup;
    private String backupPath;

    public BackupWriteTask(Backup backup, String backupPath) {
        this.backup = backup;
        this.backupPath = backupPath;
    }

    @Override
    public Backup call() {
        String backupFileName = getBackupFileName(backup);

        try {
            File file = new File(backupPath + backupFileName);
            file.getParentFile().mkdir();
            file.createNewFile();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

            String json = convertToJson(backup);

            bufferedWriter.write(json);
            bufferedWriter.close();

        } catch (IOException e) {
            backup.setStatus(BackupStatus.FAILED);
            return backup;
        }

        backup.setStatus(BackupStatus.SUCCESS);
        return backup;
    }

    private String getBackupFileName(Backup backup) {
        Date date = backup.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return dateFormat.format(date) + "_todo.back";
    }

    private String convertToJson(Backup backup) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(backup);
    }
}
