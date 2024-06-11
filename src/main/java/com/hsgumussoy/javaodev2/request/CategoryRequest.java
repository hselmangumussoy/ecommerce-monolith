package com.hsgumussoy.javaodev2.request;

import com.hsgumussoy.javaodev2.entity.Product;

import java.util.List;

public class CategoryRequest {
    private Long id;
    private String name;
    private List<ProductRequest> productList;

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

    public List<ProductRequest> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductRequest> productList) {
        this.productList = productList;
    }
}
