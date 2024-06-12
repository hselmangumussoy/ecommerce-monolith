package com.hsgumussoy.javaodev2.dto;

import com.hsgumussoy.javaodev2.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {
    private Long id;
    private User user;


}
