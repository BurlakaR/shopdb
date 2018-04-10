package com.shopserver.database.repositories;

import com.shopserver.database.objects.Authorize;
import com.shopserver.database.objects.Sell;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellRepository extends MongoRepository<Sell, Long> {
    void deleteAllByLogin(String s);
}