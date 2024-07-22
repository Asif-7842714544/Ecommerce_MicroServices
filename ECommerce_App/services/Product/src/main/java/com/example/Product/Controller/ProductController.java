package com.example.Product.Controller;

import com.example.Product.DTO.ProductResponse;
import com.example.Product.DTO.ProductPurchaseRequest;
import com.example.Product.DTO.ProductPurchaseResponse;
import com.example.Product.DTO.ProductRequest;
import com.example.Product.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        log.info("inside ProductController - createProduct method");
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request) {
        return ResponseEntity.ok(productService.purchaseProduct(request));
    }

    @GetMapping("/{productId}")
public ResponseEntity<ProductResponse> findById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

}
