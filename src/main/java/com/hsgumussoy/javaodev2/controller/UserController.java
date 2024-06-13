package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.request.UserRequest;
import com.hsgumussoy.javaodev2.response.BasketResponse;
import com.hsgumussoy.javaodev2.response.UserResponse;
import com.hsgumussoy.javaodev2.service.UserService;
import com.hsgumussoy.javaodev2.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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


    @GetMapping
    public List<UserResponse> getAll(){
        List<UserDto> dtos  = service.getAll();
        List<UserResponse> userResponses = dtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return userResponses;
    }
    /*
    stream(): Bir koleksiyon üzerinde işlem yapmak için kullanılan bir Stream oluşturur.
    Stream, koleksiyon elemanlarını tek tek işlemek için kullanılır.

    map(this::toResponse): Stream üzerindeki her bir öğeyi toResponse metoduna uygular.
    Burada this::toResponse, sınıf içindeki toResponse metodunu işaret eder.

    collect(Collectors.toList()): Stream üzerindeki öğeleri bir List'e dönüştürür.

    toList() metodu, Stream API'nin bir parçası olan Collectors sınıfından gelen bir metottur.

    :: Bu operatör, bir metodu veya bir sınıfın yapıcısını referans almak için kullanılır.
    Örneğin, this::toResponse, bu sınıf içindeki toResponse metodunu referans alır.

    */

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name= "id") String id){
            service.delete(id);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable(name= "id") String id, @RequestBody UserRequest request) {
        try {
            return toResponse(service.update(id, toDto(request)));

        } catch (IllegalArgumentException e) {
            UserResponse response = new UserResponse();
            response.setErrorCode(4001);
            response.setErrorMessage("Geçersiz istek.");
            return response;
        } catch (RuntimeException e) {
            UserResponse response = new UserResponse();
            response.setErrorCode(4002);
            response.setErrorMessage("Kullanıcı güncellenemedi.");
            return response;
        }
    }
    /*
    public ResponseEntity<UserResponse> update(@PathVariable(name= "id") String id, @RequestBody UserRequest request) {
    try {
        UserDto dto = service.update(id, toDto(request));
        UserResponse response = toResponse(dto);
        return ResponseEntity.ok(response);
    } catch (IllegalArgumentException e) {
        UserResponse response = new UserResponse();
        response.setErrorCode(4001);
        response.setErrorMessage("Geçersiz istek.");
        return ResponseEntity.badRequest().body(response);
    } catch (RuntimeException e) {
        UserResponse response = new UserResponse();
        response.setErrorCode(4002);
        response.setErrorMessage("Kullanıcı güncellenemedi.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}*/

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
