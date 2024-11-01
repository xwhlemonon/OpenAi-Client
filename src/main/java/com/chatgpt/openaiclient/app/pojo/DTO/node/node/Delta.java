package com.chatgpt.openaiclient.app.pojo.DTO.node.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Delta {

    @JsonProperty("content")
    String content;

}
