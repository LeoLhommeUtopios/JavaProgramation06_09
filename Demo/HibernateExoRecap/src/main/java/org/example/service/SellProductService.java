package org.example.service;

import org.example.entity.product.Product;
import org.example.entity.Sell;
import org.example.entity.SellProduct;
import org.example.repository.ProductRepository;
import org.example.repository.SellProductRepository;
import org.example.repository.SellRepository;
import org.example.utils.exception.NotFoundException;

import java.util.List;

public class SellProductService {
    private final SellProductRepository sellProductRepository;

    private final SellRepository sellRepositoryl;

    private final ProductRepository productRepository;

    public SellProductService() {
        this.sellProductRepository = new SellProductRepository();
        this.sellRepositoryl = new SellRepository();
        this.productRepository = new ProductRepository();
    }

    public SellProduct create(int idProduct,int idSell,int quantity){
        Sell sell = sellRepositoryl.findById(idSell);
        Product product = productRepository.findById(idProduct);

        if(sell != null && product != null){
            SellProduct sellProduct = SellProduct.builder().product(product).sell(sell).quantity(quantity).build();
            return sellProductRepository.create(sellProduct);
        }

        throw new NotFoundException();
    }

    public Sell update(int id,int idProduct,int idSell,int quantity){

        SellProduct sellProduct = sellProductRepository.findById(id);

        if (sellProduct != null){
            Sell sell = sellRepositoryl.findById(idSell);
            Product product = productRepository.findById(idProduct);
            if(sell != null && product != null){
                sellProduct.setSell(sell);
                sellProduct.setProduct(product);
                sellProduct.setQuantity(quantity);
                sellProductRepository.update(sellProduct);
            }
        }
        throw new NotFoundException();
    }

    public boolean delete (int id){
        SellProduct sellProduct = sellProductRepository.findById(id);
        if(sellProduct != null){
            return sellProductRepository.delete(sellProduct);
        }
        return false;
    }

    public SellProduct findById (int id){
        SellProduct sellProduct = sellProductRepository.findById(id);
        if(sellProduct != null){
            return sellProduct;
        }
        throw new NotFoundException();
    }

    public List<SellProduct> findAll (){
        return sellProductRepository.findAll();
    }
}
