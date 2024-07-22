package com.example.Product.Service;

import com.example.Product.DTO.*;
import com.example.Product.Entity.Product;
import com.example.Product.Exception.ProductPurchaseException;
import com.example.Product.Repo.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo repo;
    private final ProductMapper mapper;


    public Integer createProduct(ProductRequest productRequest) {
        Product product = mapper.toProduct(productRequest);
        return repo.save(product).getId();
    }


    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        log.info("inside purchaseProduct method");
        List<Integer> productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        log.info("productIds: {}", productIds);

        List<Product> storedProduct = repo.findAllByIdInOrderById(productIds);
        log.info("storedProduct: {}", storedProduct.toString());
        if (productIds.size() != storedProduct.size()) {
            throw new ProductPurchaseException("one or more products does not exists");
        }
        List<ProductPurchaseRequest> storesRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
           log.info("storesRequest : {}", storesRequest.toString());

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();
        for (int i = 0; i < storedProduct.size(); i++) {
            Product product = storedProduct.get(i);
            ProductPurchaseRequest productPurchaseRequest = storesRequest.get(i);
            log.info("productPurchaseRequest quantity: {}", productPurchaseRequest.quantity());
            log.info("product availableQuantity: {}", product.getAvailableQuantity());
            if (product.getAvailableQuantity() < productPurchaseRequest.quantity()) {
                log.error("Insufficient quantity");
                throw new ProductPurchaseException("Insufficient Stock");
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productPurchaseRequest.quantity();
            log.debug("new availableQuantity: {}", newAvailableQuantity);
            product.setAvailableQuantity(newAvailableQuantity);
            repo.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productPurchaseRequest.quantity()));
        }


        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repo.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the id: " + productId));
    }

    public List<ProductResponse> findAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }
}
