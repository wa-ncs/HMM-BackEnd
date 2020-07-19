package com.hmm.hmm.interfaces;

import com.hmm.hmm.application.BoardQnaService;
import com.hmm.hmm.domain.BoardQna;
import com.hmm.hmm.interfaces.dto.BoardCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/v1/qna-boards")
@RestController
public class BoardQnaController {

    private final BoardQnaService qnaBoardService;

    @PostMapping("/create")
    public BoardQna create(@Valid @RequestBody BoardCreateRequest createRequest) {
        return qnaBoardService.create(createRequest);
    }
}
