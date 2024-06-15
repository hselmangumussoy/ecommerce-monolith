package com.hsgumussoy.javaodev2.response;

import com.hsgumussoy.javaodev2.response.baseResponse.BaseResponse;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse extends BaseResponse {
    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private String tckn;
    private String birthPlace;
    private Date birthDate;
    private String telNo;


}
