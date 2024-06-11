package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.request.BasketRequest;
import com.hsgumussoy.javaodev2.response.BasketResponse;
import com.hsgumussoy.javaodev2.service.impl.BasketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/baskets")
public class BasketController {
    @Autowired
    private BasketServiceImpl service;

    @PostMapping
    public BasketResponse save(@RequestBody BasketRequest request) {
        return toResponse(service.save(toDto(request)));
    }


    @GetMapping("/{id}")
    public BasketResponse get(@PathVariable(name= "id") String id){
        return toResponse(service.get(id));
    }

    /*
    @GetMapping
    public List<UserResponse> getAll(){
        return toResponse(service.getAll());
    }*/

    @DeleteMapping("/{id}")
    public BasketResponse delete(@PathVariable(name= "id") String id){
        return toResponse( service.delete(id));
    }
    @PutMapping("/{id}")
    public BasketResponse update(@PathVariable(name= "id") String id, @RequestBody BasketRequest request){
        return toResponse(service.update(id, toDto(request)));
    }

    private BasketResponse toResponse(BasketDto dto) {
        BasketResponse response = new BasketResponse();
        response.setId(response.getId());
        response.setUserId(response.getUserId());
        return response;
    }
    private BasketDto toDto(BasketRequest request) {
        BasketDto dto = new BasketDto();
        dto.setId(request.getId());
        //dto.setUser(request.getUserId()); --> burada veri tipinden dolayı hata alıyorum. Nasıl bir çözüm üretmeliyim.
        return dto;
    }
}
