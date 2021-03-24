package br.com.api.curso.validations;

import java.util.Objects;

public interface Validate<T> {

    void valid(T t);

    default void validNull(T t){
        if(Objects.isNull(t)){
            throw new IllegalArgumentException("Course is null");
        }
    }
}
