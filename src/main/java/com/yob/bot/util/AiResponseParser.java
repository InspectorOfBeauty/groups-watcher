package com.yob.bot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AiResponseParser {
    public static final int BEGIN_INDEX = "data: ".length();

    public static String parse(String response) {
        StringBuilder parsedResponse = new StringBuilder();
        String[] responseData = response.split("\n\n");

        for (int i = 0; i < responseData.length - 1; i++) {
            String json = responseData[i].substring(BEGIN_INDEX);
            JsonNode tree = convertToTree(json);
            JsonNode content = getContentFrom(tree);

            if (content != null) {
                parsedResponse.append(content.asText());
            }
        }

        return parsedResponse.toString();
    }

    private static JsonNode convertToTree(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static JsonNode getContentFrom(JsonNode tree) {
        ArrayNode arrayNode = (ArrayNode) tree.get("choices");
        return arrayNode.get(0).get("delta").get("content");
    }
}
