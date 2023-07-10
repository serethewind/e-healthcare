package com.hackathon.ehealthcareproject.service.cart;

public interface CartService {



    void addCartItemToCart(Long userId, Long productId);
    void removeCartItemFromCart(Long userId, Long productId);
    void clearAllItemsFromCart(Long userId);
}
