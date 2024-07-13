package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.dto.BasketProductDto;
import com.hsgumussoy.javaodev2.entity.Basket;
import com.hsgumussoy.javaodev2.entity.BasketProduct;
import com.hsgumussoy.javaodev2.entity.User;
import com.hsgumussoy.javaodev2.exception.RecordNotFoundExceptions;
import com.hsgumussoy.javaodev2.mapper.BasketMapper;
import com.hsgumussoy.javaodev2.mapper.BasketProductMapper;
import com.hsgumussoy.javaodev2.repository.BasketRepository;
import com.hsgumussoy.javaodev2.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository repository;
    @Autowired
    private BasketMapper basketMapper;
    @Autowired
    @Lazy
    private BasketProductMapper basketProductMapper;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    @Lazy
    private BasketProductServiceImpl basketProductService;

    private static  int BASKET_STATUS_NONE = 0;
    private static int BASKET_STATUS_SALED = 1;
    @Override
    public BasketDto save(BasketDto dto) {
        // Kullanıcıya ait açık bir sepet var mı kontrol et
        User user = userService.findUserById(dto.getUserId());
        Optional<Basket> existingBasket = repository.findByUserIdAndStatus(user.getId(),BASKET_STATUS_NONE);

        Basket basket;
        if(existingBasket.isPresent()){
            basket = existingBasket.get();
        }else {
            // yeni sepet oluştur.
            basket = basketMapper.dtoToEntity(dto);
            basket.setStatus(BASKET_STATUS_NONE);
            basket.setUser(user);

            repository.save(basket);
        }

        //sepete ürün ekle
        if (dto.getBasketProductList() != null) {  // Null kontrolü
            for (BasketProductDto bpDto : dto.getBasketProductList()) {
                try {
                    BasketProduct existingBasketProduct = basketProductService.findBasketProductByBasketIdAndProductId(basket.getId(), bpDto.getProductId());
                    existingBasketProduct.setCount(existingBasketProduct.getCount() + bpDto.getCount());
                    existingBasketProduct.setTotalAmount(existingBasketProduct.getTotalAmount() + bpDto.getTotalAmount());
                    basketProductService.save(existingBasketProduct);
                } catch (IllegalArgumentException e) {
                    BasketProduct bp = basketProductMapper.dtoToEntity(bpDto);
                    bp.setBasket(basket);
                    basketProductService.save(bp);
                }
            }
        } else {
            dto.setBasketProductList(new ArrayList<>());  // Null ise boş bir liste oluştur
        }
        return basketMapper.entityToDto(basket);

    }

    @Override
    public BasketDto get(String id) {
        return basketMapper.entityToDto(repository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RecordNotFoundExceptions(4000,"Basket not found with id"+id)));
    }


    @Override
    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    @Override
    public BasketDto update(String id, BasketDto dto) {
        // sepetin varlığını kontrol et
        Basket existingBasket = repository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RecordNotFoundExceptions(4000, "Basket not found with id"+id));

        //sepet durumu kontrolü
        if(existingBasket.getStatus() == BASKET_STATUS_SALED){
            throw new IllegalArgumentException("Can not update a sold basket" );
        }

        //sepeti güncelle
        existingBasket = basketMapper.dtoToEntity(dto);
        existingBasket.setId(Long.parseLong(id));
        Basket updatedBasket = repository.save(existingBasket);

        //Sepetteki ürünleri güncelle
        double totalPrice = 0;
        for(BasketProduct bp: updatedBasket.getBasketProductList()){
            bp.setBasket(updatedBasket);
            basketProductService.save(bp);
            totalPrice += bp.getTotalAmount();
        }
        updatedBasket.setTotalPrice(totalPrice);

        return basketMapper.entityToDto(repository.save(updatedBasket));
    }


    @Override
    public List<BasketDto> getAll() {
        List<BasketDto> basketDtoList = new ArrayList<>();

        for(Basket basket: repository.findAll()){
            basketDtoList.add(basketMapper.entityToDto(basket));
        }
        return basketDtoList;

    }


    public Basket findBasketById(Long basketId) {
        if(basketId == null){
            throw  new IllegalArgumentException("The given id must not be null");
        }
        return repository.findById(basketId).orElseThrow();
    }
}




