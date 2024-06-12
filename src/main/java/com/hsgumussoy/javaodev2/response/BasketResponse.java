package com.hsgumussoy.javaodev2.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketResponse {
    private Long id;
    private int userId;

}
