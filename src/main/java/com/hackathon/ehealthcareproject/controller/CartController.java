package com.hackathon.ehealthcareproject.controller;

import com.hackathon.ehealthcareproject.dto.cart.CartRequestDto;
import com.hackathon.ehealthcareproject.service.cart.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/health/v1/carts")
@AllArgsConstructor
public class CartController {

    private CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<String> addItemToCart(@RequestBody CartRequestDto cartRequestDto){
        cartService.addCartItemToCart(cartRequestDto);
        return ResponseEntity.ok("Item added to cart");
    }

    @DeleteMapping("/removeFromCart")
    public ResponseEntity<String> removeSingleItemFromCart(@RequestBody CartRequestDto cartRequestDto){
        cartService.removeCartItemFromCart(cartRequestDto);
        return ResponseEntity.ok("Item removed from cart");
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> removeAllItems(@PathVariable("userId") Long userId){
        cartService.clearAllItemsFromCart(userId);
        return ResponseEntity.ok("All items removed from cart");
    }
 }
