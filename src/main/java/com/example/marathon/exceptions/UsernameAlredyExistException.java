package com.example.marathon.exceptions;

public class UsernameAlredyExistException extends RuntimeException{

    public UsernameAlredyExistException() {
        super("Username already exist");
    }
}
