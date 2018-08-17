package com.petrunko.backup.todo.service.util.exporter;

import com.petrunko.backup.todo.model.Backup;
import com.petrunko.backup.todo.model.ToDo;
import com.petrunko.backup.todo.model.User;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CSVExporter implements BackupExporter {
    private static final String CSV_SEPARATOR = ";";

    @Override
    public String convert(Backup backup) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer head = new StringBuffer(createCSVHeader());
        StringBuffer body = new StringBuffer();

        List<User> users = backup.getUsers();
        for (User user : users) {
            List<ToDo> toDos = user.getTodos();
            for (ToDo toDo : toDos) {
                body.append(user.getUsername() == null ? "" : user.getUsername());
                body.append(CSV_SEPARATOR);
                body.append(toDo.getId() == null ? "" : toDo.getId());
                body.append(CSV_SEPARATOR);
                body.append(toDo.getSubject() == null ? "" : toDo.getSubject());
                body.append(CSV_SEPARATOR);
                body.append(toDo.getDueDate() == null ? "" : dateFormat.format(toDo.getDueDate()));
                body.append(CSV_SEPARATOR);
                body.append(toDo.isDone());
                body.append(" ");
            }
        }

        return head.append(body).toString();
    }

    private String createCSVHeader() {
        return "Username" + CSV_SEPARATOR +
                "TodoItemId" + CSV_SEPARATOR +
                "Subject" + CSV_SEPARATOR +
                "DueDate" + CSV_SEPARATOR +
                "Done ";
    }
}
