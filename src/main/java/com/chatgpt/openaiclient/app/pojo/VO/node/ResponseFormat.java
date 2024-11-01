package com.chatgpt.openaiclient.app.pojo.VO.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFormat {

    @JsonProperty("type")
    String type;

    public static ResponseFormat def() {
        return new ResponseFormat("text");
    }

}
