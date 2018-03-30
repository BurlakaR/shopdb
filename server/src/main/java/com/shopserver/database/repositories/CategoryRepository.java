package com.shopserver.database.repositories;

import com.shopserver.database.objects.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryRepository extends MongoRepository<Category, Long> {
    List<Category> findAll();
    void deleteAllByUrl(String url);
}