package com.hsgumussoy.javaodev2.repository;


import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //UserDto findUserById(Long id);

    //UserDto deleteUserById(Long id);

}
