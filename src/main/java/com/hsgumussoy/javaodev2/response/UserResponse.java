package com.hsgumussoy.javaodev2.response;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String userName;
    private String fullName;
    private String tckn;
    private String birthPlace;
    private Date birthDate;
    private String telNo;


}
