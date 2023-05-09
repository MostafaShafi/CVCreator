package com.aras.cvcreator.service;

import com.aras.cvcreator.exceptions.PasswordWrongFormatException;
import com.aras.cvcreator.model.Users;

public interface UserService {
    Users getUserById(Integer id);

    Users getByEmail(String email);

    Users save(Users user);

    Users create(Users user);

    boolean registerAuthorise(String email, String password) throws PasswordWrongFormatException;

    Users loginAuthorise(String email, String pass) throws PasswordWrongFormatException;

    Users getByPersonId(Integer id);
}
