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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
    public void addCartItemToCart(CartRequestDto cartRequestDto) {
        UserEntity user = userRepository.findById(cartRequestDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProductEntity productEntity = productRepository.findById(cartRequestDto.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Cart newCart = new Cart();
        //find the cart associated with user
        Cart cart = cartRepository.findByUser(user).orElse(newCart);
        newCart.setUser(user);
        user.setCart(newCart);

        List<ProductEntity> listOfProducts = new ArrayList<>();
        listOfProducts.add(productEntity);

// Assuming ArrayList<ItemEntity> listA and ArrayList<ItemEntity> listB

        for (ProductEntity itemA : listOfProducts ) {
            boolean found = false;
            for (ProductEntity itemB : cart.getProductItems()) {
                if (itemA.getId().equals(itemB.getId())) {
                    found = true;
                    break;
                }
            }
            if (found) {
                // itemA is present in listB
                cart.setQuantity(cart.getQuantity() + 1);
            } else {
                cart.getProductItems().add(itemA);
                cart.setQuantity(1);
            }
        }

        cartRepository.save(cart);
    }

    @Override
    public void removeCartItemFromCart(CartRequestDto cartRequestDto) {


//        UserEntity user = userRepository.findById(cartRequestDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        Cart cart = user.getCart();
//        ProductEntity productEntity = productRepository.findById(cartRequestDto.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        cart.getCartItemList().removeIf(cartItem -> cartItem.getProductEntity().getId().equals(productEntity.getId()));
//        cartRepository.save(cart);
    }

    @Override
    public void clearAllItemsFromCart(Long userId) {
//        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        Cart cart = user.getCart();
//        cart.getCartItemList().clear();
//        cartRepository.save(cart);
    }
}
