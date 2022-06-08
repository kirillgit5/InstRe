package com.kramar.Inst.network.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private String code;

    public ErrorResponse(String code) {
        this.code = code;
    }
}
