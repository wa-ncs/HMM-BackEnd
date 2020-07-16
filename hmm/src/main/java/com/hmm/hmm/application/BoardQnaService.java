package com.hmm.hmm.application;

import com.hmm.hmm.domain.BoardQna;
import com.hmm.hmm.domain.BoardQnaRepository;
import com.hmm.hmm.interfaces.dto.BoardCreateRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardQnaService {

    private final BoardQnaRepository boardQnaRepository;

    public BoardQna create(@NonNull final BoardCreateRequest request) {
        return boardQnaRepository.save(request.toEntity());
    }
}
