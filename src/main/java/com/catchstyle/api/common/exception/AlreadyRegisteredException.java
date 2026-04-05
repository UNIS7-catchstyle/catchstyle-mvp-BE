package com.catchstyle.api.common.exception;

//RuntimeException을 상속받아 실행 중에 발생시킬 수 있는 예외로 만듦
public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
