package com.shopserver.database.objects;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
public class Authorize implements Serializable {
    @NonNull Client clientAutor;
    @NonNull String clientIp;
    @NonNull Basket basket;
}
