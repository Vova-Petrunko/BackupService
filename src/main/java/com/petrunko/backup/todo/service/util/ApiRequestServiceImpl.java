package com.petrunko.backup.todo.service.util;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiRequestServiceImpl implements ApiRequestService {
    private RestTemplate template = new RestTemplate();

    @Override
    public String sendGetRequest(String url) {
        String result = null;
        ResponseEntity<String> entity = null;
        entity = template.exchange(url, HttpMethod.GET, createHeader(), String.class);
        result = entity.getBody();
        return result;
    }

    @Override
    public String sendPostRequest(String url, Object body) {
        String result = null;
        ResponseEntity<String> entity = null;
        HttpEntity httpEntity = new HttpEntity(body.toString(), createHeader().getHeaders());
        entity = template.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return entity.getBody();
    }

    private HttpEntity createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity(headers);
    }
}
