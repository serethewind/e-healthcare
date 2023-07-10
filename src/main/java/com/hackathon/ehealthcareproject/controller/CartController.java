package com.hackathon.ehealthcareproject.controller;

import com.hackathon.ehealthcareproject.service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/health/v1/carts")
public class CartController {

    private CartService cartService;

    @PutMapping("{userId}/{productId}")
    public ResponseEntity<String> addItemToCart(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId){
        cartService.addCartItemToCart(userId, productId);
        return ResponseEntity.ok("Item added to cart");
    }

    @DeleteMapping("{userId}/{productId}")
    public ResponseEntity<String> removeSingleItemFromCart(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId){
        cartService.removeCartItemFromCart(userId, productId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> removeAllItems(@PathVariable("userId") Long userId){
        cartService.clearAllItemsFromCart(userId);
        return ResponseEntity.ok("All items removed from cart");
    }
 }
