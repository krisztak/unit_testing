package com.verval.exception;

public class UserChangeException extends RuntimeException{

        public UserChangeException() {
            super("User change not possible");
        }
}
