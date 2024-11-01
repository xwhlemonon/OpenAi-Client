package com.chatgpt.openaiclient.app.pojo.DTO;

import com.chatgpt.openaiclient.app.pojo.DTO.node.Choices;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDTO {

    @JsonProperty("choices")
    List<Choices> choices;

}
