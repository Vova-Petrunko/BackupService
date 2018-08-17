package com.petrunko.backup.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document
public class Backup {
    @Id
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private BackupStatus status;

    @DBRef
    private List<User> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BackupStatus getStatus() {
        return status;
    }

    public void setStatus(BackupStatus status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Backup that = (Backup) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                status == that.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, status);
    }

    @Override
    public String toString() {
        return "Backup{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
