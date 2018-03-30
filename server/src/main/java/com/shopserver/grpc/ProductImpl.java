package com.shopserver.grpc;

import com.google.protobuf.ByteString;
import com.shop.*;

import com.shopserver.database.objects.Category;
import com.shopserver.database.objects.Product;
import com.shopserver.database.objects.Property;
import com.shopserver.database.repositories.CategoryRepository;
import com.shopserver.database.repositories.ProductRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableMongoRepositories(basePackages = "com.shopserver.database.repositories")
public  class ProductImpl  extends ProductServiceGrpc.ProductServiceImplBase  {
    @Autowired
    private ProductRepository dbProd;

    @Autowired
    private CategoryRepository dbCateg;

    @PostConstruct
    public void init(){
        //magic
        test();
    }

    public void test(){
        dbProd.findByUrl("p0");
        dbCateg.findAll();
        List<String>list=new ArrayList<String >();
        Category testCategory=new Category("name", list);
        dbCateg.save(testCategory);
        dbCateg.deleteAllByUrl("name");
        List<Property> listp = new ArrayList<Property>();
        Product product = new Product("1","1","1",list, "1", 5, listp);
        dbProd.save(product);
        dbProd.deleteAllByUrl("1");
    }

    public static byte[] convertToBytes(Serializable object) {
        return SerializationUtils.serialize(object);
    }

    public static Object convertFromBytes(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }



    @Override
    public void takeProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

        Product buf =dbProd.findByUrl(request.getUrl());
        byte[] mas=convertToBytes(buf);
        ByteString byteString = ByteString.copyFrom(mas);
        ProductResponse response =  ProductResponse.newBuilder().setProduct(byteString).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void takeProductList(ProductListRequest request, StreamObserver<ProductResponse> responseObserver) {
        List<Product> productList = dbProd.findAllByCategory(request.getUrl());
        for(int i=0;i<productList.size();i++){
            byte[] mas=convertToBytes(productList.get(i));
            ByteString byteString = ByteString.copyFrom(mas);
            ProductResponse response =  ProductResponse.newBuilder().setProduct(byteString).build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void takeCategories(CategoryRequest request, StreamObserver<CategoryResponse> responseObserver) {
        List<Category> categoryList = dbCateg.findAll();
        for(int i=0;i<categoryList.size();i++){
            byte [] mas=convertToBytes(categoryList.get(i));
            ByteString byteString=ByteString.copyFrom(mas);
            CategoryResponse response =  CategoryResponse.newBuilder().setCategory(byteString).build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void saveCategory(SaveCategoryRequest request, StreamObserver<SaveResponse> responseObserver ){
        ByteString byteString=request.getCategory();
        byte [] mas = byteString.toByteArray();
        Object object= convertFromBytes(mas);
        Category category = (Category) object;
        dbCateg.save(category);
        SaveResponse response =  SaveResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void saveProduct(SaveProductRequest request, StreamObserver<SaveResponse> responseObserver){
        ByteString byteString=request.getProduct();
        byte [] mas = byteString.toByteArray();
        Object object= convertFromBytes(mas);
        Product product = (Product) object;
        dbProd.save(product);
        SaveResponse response =  SaveResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}

