package com.chatgpt.openaiclient.app.repository;

import com.chatgpt.openaiclient.OpenAiClientApplication;
import com.chatgpt.openaiclient.app.pojo.DTO.ResponseDTO;
import com.chatgpt.openaiclient.app.pojo.DTO.node.Choices;
import com.chatgpt.openaiclient.app.pojo.VO.RequestVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class SendRepository {

    public static String getMessage() {
        StringBuilder str = new StringBuilder();
        JsonMapper jsonMapper = new JsonMapper();

        RestTemplate restTemplate = new RestTemplate();

        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + OpenAiClientApplication.KEY);

        // 创建请求体
        String requestBody = "";
        try {
            requestBody = jsonMapper.writeValueAsString(RequestVO.getBody());
        } catch (JsonProcessingException e) {
            log.debug("获取请求体错误: {}", e.getMessage());
        }

        // 创建HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 目标URL
        String url = "https://api.openai.com/v1/chat/completions";

        // 创建RequestCallback以发送请求
        RequestCallback requestCallback = restTemplate.httpEntityCallback(entity, String.class);

        // 创建ResponseExtractor来处理SSE响应
        ResponseExtractor<Void> responseExtractor = clientHttpResponse -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("data: ")) {
                        // 去掉 "data: " 前缀，处理事件数据
                        String eventData = line.substring(6).trim();
                        // 处理逻辑
                        if (!eventData.equals("[DONE]")) {
                            ResponseDTO dto = jsonMapper.readValue(eventData, ResponseDTO.class);
                            List<Choices> choices = dto.getChoices();
                            if (!choices.isEmpty()) {
                                if (!"stop".equals(choices.get(0).getFinishReason())) {
                                    String data = choices.get(0).getDelta().getContent();
                                    str.append(data);
                                }

                            }
                        }
                    }
                }
            }
            return null;
        };

        // 执行请求
        restTemplate.execute(url, HttpMethod.POST, requestCallback, responseExtractor);
        return str.toString();
    }

}
