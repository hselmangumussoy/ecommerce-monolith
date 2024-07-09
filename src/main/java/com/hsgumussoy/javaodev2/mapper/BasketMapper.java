package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.request.BasketRequest;
import com.hsgumussoy.javaodev2.response.BasketResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BasketMapper {
    public BasketDto requestToDto(BasketRequest request) {
        return BasketDto.builder()
                .status(request.getCount())
                .totalPrice(request.getTotalPrice())
                .userId(request.getUserId())
                .build();
                //.basketProductDtoList(request.)build();
    }
    public BasketResponse dtoToResponse(BasketDto dto) {
        return BasketResponse.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .totalPrice(dto.getTotalPrice())
                .basketProductDtoList(dto.getBasketProductDtoList())
                .build();
    }
    public List<BasketResponse> mapDtosToResponses(List<BasketDto> basketDtoList){
        return basketDtoList.stream()
                .map(this::dtoToResponse)
                .collect(Collectors.toList());
    }


}
