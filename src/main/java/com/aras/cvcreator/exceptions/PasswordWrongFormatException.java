package com.aras.cvcreator.exceptions;

public class PasswordWrongFormatException extends Exception {
    public PasswordWrongFormatException() {
        super("Password should be at least 8 character " +
                "and contains digits, one lowercase letter, one uppercase letter and one underscore.");
    }
}
