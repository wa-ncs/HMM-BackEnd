package com.hmm.hmm.application;

import com.hmm.hmm.application.component.S3Uploader;
import com.hmm.hmm.domain.BoardQna;
import com.hmm.hmm.domain.BoardQnaRepository;
import com.hmm.hmm.interfaces.dto.BoardCreateRequest;
import com.hmm.hmm.interfaces.dto.BoardQnaDto;
import com.hmm.hmm.interfaces.dto.BoardUpdateRequest;
import com.hmm.hmm.interfaces.dto.PageDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardQnaService {

    private final BoardQnaRepository boardQnaRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public BoardQna create(@NonNull final BoardCreateRequest request) {
        return boardQnaRepository.save(request.toEntity());
    }

    @Transactional
    public BoardQnaDto createWithFile(@NonNull final BoardCreateRequest request,
                                      @NonNull final MultipartFile[] files) {
        BoardQna boardQna = boardQnaRepository.save(request.toEntity());

        List<String> urlList = Collections.emptyList();
        try {
            urlList = s3Uploader.upload(files, boardQna.getId());
        } catch (IOException e) {
            log.error("S3 upload failed...", e);
        }

        return BoardQnaDto.of(boardQna, urlList);
    }

    @Transactional(readOnly = true)
    public BoardQnaDto find(@NonNull final Long id) {
        BoardQna boardQna = boardQnaRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        return BoardQnaDto.of(boardQna);
    }

    @Transactional(readOnly = true)
    public PageDto<BoardQnaDto> findBoards(Pageable pageable) {
        Page<BoardQna> boardQnaList = boardQnaRepository.findAll(pageable);

        return PageDto.of(boardQnaList, BoardQnaDto::new);
    }

    @Transactional
    public BoardQna update(@NonNull final Long id,
                           @NonNull final BoardUpdateRequest request) {
        BoardQna boardQna = boardQnaRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        BoardQna updateBoard = request.toEntity();
        updateBoard.setId(id);
        return boardQnaRepository.save(updateBoard);
    }

    @Transactional
    public void delete(@NonNull final Long id) {
        BoardQna boardQna = boardQnaRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        boardQnaRepository.delete(boardQna);
    }
}
