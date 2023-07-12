package com.hackathon.ehealthcareproject.service.cart;

import com.hackathon.ehealthcareproject.dto.cart.CartRequestDto;
import com.hackathon.ehealthcareproject.entity.Cart;
import com.hackathon.ehealthcareproject.entity.ProductEntity;
import com.hackathon.ehealthcareproject.entity.UserEntity;
import com.hackathon.ehealthcareproject.repository.CartRepository;
import com.hackathon.ehealthcareproject.repository.ProductRepository;
import com.hackathon.ehealthcareproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    /**
     * create cart, fetch product from product repository,
     * add product to cart.
     */

    @Override
    public void addCartItemToCart(CartRequestDto cartRequestDto) {
        UserEntity user = userRepository.findById(cartRequestDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProductEntity productEntity = productRepository.findById(cartRequestDto.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Cart shoppingCart = user.getShoppingCart();
        if (shoppingCart == null) {
            shoppingCart = new Cart();
            shoppingCart.setUser(user);
        }

        shoppingCart.getProducts().add(productEntity);
        shoppingCart.setQuantity(shoppingCart.getQuantity() + 1);

        cartRepository.save(shoppingCart);
    }

    @Override
    public void removeCartItemFromCart(CartRequestDto cartRequestDto) {
        UserEntity user = userRepository.findById(cartRequestDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProductEntity productEntity = productRepository.findById(cartRequestDto.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Cart cart = user.getShoppingCart();
        ProductEntity foundProduct = cart.getProducts().stream().filter(product -> product.getId()==productEntity.getId()).toList().iterator().next();
        cart.getProducts().remove(foundProduct);
        cart.setQuantity(cart.getQuantity() - 1);
        cartRepository.save(cart);
    }

    @Override
    public void clearAllItemsFromCart(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Cart cart = user.getShoppingCart();
        cart.getProducts().clear();
        cartRepository.save(cart);
    }
}
