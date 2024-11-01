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
public class StreamOptions {

    @JsonProperty("include_usage")
    Boolean includeUsage;

    public static StreamOptions def() {
        return new StreamOptions(true);
    }

}
