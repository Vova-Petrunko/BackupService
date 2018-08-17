package com.petrunko.backup.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document
public class ToDo {
    @Id
    private String id;

    private String subject;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueDate;
    private boolean done;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo that = (ToDo) o;
        return done == that.done &&
                Objects.equals(id, that.id) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(dueDate, that.dueDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, subject, dueDate, done);
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", dueDate=" + dueDate +
                ", done=" + done +
                '}';
    }
}
