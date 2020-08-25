package com.hmm.hmm.interfaces.dto;

import com.hmm.hmm.domain.BoardQna;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
public class ReplyCreateRequest {

    @NotNull(message = "The content must not be Null")
    private String content;

    @NotNull(message = "The userId must not be Null")
    private Long userId;

    public Reply toEntity() {
        return Reply.builder()
                .content(content)
                .userId(userId)
                .build();
    }
}
