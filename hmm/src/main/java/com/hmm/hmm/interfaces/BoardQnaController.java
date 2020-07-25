package com.hmm.hmm.interfaces;

import com.hmm.hmm.application.BoardQnaService;
import com.hmm.hmm.interfaces.dto.BoardCreateRequest;
import com.hmm.hmm.interfaces.dto.BoardQnaDto;
import com.hmm.hmm.interfaces.dto.BoardUpdateRequest;
import com.hmm.hmm.interfaces.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/v1/qna-boards")
@RestController
public class BoardQnaController {

    private final BoardQnaService qnaBoardService;

    @PostMapping("/create")
    public BoardQnaDto create(@Valid @RequestBody BoardCreateRequest createRequest) {
        return BoardQnaDto.of(qnaBoardService.create(createRequest));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardQnaDto> find(@PathVariable("boardId") Long boardId) {
        return ResponseEntity.ok(qnaBoardService.find(boardId));
    }

    @GetMapping("/boards")
    public PageDto<BoardQnaDto> findBoards(@RequestParam(defaultValue = "0") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size) {
        return qnaBoardService.findBoards(PageRequest.of(page,size));
    }

    @PatchMapping("/{boardId}")
    public BoardQnaDto update(@PathVariable("boardId") Long boardId,
                              @Valid @RequestBody BoardUpdateRequest updateRequest) {
        return BoardQnaDto.of(qnaBoardService.update(boardId, updateRequest));
    }

    @DeleteMapping("/{boardId}")
    public void delete(@PathVariable("boardId") Long boardId) {
        qnaBoardService.delete(boardId);
    }
}
