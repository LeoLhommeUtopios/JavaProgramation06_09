package org.example.service;

import org.example.entity.product.Product;
import org.example.entity.product.ProductCloth;
import org.example.entity.product.ProductElectronic;
import org.example.entity.product.ProductFood;
import org.example.repository.ProductRepository;
import org.example.utils.Categories;
import org.example.utils.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public Product createProductCloth(String description, Categories categories,String size,float price,int stock){
        Product product = ProductCloth.builder()
                .description(description)
                .price(price)
                .stock(stock)
                .size(size)
                .categories(categories)
                .build();

        return productRepository.create(product);
    }
    public Product createProductFood(String description, float price, int stock, LocalDate dateExpiry){
        Product product = ProductFood.builder()
                .description(description)
                .price(price)
                .stock(stock)
                .dateExpiry(dateExpiry)
                .build();

        return productRepository.create(product);
    }

    public Product createElectronic(String description, float price, int stock, int batteryDuration){
        Product product = ProductElectronic.builder()
                .description(description)
                .price(price)
                .stock(stock)
                .batteryDuration(batteryDuration)
                .build();

        return productRepository.create(product);
    }


    public Product updateProductCloth(int id,String description, Categories categories,String size,float price,int quantity){

        Product product = productRepository.findById(id);

        if(product instanceof ProductCloth productCloth){
            productCloth.setDescription(description);
            productCloth.setPrice(price);
            productCloth.setStock(quantity);
            productCloth.setCategories(categories);
            productCloth.setSize(size);

            return productRepository.update(product);
        }else {
            System.out.println("not a product cloth");
            return null;
        }
    }

    public Product updateProductFood (int id, String description,float price,int quantity,LocalDate expiryDate){
        Product product = productRepository.findById(id);

        if(product instanceof ProductFood productFood){
            productFood.setDescription(description);
            productFood.setPrice(price);
            productFood.setStock(quantity);
            productFood.setDateExpiry(expiryDate);

            return productRepository.update(product);
        }else {
            System.out.println("not a product cloth");
            return null;
        }
    }

    public Product updateProductElectronic (int id, String description,float price,int quantity,int batteryDuration){
        Product product = productRepository.findById(id);

        if(product instanceof ProductElectronic productElectronic){
            productElectronic.setDescription(description);
            productElectronic.setPrice(price);
            productElectronic.setStock(quantity);
            productElectronic.setBatteryDuration(batteryDuration);

            return productRepository.update(product);
        }else {
            System.out.println("not a product cloth");
            return null;
        }
    }

    public Product update (int id,int stock){
        Product product = productRepository.findById(id);
        product.setStock(stock);
        return productRepository.update(product);
    }

    public boolean delete (int id){
        Product product = productRepository.findById(id);
        if(product != null){
            return productRepository.delete(product);
        }
        return false;
    }

    public Product findById (int id){
        Product product = productRepository.findById(id);
        if(product != null){
            return product;
        }
        throw new NotFoundException();
    }

    public List<Product> findAll (){
        return productRepository.findAll();
    }
}
