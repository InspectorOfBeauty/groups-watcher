package com.yob.bot.getting;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wall_post")
@Data
@NoArgsConstructor
public class WallPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "group_url")
    private String groupUrl;

    @Column(name = "text")
    private String text;

    public WallPost(String groupUrl, String text) {
        this.groupUrl = groupUrl;
        this.text = text;
    }
}
