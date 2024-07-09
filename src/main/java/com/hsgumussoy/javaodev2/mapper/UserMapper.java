package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.entity.User;

public class UserMapper {
    public User dtoToEntity(UserDto dto) {
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

    private UserDto EntityToDto(User user) {
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
