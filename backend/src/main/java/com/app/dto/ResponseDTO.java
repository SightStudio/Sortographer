package com.app.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * REST API에서 응답을 보내주는 공통 모듈
 * @author Dong-Min Seol
 * @since  2019-11-04
 */
@Getter
public class ResponseDTO {

    private String     message;
    private HttpStatus httpStatus;
    private Map<String, Object> data;

    public ResponseDTO(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ResponseDTO(String message, HttpStatus httpStatus, boolean hasData) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.data =  hasData ? new HashMap<>() : null;
    }

    public void addData(String name, Object data) {
        this.data.put(name, data);
    }
}
