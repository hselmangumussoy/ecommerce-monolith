package com.hsgumussoy.javaodev2.impl;

import com.hsgumussoy.javaodev2.entity.BasketProduct;
import com.hsgumussoy.javaodev2.repository.BasketProductRepository;
import com.hsgumussoy.javaodev2.service.BasketProductService;
import com.hsgumussoy.javaodev2.service.BasketService;
import com.hsgumussoy.javaodev2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketProductServiceImpl implements BasketProductService {

    @Autowired
    BasketProductRepository repository;
    @Autowired
    BasketService basketService;

    @Autowired
    ProductService productService;

    @Override
    public BasketProduct save(BasketProduct basketProduct) {
        return repository.save(basketProduct).findBasketProductByBasket_BasketIdAndProduct_ProductId;
    }

}