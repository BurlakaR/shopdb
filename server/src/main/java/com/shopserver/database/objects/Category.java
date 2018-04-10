package com.shopserver.database.objects;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "categories")
@Data
public class Category implements Serializable{

    @NonNull private String url;
    @NonNull private List<String> subcategories;

}
