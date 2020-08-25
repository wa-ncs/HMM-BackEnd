package com.hmm.hmm.interfaces.dto;

import com.hmm.hmm.domain.BoardQna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReplyDto {

    private Long commentId;
    private String title;
    private String content;

    public static ReplyDto of(Reply reply) {
        return ReplyDto.builder()
                .commentId(reply.getCommentId())
                .content(boardQna.getContent())
                .build();
    }

    public ReplyDto(Reply reply) {
        this.commentId = reply.getCommentId();
        this.content = reply.getContent();
    }
}
