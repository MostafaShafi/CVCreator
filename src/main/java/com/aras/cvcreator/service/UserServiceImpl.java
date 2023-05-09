package com.aras.cvcreator.service;

import com.aras.cvcreator.exceptions.PasswordWrongFormatException;
import com.aras.cvcreator.model.Users;
import com.aras.cvcreator.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final String PASSWORD_FORMAT = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Override
    @Transactional
    public Users create(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    @Override
    @Transactional
    public Users getUserById(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Users getByEmail(String email) {
        return usersRepository.getByEmail(email);
    }

    @Override
    @Transactional
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Override
    @Transactional
    public boolean registerAuthorise(String email, String password) throws PasswordWrongFormatException {
        checkPasswordFormat(password);

        if (getByEmail(email) == null)
            return true;
        return false;
    }

    @Override
    @Transactional
    public Users loginAuthorise(String email, String password) throws PasswordWrongFormatException, UsernameNotFoundException {
        checkPasswordFormat(password);
        Users user = getByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException(new StringBuilder("User not found by email: ").append(email).toString());
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        else
            throw new BadCredentialsException("Invalid password!");
    }

    private boolean checkPasswordFormat(String password) throws PasswordWrongFormatException {
        Pattern pattern = Pattern.compile(PASSWORD_FORMAT);
        if (!pattern.matcher(password).matches())
            throw new PasswordWrongFormatException();
        return true;
    }

    @Override
    @Transactional
    public Users getByPersonId(Integer id) {
        return usersRepository.getByPersonId(id);
    }
}
