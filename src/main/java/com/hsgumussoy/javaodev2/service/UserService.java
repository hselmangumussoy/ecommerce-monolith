package com.hsgumussoy.javaodev2.service;

import com.hsgumussoy.javaodev2.dto.UserDto;

public interface   UserService {
    public UserDto save(UserDto dto);
    public UserDto get(String id);
    public void delete(String id);
    public UserDto update(String id, UserDto dto);
    //public List<Basket>getAll();
}
