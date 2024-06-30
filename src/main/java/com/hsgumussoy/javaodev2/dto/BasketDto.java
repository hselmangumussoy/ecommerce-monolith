package com.hsgumussoy.javaodev2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {
    private Long id;
    private UserDto user ;
}
