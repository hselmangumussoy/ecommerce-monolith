package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.repository.UserRepository;
import com.hsgumussoy.javaodev2.entity.User;
import com.hsgumussoy.javaodev2.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDto save(UserDto dto) {
        return  toDto(repository.save(toEntity(dto)));
    }

    @Override
    public UserDto get(String id) {
        User user = repository.findUserById(Long.parseLong(id));
        return toDto(user);
    }


    @Override
    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    @Override
    public UserDto update(String id, UserDto dto) {
        try{
            User existUser =repository.findUserById(Long.parseLong(id));

            if(existUser == null){
                throw new Exception("Kullanıcı Bulunamadı");
            }

            existUser.setUserName(dto.getUserName());
            existUser.setFullName(dto.getFullName());
            existUser.setPassword(dto.getPassword());
            existUser.setTelNo(dto.getTelNo());
            existUser.setTckn(dto.getTckn());
            existUser.setBirthDate(dto.getBirthDate());
            existUser.setBirthPlace(dto.getBirthPlace());

            return toDto(repository.save(existUser));
        }catch (NumberFormatException |NullPointerException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("geçersiz id veya veri");
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Kullanıcı güncellenemedi");
        }


    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private User toEntity(UserDto dto) {
        User user = new User();
        user.setId(user.getId());
        user.setUserName(dto.getUserName());
        user.setFullName(dto.getFullName());
        user.setPassword(dto.getPassword());
        user.setBirthDate(dto.getBirthDate());
        user.setBirthPlace(dto.getBirthPlace());
        user.setTelNo(dto.getTelNo());
        user.setTckn(dto.getTckn());
        return user;

    }
    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setFullName(user.getFullName());
        dto.setPassword(user.getPassword());
        dto.setBirthDate(user.getBirthDate());
        dto.setBirthPlace(user.getBirthPlace());
        dto.setTelNo(user.getTelNo());
        dto.setTckn(user.getTckn());
        return dto;
    }
}
