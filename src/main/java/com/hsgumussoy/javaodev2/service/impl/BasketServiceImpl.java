package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.dto.BasketProductDto;
import com.hsgumussoy.javaodev2.entity.Basket;
import com.hsgumussoy.javaodev2.entity.BasketProduct;
import com.hsgumussoy.javaodev2.entity.Product;
import com.hsgumussoy.javaodev2.entity.User;
import com.hsgumussoy.javaodev2.exception.RecordNotFoundExceptions;
import com.hsgumussoy.javaodev2.mapper.BasketMapper;
import com.hsgumussoy.javaodev2.mapper.BasketProductMapper;
import com.hsgumussoy.javaodev2.repository.BasketProductRepository;
import com.hsgumussoy.javaodev2.repository.BasketRepository;
import com.hsgumussoy.javaodev2.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository repository;
    @Autowired
    private BasketProductRepository basketProductRepository;
    @Autowired
    private BasketMapper basketMapper;
    @Autowired
    @Lazy
    private BasketProductMapper basketProductMapper;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    @Lazy
    private BasketProductServiceImpl basketProductService;

    private static  int BASKET_STATUS_NONE = 0;
    private static int BASKET_STATUS_SALED = 1;
    @Override
    public BasketDto save(BasketDto dto) {
        // Kullanıcıya ait açık bir sepet var mı kontrol et
        User existingUser = userService.findUserById(dto.getUserId());
        if(existingUser == null){
            throw  new RecordNotFoundExceptions(4000, "User  not found");
        }

        Optional<Basket> existingBasket = repository.findByUserIdAndStatus(existingUser.getId(), BASKET_STATUS_NONE);

        Product existingProduct = productService.findProductById(dto.getProductId());

        if(existingBasket.isPresent()){
            return basketMapper.entityToDto(alreadyExistBasket(dto, existingProduct, existingBasket.get()));
        }else{
            return basketMapper.entityToDto(addToBasket(dto, existingProduct,existingUser));
        }
        /*Basket basket;
        if (existingBasket.isPresent()) {
            basket = existingBasket.get();
        } else {
            // yeni sepet oluştur.
            basket = basketMapper.dtoToEntity(dto);
            basket.setStatus(BASKET_STATUS_NONE);
            basket.setUser(user);

            repository.save(basket);
        }

        // Sepete ürün ekle
        if (dto.getBasketProductList() != null && !dto.getBasketProductList().isEmpty()) {  // Null kontrolü
            for (BasketProductDto bpDto : dto.getBasketProductList()) {
                BasketProduct existingBasketProduct = basketProductService.findBasketProductByBasketIdAndProductId(basket.getId(), bpDto.getProductId());
                if (existingBasketProduct != null){
                    // Eğer ürün zaten sepette varsa, miktarı artır
                    existingBasketProduct.setCount(existingBasketProduct.getCount() + bpDto.getCount());
                    existingBasketProduct.setTotalAmount(existingBasketProduct.getTotalAmount() + bpDto.getTotalAmount());
                    basketProductService.save(existingBasketProduct);
                } else {
                    // Yeni ürün ekleyin
                    BasketProduct bp = basketProductMapper.dtoToEntity(bpDto);
                    bp.setProduct(productService.findProductById(bpDto.getProductId()));
                    basketProductService.save(bp);
                }
            }
        } else {
            dto.setBasketProductList(new ArrayList<>());  // Null ise boş bir liste oluştur
        }*/

    }

    private Basket addToBasket(BasketDto dto, Product existingProduct,User existingUser) {
        Basket basket = new Basket();

        basket.setUser(existingUser);
        basket.setStatus(BASKET_STATUS_NONE);
        basket.setTotalPrice(existingProduct.getPrice() * dto.getCount());

        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setCount(dto.getCount());
        basketProduct.setTotalAmount(existingProduct.getPrice() * dto.getCount());
        basketProduct.setBasket(basket);
        basketProduct.setProduct(existingProduct);

        basketProduct = basketProductRepository.save(basketProduct);
        basket.setBasketProductList(List.of(basketProduct));

        basket =repository.save(basket);

        return basket;
    }

    private Basket alreadyExistBasket(BasketDto dto, Product existingProduct, Basket basket) {
        basket.setTotalPrice(basket.getTotalPrice() + existingProduct.getPrice()*dto.getCount());
        List<BasketProduct> basketProductList = basket.getBasketProductList();

        boolean isFindBasketProduct = false;
        if(basketProductList != null){
            for( BasketProduct basketProduct: basketProductList) {
                if (Objects.equals(basketProduct.getProduct().getId(), existingProduct.getId())) {
                    basketProduct.setCount(dto.getCount() + basketProduct.getCount());
                    basketProduct.setTotalAmount(dto.getCount() * existingProduct.getPrice() + basketProduct.getTotalAmount());
                    isFindBasketProduct = true;
                    break;
                }

            }
        }
        if(!isFindBasketProduct){
            BasketProduct basketProduct = new BasketProduct();
            basketProduct.setBasket(basket);
            basketProduct.setProduct(existingProduct);
            basketProduct.setCount(dto.getCount());
            basketProduct.setTotalAmount(dto.getCount() * existingProduct.getPrice());

            basketProduct= basketProductRepository.save(basketProduct);
            basketProductList.add(basketProduct);
        }

        basket.setBasketProductList(basketProductList);
        basket = repository.save(basket);


        return basket;
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




