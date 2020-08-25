package com.hmm.hmm.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public BoardQna(String content, Long userId) {
        this.content = content;
        this.userId = userId;
    }
}
