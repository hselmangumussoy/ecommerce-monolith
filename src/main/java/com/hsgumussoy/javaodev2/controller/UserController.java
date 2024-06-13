package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.request.UserRequest;
import com.hsgumussoy.javaodev2.response.BasketResponse;
import com.hsgumussoy.javaodev2.response.UserResponse;
import com.hsgumussoy.javaodev2.service.UserService;
import com.hsgumussoy.javaodev2.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService service;

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
    public void delete(@PathVariable(name= "id") String id){
        service.delete(id);
    }
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable(name= "id") String id, @RequestBody UserRequest request)throws Exception{
        UserDto dto = service.update(id, toDto(request));

        try {
            return toResponse(dto);
        }catch (Exception e){
            UserResponse response =new UserResponse();
            response.errorCode =4001;
            response.errorMessage = "Kullanıcı Güncellenemedi.";
            return response;
        }
    }

    private UserResponse toResponse(UserDto dto) {
        return  UserResponse.builder()
                .id(dto.getId())
                .userName(dto.getUserName())
                .fullName(dto.getFullName())
                .password(dto.getPassword())
                .telNo(dto.getTelNo())
                .birthDate(dto.getBirthDate())
                .birthPlace(dto.getBirthPlace())
                .tckn(dto.getTckn())
                .build();
    }
    private UserDto toDto(UserRequest request) {
        return UserDto.builder()
                .userName(request.getUserName())
                .fullName(request.getFullName())
                .password(request.getPassword())
                .birthDate(request.getBirthDate())
                .birthPlace(request.getBirthPlace())
                .telNo(request.getTelNo())
                .tckn(request.getTckn())
                .build();
    }
}
