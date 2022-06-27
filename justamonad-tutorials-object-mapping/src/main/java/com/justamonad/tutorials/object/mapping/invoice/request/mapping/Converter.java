package com.justamonad.tutorials.object.mapping.invoice.request.mapping;

import java.util.function.Function;

public final class Converter<T, U> {

    private final Function<T, U> fromDto;
    private final Function<U, T> fromEntity;

    public Converter(Function<T, U> fromDto, Function<U, T> fromEntity) {
        this.fromDto = fromDto;
        this.fromEntity = fromEntity;
    }

    public U convertFromDto(T dto) {
        return fromDto.apply(dto);
    }

    public T convertFromEntity(U entity) {
        return fromEntity.apply(entity);
    }

}
