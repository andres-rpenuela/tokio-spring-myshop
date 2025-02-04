package com.tokioschool.myshop.service.impl;

import com.tokioschool.myshop.domain.Role;
import com.tokioschool.myshop.domain.User;
import com.tokioschool.myshop.dto.UserFormDto;
import com.tokioschool.myshop.repository.RoleRepository;
import com.tokioschool.myshop.repository.UserRepository;
import com.tokioschool.myshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static com.tokioschool.myshop.security.Constants.USER_ROLE;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public boolean add(UserFormDto userFormDto) {
        final User user = modelMapper.map(userFormDto, User.class);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreationDate(LocalDate.now());
        user.setActive(true);
        Role userRole = roleRepository.findByName(USER_ROLE);
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);

        return true;
    }
    
    @Override
    public boolean update(User user) {
        userRepository.save(user);

        return true;
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public Set<User> findAll() {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserFormDto> findByUserId(Long id) {
        if(Objects.isNull(id)){
            return Optional.empty();
        }
        return userRepository.findById(id)
                .map(user ->modelMapper.map(user, UserFormDto.class));
    }

    @Override
    public Set<User> findByCity(String city) {
        return null;
    }
}
