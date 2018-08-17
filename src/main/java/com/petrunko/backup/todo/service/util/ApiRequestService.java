package com.petrunko.backup.todo.service.util;

public interface ApiRequestService {
    String sendGetRequest(String url);

    String sendPostRequest(String url, Object body);
}
