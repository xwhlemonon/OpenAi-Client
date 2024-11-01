package com.chatgpt.openaiclient.app.pojo.VO.node;

import com.chatgpt.openaiclient.app.pojo.VO.node.node.Content;
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
public class Messages {

    @JsonProperty("role")
    String role;
    @JsonProperty("content")
    List<Content> content;

    public static Messages system(String text) {
        List<Content> contents = new ArrayList<>();
        contents.add(Content.def(text));
        return new Messages("system", contents);
    }

    public static Messages user(String text) {
        List<Content> contents = new ArrayList<>();
        contents.add(Content.def(text));
        return new Messages("user", contents);
    }

    public static Messages assistant(String text) {
        List<Content> contents = new ArrayList<>();
        contents.add(Content.def(text));
        return new Messages("assistant", contents);
    }

}
