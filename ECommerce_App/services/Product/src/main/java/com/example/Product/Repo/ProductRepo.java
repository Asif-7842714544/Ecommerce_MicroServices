package com.example.Product.Repo;

import com.example.Product.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
