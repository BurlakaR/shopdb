package com.shopserver.database.objects;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "products")
@Data
public class Product implements Serializable{

    @NonNull private String url;
    @NonNull private String img;
    @NonNull private String category;
    @NonNull private List<String> subcategoryList;
    @NonNull private String name;
    @NonNull private int price;
    @NonNull private List<Property> propertyList;
}