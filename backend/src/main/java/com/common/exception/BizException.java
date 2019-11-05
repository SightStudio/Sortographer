package com.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 공통 비즈니스 로직 Exception Class
 * @author  Dong-Min Seol
 * @since	2019.02.03
 *
 */
@Getter
@RequiredArgsConstructor
public class BizException extends RuntimeException {

    // defatul serial ID
    @Getter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    // 최종 error message
    final protected String errMsg;

    // 최종 error code
    final protected HttpStatus httpStatus;
}
