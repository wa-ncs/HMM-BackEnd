package com.hmm.hmm.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class BoardQna extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Long userId;

    @Builder
    public BoardQna(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
