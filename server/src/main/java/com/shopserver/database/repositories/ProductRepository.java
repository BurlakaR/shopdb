package com.shopserver.database.repositories;

import com.shopserver.database.objects.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, Long> {
    Product findByUrl(String url);
    void deleteAllByUrl(String url);
}
