package com.hmm.hmm.interfaces.dto;

import com.hmm.hmm.domain.BoardQna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BoardQnaDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public static BoardQnaDto of(BoardQna boardQna) {
        return BoardQnaDto.builder()
                .id(boardQna.getId())
                .title(boardQna.getTitle())
                .content(boardQna.getContent())
                .build();
    }

    public BoardQnaDto(BoardQna boardQna) {
        this.id = boardQna.getId();
        this.title = boardQna.getTitle();
        this.content = boardQna.getContent();
    }
}
