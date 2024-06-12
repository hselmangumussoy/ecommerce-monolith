package com.hsgumussoy.javaodev2.request;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String userName;
    private String fullName;
    private String tckn;
    private String birthPlace;
    private Date birthDate;
    private String telNo;


}
