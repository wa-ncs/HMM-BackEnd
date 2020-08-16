package com.hmm.hmm.interfaces;

import com.hmm.hmm.application.BoardQnaService;
import com.hmm.hmm.domain.BoardQna;
import com.hmm.hmm.interfaces.dto.BoardCreateRequest;
import com.hmm.hmm.interfaces.dto.BoardQnaDto;
import com.hmm.hmm.interfaces.dto.BoardUpdateRequest;
import com.hmm.hmm.interfaces.dto.PageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/qna-boards")
@RestController
public class BoardQnaController {

    private final BoardQnaService qnaBoardService;

    @PostMapping("/create")
    public BoardQnaDto create(@Valid @RequestBody BoardCreateRequest createRequest) {
        return BoardQnaDto.of(qnaBoardService.create(createRequest));
    }

    @PostMapping("/create/file")
    public BoardQnaDto createWithFile(@RequestParam("title") String title,
                                  @RequestParam("content") String content,
                                  @RequestParam("userId") Long userId,
                                  @RequestParam(required = false, value = "files") MultipartFile[] files) {

        BoardCreateRequest createRequest = BoardCreateRequest.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();

        return qnaBoardService.createWithFile(createRequest, files);
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
