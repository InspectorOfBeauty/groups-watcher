package com.yob.bot.getting;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RephrasedWallPost {
    private String groupUrl;
    private String originalText;
    private String rephrasedText;
}
