package org.example.service;

import org.example.entity.Customer;
import org.example.entity.Sell;
import org.example.entity.SellProduct;
import org.example.repository.SellRepository;
import org.example.utils.exception.NotFoundException;
import org.example.utils.SellStatus;

import java.time.LocalDate;
import java.util.List;

public class SellService {
    private final SellRepository sellRepository;
    private final ProductService productService;

    public SellService() {
        this.sellRepository = new SellRepository();
        this.productService = new ProductService();
    }

    public Sell create(Customer customer){
        Sell sell = Sell.builder()
                .customer(customer)
                .sellStatus(SellStatus.ONGOING)
                .sellDate(LocalDate.now()).build();

        return sellRepository.create(sell);
    }

    public Sell update(int id,Customer customer){

        Sell sell = sellRepository.findById(id);

        if (sell != null && sell.getSellStatus().equals(SellStatus.ONGOING) ){
            sell.setCustomer(customer);
            return sellRepository.update(sell);
        }
        throw new NotFoundException();
    }

    public boolean delete (int id){
        Sell sell = sellRepository.findById(id);
        if(sell != null && sell.getSellStatus().equals(SellStatus.ONGOING)){
            sell.setSellStatus(SellStatus.CANCELED);
            sellRepository.update(sell);
            return true;
        }
        return false;
    }

    public Sell findById (int id){
        Sell sell = sellRepository.findById(id);
        if(sell != null){
            return sell;
        }
        throw new NotFoundException();
    }

    public List<Sell> findAll (){
        return sellRepository.findAll();
    }

    public Sell validateSell (int id){
        Sell sell = sellRepository.findById(id);
        if(sell != null && sell.getSellStatus().equals(SellStatus.ONGOING)){
            sell.setSellStatus(SellStatus.FINALIZED);
            List<SellProduct> sellProducts = sell.getSellProducts();
            for (SellProduct sellProduct : sellProducts){
                productService.update(sellProduct.getProduct().getId(),sellProduct.getProduct().getStock() - sellProduct.getQuantity());
            }
            sellRepository.update(sell);
            return sell;
        }
        throw new NotFoundException();
    }

    public List<Sell> getByDate (LocalDate dateStart, LocalDate dateStop){
        return sellRepository.findByDate(dateStart,dateStop);
    }
}
