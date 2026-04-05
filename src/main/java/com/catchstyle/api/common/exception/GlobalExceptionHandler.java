
package com.catchstyle.api.common.exception;

import com.catchstyle.api.common.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //컨트롤러든 서비스 어디에서든 에러 터지면 이 클래스가 낚아채서 프엔에 응답 보냄
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyRegisteredException(AlreadyRegisteredException e) {
        //프엔으로 HTTP 상태 코드 409(Conflict)와 함께 에러 메시지를 JSON으로 묶어서 반환함
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(e.getMessage()));
    }
}
