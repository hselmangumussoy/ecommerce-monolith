package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.mapper.UserMapper;
import com.hsgumussoy.javaodev2.request.UserRequest;
import com.hsgumussoy.javaodev2.response.UserResponse;
import com.hsgumussoy.javaodev2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public UserResponse save(@RequestBody UserRequest request) {
        return userMapper.dtoToResponse(service.save(userMapper.requestToDto(request)));
    }


    @GetMapping("/{id}")
    public UserResponse get(@PathVariable(name = "id") String id) {
        return userMapper.dtoToResponse(service.get(id));
    }


    @GetMapping
    public List<UserResponse> getAll() {
        return userMapper.dtosToResponses(service.getAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") String id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable(name = "id") String id, @RequestBody UserRequest request) {
        return userMapper.dtoToResponse(service.update(id, userMapper.requestToDto(request)));
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


}
