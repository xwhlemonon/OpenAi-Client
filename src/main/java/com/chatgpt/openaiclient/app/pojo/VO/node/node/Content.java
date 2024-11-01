package com.chatgpt.openaiclient.app.pojo.VO.node.node;

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
public class Content {

    @JsonProperty("type")
    String type;
    @JsonProperty("text")
    String text;

    public static Content def(String text) {
        return new Content("text", text);
    }

}
