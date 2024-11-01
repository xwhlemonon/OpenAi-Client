package com.chatgpt.openaiclient.app.pojo.VO;

import com.chatgpt.openaiclient.OpenAiClientApplication;
import com.chatgpt.openaiclient.app.pojo.VO.node.Messages;
import com.chatgpt.openaiclient.app.pojo.VO.node.ResponseFormat;
import com.chatgpt.openaiclient.app.pojo.VO.node.StreamOptions;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RequestVO {

    @JsonProperty("messages")
    List<Messages> messages;
    @JsonProperty("temperature")
    Integer temperature;
    @JsonProperty("max_tokens")
    Integer maxTokens;
    @JsonProperty("top_p")
    Integer topP;
    @JsonProperty("frequency_penalty")
    Integer frequencyPenalty;
    @JsonProperty("presence_penalty")
    Integer presencePenalty;
    @JsonProperty("seed")
    Integer seed;
    @JsonProperty("model")
    String model;
    @JsonProperty("modalities")
    List<String> modalities;
    @JsonProperty("response_format")
    ResponseFormat responseFormat;
    @JsonProperty("stream")
    Boolean stream;
    @JsonProperty("stream_options")
    StreamOptions streamOptions;

    public static RequestVO getBody() {
        List<Messages> messagesList = new ArrayList<>(OpenAiClientApplication.HISTORY_LIST);
        List<String> modalitiesList = new ArrayList<>();
        modalitiesList.add("text");
        RequestVO requestVO = new RequestVO();
        requestVO.setMessages(messagesList);
        requestVO.setTemperature(1);
        requestVO.setMaxTokens(4095);
        requestVO.setTopP(1);
        requestVO.setFrequencyPenalty(0);
        requestVO.setPresencePenalty(0);
        requestVO.setSeed(null);
        requestVO.setModel("gpt-4o");
        requestVO.setModalities(modalitiesList);
        requestVO.setResponseFormat(ResponseFormat.def());
        requestVO.setStream(true);
        requestVO.setStreamOptions(StreamOptions.def());
        return requestVO;
    }

}
