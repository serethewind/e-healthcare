//package com.hackathon.ehealthcareproject.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table(name = "cartItem_database")
//public class CartItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @OneToMany
//    @JoinColumn(name = "product_entity_id")
//    private ProductEntity productEntity;
//
//    private int quantity;
//
//    @ManyToOne
//    @JoinColumn(name = "cart_id", referencedColumnName = "id")
//    private Cart cart;
//}
