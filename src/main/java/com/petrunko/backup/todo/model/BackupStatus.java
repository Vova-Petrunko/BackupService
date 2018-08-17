package com.petrunko.backup.todo.model;

public enum BackupStatus {
    IN_PROGRESS(0, "in progress"),
    SUCCESS(1, "success"),
    FAILED(2, "failed");

    private Integer statusCode;
    private String description;

    BackupStatus(Integer statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }
}
