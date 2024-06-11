package com.hsgumussoy.javaodev2.dto;


import java.util.List;

public class CategoryDto {
    private Long id;
    private String name;
    private List<ProductDto> productsList;

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

    public List<ProductDto> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductDto> productsList) {
        this.productsList = productsList;
    }
}
