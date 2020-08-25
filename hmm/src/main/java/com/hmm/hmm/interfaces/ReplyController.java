package com.hmm.hmm.interfaces;

import com.hmm.hmm.application.BoardQnaService;
import com.hmm.hmm.interfaces.dto.BoardCreateRequest;
import com.hmm.hmm.interfaces.dto.BoardQnaDto;
import com.hmm.hmm.interfaces.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/reply")
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/create")
    public ReplyDto create(@Valid @RequestBody ReplyCreateRequest createRequest) {
        return ReplyDto.of(replyService.create(createRequest));
    }

}
