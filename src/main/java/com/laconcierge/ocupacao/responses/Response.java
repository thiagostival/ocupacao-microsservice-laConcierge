package com.laconcierge.ocupacao.responses;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private T data;
    private List<String> errors;

    public Response() {
        this.errors = new ArrayList<>();
    }
}
