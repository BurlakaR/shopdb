package com.shopserver.database.objects;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "clients")
@Data
public class Client implements Serializable {
    @NonNull String fio;
    @NonNull String login;
    @NonNull String password;
    @NonNull String email;
    @NonNull Date date;

}
