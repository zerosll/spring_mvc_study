package com.cafe24.springmvcstudy.common.exception;

public class WrongIdPasswordException extends RuntimeException{
    public WrongIdPasswordException(){
        super();
    }
    public WrongIdPasswordException(String message){
        super(message);
    }

}
