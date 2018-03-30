package com.shopserver.database.repositories;

import com.shopserver.database.objects.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, Long> {
    Product findByUrl(String url);
    List<Product> findAllByCategory(String category);
    void deleteAllByUrl(String url);
}
