package com.hackathon.ehealthcareproject.service.cart;

import com.hackathon.ehealthcareproject.dto.cart.CartRequestDto;

public interface CartService {



    void addCartItemToCart(CartRequestDto cartRequestDto);
    void removeCartItemFromCart(CartRequestDto cartRequestDto);
    void clearAllItemsFromCart(Long userId);
}
