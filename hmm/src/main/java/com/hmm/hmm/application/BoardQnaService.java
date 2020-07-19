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

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BoardQnaService {

    private final BoardQnaRepository boardQnaRepository;

    public BoardQna create(@NonNull final BoardCreateRequest request) {
        return boardQnaRepository.save(request.toEntity());
    }

    public BoardQnaDto find(@NonNull final Long id) {
        BoardQna boardQna = boardQnaRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        return BoardQnaDto.of(boardQna);
    }

    public PageDto<BoardQnaDto> findBoards(Pageable pageable) {
        Page<BoardQna> boardQnaList = boardQnaRepository.findAll(pageable);

        return PageDto.of(boardQnaList, BoardQnaDto::new);
    }
}
