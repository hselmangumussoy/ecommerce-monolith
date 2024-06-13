package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.request.BasketRequest;
import com.hsgumussoy.javaodev2.response.BasketResponse;
import com.hsgumussoy.javaodev2.service.BasketService;
import com.hsgumussoy.javaodev2.service.impl.BasketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baskets")
public class BasketController {
    @Autowired
    private BasketService service;

    @PostMapping
    public BasketResponse save(@RequestBody BasketRequest request) {
        return toResponse(service.save(toDto(request)));
    }

    @GetMapping("/{id}")
    public BasketResponse get(@PathVariable(name = "id") String id) {
        return toResponse(service.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public BasketResponse update(@PathVariable(name = "id") String id, @RequestBody BasketRequest request) {
        return toResponse(service.update(id, toDto(request)));
    }

    private BasketResponse toResponse(BasketDto dto) {
        BasketResponse response = new BasketResponse();
        response.setId(dto.getId());
        response.setUserId(dto.getUser().getId()); // DTO'dan gelen User'ın ID'sini ayarlıyoruz
        return response;
    }

    private BasketDto toDto(BasketRequest request) {
        BasketDto dto = new BasketDto();
        UserDto userDto = new UserDto();

        dto.setId(request.getUserId());

        userDto.setId(request.getUserId());
        dto.setUser(userDto);

        return dto;
    }
}
