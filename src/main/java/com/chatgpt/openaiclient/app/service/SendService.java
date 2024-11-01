package com.chatgpt.openaiclient.app.service;

import com.chatgpt.openaiclient.OpenAiClientApplication;
import com.chatgpt.openaiclient.app.pojo.VO.node.Messages;
import com.chatgpt.openaiclient.app.repository.SendRepository;

public class SendService {

    public static void getAssistant() {
        String data = SendRepository.getMessage();
        Messages assistant = Messages.assistant(data);
        OpenAiClientApplication.HISTORY_LIST.add(assistant);
    }

}
