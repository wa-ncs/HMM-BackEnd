package com.hmm.hmm.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Builder
public class PageDto<T> {

    private Long total;
    private Integer size;
    private Boolean hasNext;
    private List<T> data;

    public static <E, D> PageDto<D> of(Page<E> page, Function<E, D> converter) {
        return PageDto.<D>builder()
                .total(page.getTotalElements())
                .size(page.getSize())
                .hasNext(page.hasNext())
                .data(page.getContent().stream()
                        .map(converter)
                        .collect(Collectors.toList()))
                .build();
    }
}
