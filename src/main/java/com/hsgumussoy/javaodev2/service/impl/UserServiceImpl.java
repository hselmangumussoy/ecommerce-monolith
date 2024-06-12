package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.repository.UserRepository;
import com.hsgumussoy.javaodev2.entity.User;
import com.hsgumussoy.javaodev2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    public UserDto save(UserDto dto) {
        return  toDto(repository.save(toEntity(dto)));
    }



    public UserDto get(String id) {
        User user = repository.findUserByUserId(Long.parseLong(id));
        return toDto(user);
    }

    public UserDto delete(String id) {
        User user = repository.deleteUserById(Long.parseLong(id));
        return toDto(user);
    }

    public UserDto update(String id, UserDto dto) {
        User existUser =repository.findUserByUserId(Long.parseLong(id));
        if(existUser != null){
            existUser.setId(dto.getId());
            existUser.setUserName(dto.getUserName());
            existUser.setFullName(dto.getFullName());
            existUser.setTelNo(dto.getTelNo());
            existUser.setTckn(dto.getTckn());
            existUser.setBirthDate(dto.getBirthDate());
            existUser.setBirthPlace(dto.getBirthPlace());
        }
        else {
            return  null;
        }
        return toDto(repository.save(existUser));
    }

   /* public List<UserDto> getAll() {
        return repository.findAll();
    }*/
    private User toEntity(UserDto dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setFullName(dto.getFullName());
        user.setBirthDate(dto.getBirthDate());
        user.setBirthPlace(dto.getBirthPlace());
        user.setTelNo(dto.getTelNo());
        user.setTckn(dto.getTckn());
        return user;

    }
    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserName(user.getUserName());
        dto.setFullName(user.getFullName());
        dto.setBirthDate(user.getBirthDate());
        dto.setBirthPlace(user.getBirthPlace());
        dto.setTelNo(user.getTelNo());
        dto.setTckn(user.getTckn());
        return dto;
    }
}
