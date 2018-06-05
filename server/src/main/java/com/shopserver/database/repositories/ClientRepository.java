package com.shopserver.database.repositories;

import com.shopserver.database.objects.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository <Client, Long> {
    Client findByLogin(String login);
    void deleteAllByLogin(String login);
}
