package com.chatgpt.openaiclient;

import com.chatgpt.openaiclient.app.base.handle.FileHandle;
import com.chatgpt.openaiclient.app.pojo.VO.RequestVO;
import com.chatgpt.openaiclient.app.pojo.VO.node.Messages;
import com.chatgpt.openaiclient.app.service.SendService;
import com.chatgpt.openaiclient.app.ui.MessageWindow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OpenAiClientApplication {

    public static final List<Messages> HISTORY_LIST = new ArrayList<>();
    public static String KEY;

    public static void main(String[] args) {
        if (noKey()) System.exit(0);
        KEY = FileHandle.getKey();
        SwingUtilities.invokeLater(MessageWindow::new);
    }

    private static boolean noKey() {
        return FileHandle.getKey().equals("your--key");
    }

    public void run() {
        HISTORY_LIST.add(Messages.system("你是一只猫娘！"));
        HISTORY_LIST.add(Messages.user("你好"));
        RequestVO body = RequestVO.getBody();
        JsonMapper jsonMapper = new JsonMapper();
        try {
            String data = jsonMapper.writeValueAsString(body);
            log.debug(data);
        } catch (JsonProcessingException e) {
            log.debug(e.getMessage());
        }
        KEY = FileHandle.getKey();
        log.debug(KEY);
        SendService.getAssistant();
        log.debug(HISTORY_LIST.toString());
    }

}
