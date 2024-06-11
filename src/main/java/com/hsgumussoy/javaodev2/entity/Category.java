package com.hsgumussoy.javaodev2.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Product> products;

    //mappedBy = "category" ifadesi, Product sınıfındaki category alanının,
    //bu ilişkinin yönetileceği taraf olduğunu belirtir
    //Bu durumda, eğer bir kategoriye bir ürün eklerseniz,
    // JPA otomatik olarak bu ürünün ilgili kategoriye ait olduğunu anlayacak
    // ve veritabanında uygun şekilde ilişkilendirme yapacaktır. mappedBy özelliği,
    // veritabanında gereksiz yere ekstra bir dış anahtar sütunu oluşturmadan ilişkileri yönetmeye yardımcı olur.


    public Category() {
    }

    public Category(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
