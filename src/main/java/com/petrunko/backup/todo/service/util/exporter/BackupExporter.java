package com.petrunko.backup.todo.service.util.exporter;

import com.petrunko.backup.todo.model.Backup;

public interface BackupExporter {
    String convert(Backup backup);
}
