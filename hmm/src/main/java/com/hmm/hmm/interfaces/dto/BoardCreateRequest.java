package com.hmm.hmm.interfaces.dto;

import com.hmm.hmm.domain.BoardQna;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BoardCreateRequest {

    @NotNull(message = "The title must not be Null")
    private String title;
    @NotNull(message = "The content must not be Null")
    private String content;
    @NotNull(message = "The userId must not be Null")
    private Long userId;

    public BoardQna toEntity() {
        return BoardQna.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
