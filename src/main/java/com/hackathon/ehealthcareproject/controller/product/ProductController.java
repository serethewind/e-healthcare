package com.hackathon.ehealthcareproject.controller.product;

import com.hackathon.ehealthcareproject.dto.product.ProductRequest;
import com.hackathon.ehealthcareproject.dto.product.ProductResponse;
import com.hackathon.ehealthcareproject.entity.ProductEntity;
import com.hackathon.ehealthcareproject.service.product.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    ProductResponse addProduct(@RequestBody ProductRequest productRequest){
        return productService.addProduct(productRequest);
    }
    @GetMapping("/allProducts")
    List<ProductEntity> listAllProducts(){
        return productService.listAllProducts();
    }
    @GetMapping("/{productName}")
    ProductResponse viewSingleProduct(@PathVariable("productName") String productName){
        return productService.viewSingleProduct(productName);
    }
    @PutMapping("/update/products")
    ProductResponse updateProducts(@RequestBody ProductRequest productRequest){
        return productService.updateProducts(productRequest);
    }
    @DeleteMapping("/delete")
    ProductResponse deleteProduct(@RequestBody Long id){
        return productService.deleteProduct(id);
    }
}
