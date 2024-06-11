package com.hsgumussoy.javaodev2.response;

public class BasketResponse {
    private Long id;
    private int userId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
