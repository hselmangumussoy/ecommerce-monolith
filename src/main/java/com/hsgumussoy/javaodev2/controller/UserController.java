package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.request.UserRequest;
import com.hsgumussoy.javaodev2.response.UserResponse;
import com.hsgumussoy.javaodev2.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @PostMapping
    public UserResponse save(@RequestBody UserRequest request) {
        return toResponse(service.save(toDto(request)));
    }


    @GetMapping("/{id}")
    public UserResponse get(@PathVariable(name= "id") String id){
        return toResponse(service.get(id));
    }

    /*
    @GetMapping
    public List<UserResponse> getAll(){
        return toResponse(service.getAll());
    }*/

    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable(name= "id") String id){
        return toResponse( service.delete(id));
    }
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable(name= "id") String id, @RequestBody UserRequest request){
        return toResponse(service.update(id, toDto(request)));
    }

    private UserResponse toResponse(UserDto dto) {
        UserResponse response = new UserResponse();
        response.setUserName(dto.getUserName());
        response.setFullName(dto.getFullName());
        response.setBirthDate(dto.getBirthDate());
        response.setBirthPlace(dto.getBirthPlace());
        response.setTelNo(dto.getTelNo());
        response.setTckn(dto.getTckn());
        return response;
    }
    private UserDto toDto(UserRequest request) {
        UserDto dto = new UserDto();
        dto.setUserName(request.getUserName());
        dto.setFullName(request.getFullName());
        dto.setBirthDate(request.getBirthDate());
        dto.setBirthPlace(request.getBirthPlace());
        dto.setTelNo(request.getTelNo());
        dto.setTckn(request.getTckn());
        return dto;
    }
}
