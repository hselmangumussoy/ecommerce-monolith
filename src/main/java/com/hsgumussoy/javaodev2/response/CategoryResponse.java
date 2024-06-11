package com.hsgumussoy.javaodev2.response;

import com.hsgumussoy.javaodev2.entity.Product;

import java.util.List;

public class CategoryResponse {
    private Long id;
    private String name;
    private List<ProductResponse> productList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductResponse> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductResponse> productList) {
        this.productList = productList;
    }
}
