package com.hsgumussoy.javaodev2.repository;


import com.hsgumussoy.javaodev2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User deleteUserById(Long id);

}
