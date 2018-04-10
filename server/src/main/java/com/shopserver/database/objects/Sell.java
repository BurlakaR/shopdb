package com.shopserver.database.objects;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "sellList")
@Data
public class Sell {
    @NonNull String clientAutor;
    @NonNull String clientIp;
    @NonNull Basket basket;
}