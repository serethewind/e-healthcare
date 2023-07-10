package com.hackathon.ehealthcareproject.service.cart;

import com.hackathon.ehealthcareproject.entity.Cart;
import com.hackathon.ehealthcareproject.entity.CartItem;
import com.hackathon.ehealthcareproject.entity.ProductEntity;
import com.hackathon.ehealthcareproject.entity.UserEntity;
import com.hackathon.ehealthcareproject.repository.CartRepository;
import com.hackathon.ehealthcareproject.repository.ProductRepository;
import com.hackathon.ehealthcareproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    /**
     * create cart, fetch product from product repository, transform the product into a cartItem
     * add this cartItem to the cart.
     * if the cartItem already exist, then update the count of the cartItem by one
     */


    @Override
    public void addCartItemToCart(Long userId, Long productId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //find the cart associated with user
        Cart cart = user.getCart();

        //checking if the cart already contains the product to be added
        CartItem cartItem = cart.getCartItemList().stream().filter(item -> item.getProductEntity().getId().equals(productEntity.getId())).findFirst().orElse(null);


        if (cartItem != null){
            //meaning the cartItem is already present in the cart and we are incrementing the count of the cartItem by one
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            CartItem newCartItem = CartItem.builder().quantity(1).build();
            cart.getCartItemList().add(newCartItem);
        }

        cartRepository.save(cart);
    }

    @Override
    public void removeCartItemFromCart(Long userId, Long productId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Cart cart = user.getCart();
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        cart.getCartItemList().removeIf(cartItem -> cartItem.getProductEntity().getId().equals(productEntity.getId()));
        cartRepository.save(cart);
    }

    @Override
    public void clearAllItemsFromCart(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Cart cart = user.getCart();
        cart.getCartItemList().clear();
        cartRepository.save(cart);
    }
}
