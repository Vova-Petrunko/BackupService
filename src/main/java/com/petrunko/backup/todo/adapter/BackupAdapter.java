package com.petrunko.backup.todo.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.petrunko.backup.todo.model.Backup;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class BackupAdapter implements JsonSerializer<Backup> {
    @Override
    public JsonElement serialize(Backup backup, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        jsonObject.addProperty("backupId", backup.getId());
        jsonObject.addProperty("date", dateFormat.format(backup.getDate()));
        jsonObject.addProperty("status", backup.getStatus().getDescription());

        return jsonObject;
    }
}
