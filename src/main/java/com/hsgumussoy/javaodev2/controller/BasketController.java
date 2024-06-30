package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.request.BasketRequest;
import com.hsgumussoy.javaodev2.response.BasketResponse;
import com.hsgumussoy.javaodev2.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/baskets")
public class BasketController {
    @Autowired
    private BasketService basketService;

    @PostMapping
    public BasketResponse save(@RequestBody BasketRequest request) {
        return toResponse(basketService.save(toDto(request)));
    }



    @GetMapping("/{id}")
    public BasketResponse get(@PathVariable(name = "id") String id) {
        return toResponse(basketService.get(id));
    }

    @GetMapping
    public List<BasketResponse> getAll(){
        List<BasketDto> basketDtos = basketService.getAll();
        List<BasketResponse> basketResponses = basketDtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return basketResponses;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        basketService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public BasketResponse update(@PathVariable(name = "id") String id, @RequestBody BasketRequest request) {
        return toResponse(basketService.update(id, toDto(request)));
    }






    private BasketDto toDto(BasketRequest request) {
        BasketDto dto = new BasketDto();
        dto.setId(request.getUserId());
        return dto;
    }

    private BasketResponse toResponse(BasketDto dto) {
        BasketResponse response = new BasketResponse();
        response.setId(dto.getId());
        response.setUserId(dto.getUser().getId());
        return response;
    }



}
