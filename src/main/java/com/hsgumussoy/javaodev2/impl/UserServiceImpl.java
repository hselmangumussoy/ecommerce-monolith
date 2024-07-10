package com.hsgumussoy.javaodev2.impl;

import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.entity.Category;
import com.hsgumussoy.javaodev2.exception.RecordNotFoundExceptions;
import com.hsgumussoy.javaodev2.mapper.UserMapper;
import com.hsgumussoy.javaodev2.repository.UserRepository;
import com.hsgumussoy.javaodev2.entity.User;
import com.hsgumussoy.javaodev2.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto save(UserDto dto) {
        return userMapper.entityToDto(repository.save(userMapper.dtoToEntity(dto)));
    }

    @Override
    public UserDto get(String id) {
        User user = repository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RecordNotFoundExceptions(4000, "User not fund with" + id));

        return userMapper.entityToDto(user);
    }


    @Override
    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    @Override
    public UserDto update(String id, UserDto dto) {
        User userCategory = repository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RecordNotFoundExceptions(4000, "User not found with id" + id));

        User updateUser = userMapper.dtoToEntity(dto);
        updateUser.setId(userCategory.getId());

        return userMapper.entityToDto(repository.save(updateUser));

    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : repository.findAll()) {
            userDtoList.add(userMapper.entityToDto(user));
        }
        return userDtoList;
    }


    public User findUserById(Long userId) {
        if(userId == null){
            throw  new IllegalArgumentException("The given id must not be null");
        }
        return repository.findById(userId).orElseThrow();
    }
}
