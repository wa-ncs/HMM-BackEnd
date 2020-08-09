package com.hmm.hmm.interfaces.dto;

import com.hmm.hmm.domain.BoardQna;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BoardUpdateRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    public BoardQna toEntity() {
        return BoardQna.builder()
                .title(title)
                .content(content)
                .build();
    }
}
