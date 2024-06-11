package com.hsgumussoy.javaodev2.dto;

import com.hsgumussoy.javaodev2.entity.User;

public class BasketDto {
    private Long id;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
