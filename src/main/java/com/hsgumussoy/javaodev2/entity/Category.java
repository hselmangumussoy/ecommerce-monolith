package com.hsgumussoy.javaodev2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    //@JoinColumn(name = "product_id")-> joinColumn sadece ManyToOne ve OneToOne da kullanılır
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> productList;

    /*
    @OneToMany(mappedBy = "category" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();
    */

    //mappedBy = "category" ifadesi, Product sınıfındaki category alanının,
    //bu ilişkinin yönetileceği taraf olduğunu belirtir
    //Bu durumda, eğer bir kategoriye bir ürün eklerseniz,
    // JPA otomatik olarak bu ürünün ilgili kategoriye ait olduğunu anlayacak
    // ve veritabanında uygun şekilde ilişkilendirme yapacaktır. mappedBy özelliği,
    // veritabanında gereksiz yere ekstra bir dış anahtar sütunu oluşturmadan ilişkileri yönetmeye yardımcı olur.



}
