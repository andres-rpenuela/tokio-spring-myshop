package com.tokioschool.myshop.service;

import com.tokioschool.myshop.domain.User;
import com.tokioschool.myshop.dto.UserFormDto;

import java.util.Optional;
import java.util.Set;

/**
 * Service para gestión de usuarios
 */
public interface UserService {

    boolean add(UserFormDto userFormDto);
    public boolean update(User user) ;
    void remove(User user);
    Set<User> findAll();
    User findByUsername(String username);
    Optional<UserFormDto> findByUserId(Long id);
    Set<User> findByCity(String city);
}
