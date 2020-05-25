package com.ppf.springboot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GlobalException extends RuntimeException {

    private int code;
    private String message;


    public GlobalException(int code) {
        super();
        this.code = code;
    }

    public GlobalException(String message) {
        super();
        this.message = message;
    }

    public GlobalException(int code,String message) {
        super();
        this.code = code;
        this.message = message;
    }


}
