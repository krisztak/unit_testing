package com.example.junit.exception;

public class WrongTeamException extends RuntimeException {

    public WrongTeamException() {
        super("User does not belong to this team");
    }
}
