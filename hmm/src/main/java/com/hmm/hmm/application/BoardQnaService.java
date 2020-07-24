package com.hmm.hmm.application;

import com.hmm.hmm.domain.BoardQna;
import com.hmm.hmm.domain.BoardQnaRepository;
import com.hmm.hmm.interfaces.dto.BoardCreateRequest;
import com.hmm.hmm.interfaces.dto.BoardQnaDto;
import com.hmm.hmm.interfaces.dto.PageDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BoardQnaService {

    private final BoardQnaRepository boardQnaRepository;

    @Transactional
    public BoardQna create(@NonNull final BoardCreateRequest request) {
        return boardQnaRepository.save(request.toEntity());
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
    public void delete(@NonNull final Long id) {
        BoardQna boardQna = boardQnaRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        boardQnaRepository.delete(boardQna);
    }
}
