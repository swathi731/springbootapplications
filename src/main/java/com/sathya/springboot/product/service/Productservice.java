package com.sathya.springboot.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.springboot.product.entity.Productentity;
import com.sathya.springboot.product.model.Product;
import com.sathya.springboot.product.repository.Productrepository;

@Service
public class Productservice {

    @Autowired
    private Productrepository productrepository;

    public void saveProductDetails(Product productmodel) {
        double stockValue = productmodel.getPrice() * productmodel.getQuantity();
        double discountPrice = calculateDiscountPrice(productmodel.getPrice(), productmodel.getDiscountrate());
        double offerPrice = productmodel.getPrice() - discountPrice;
        double taxPrice = calculateTax(productmodel.getPrice());
        double finalPrice = offerPrice + taxPrice;

        Productentity productEntity = new Productentity();
        productEntity.setName(productmodel.getName());
        productEntity.setBrand(productmodel.getBrand());
        productEntity.setMadein(productmodel.getMadein());
        productEntity.setPrice(productmodel.getPrice());
        productEntity.setQuantity(productmodel.getQuantity());
        productEntity.setDiscountrate(productmodel.getDiscountrate());
        productEntity.setDiscountprice(discountPrice);
        productEntity.setOfferprice(offerPrice);
        productEntity.setTaxrate(taxPrice);
        productEntity.setFinalprice(finalPrice);
        productEntity.setStockvalue(stockValue);

        System.out.println("Saving Product Details:");
        System.out.println("Name: " + productmodel.getName());
        System.out.println("Price: " + productmodel.getPrice());
        System.out.println("Discount Rate: " + productmodel.getDiscountrate());
        System.out.println("Discount Price: " + discountPrice);

        productrepository.save(productEntity);
    }

    public Productentity searchByid(Long id) {
        Optional<Productentity> optionalData = productrepository.findById(id);
        return optionalData.orElse(null); 
    }

    public List<Productentity> getallproduct() {
        return productrepository.findAll();
    }

    public void deletebyid(Long id) {
        productrepository.deleteById(id);
    }

    public void updateProductDetails(Productentity product) {
        Optional<Productentity> existingProductOpt = productrepository.findById(product.getId());
        if (existingProductOpt.isPresent()) {
            Productentity existingProduct = existingProductOpt.get();

            double discountPrice = calculateDiscountPrice(product.getPrice(),product.getDiscountprice());
            double offerPrice = product.getPrice() - discountPrice;
            double finalPrice = offerPrice + calculateTax(product.getPrice());

            existingProduct.setName(product.getName());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setMadein(product.getMadein());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setDiscountrate(product.getDiscountrate());
            existingProduct.setDiscountprice(discountPrice);
            existingProduct.setOfferprice(offerPrice);
            existingProduct.setTaxrate(calculateTax(product.getPrice()));
            existingProduct.setFinalprice(finalPrice);
            existingProduct.setStockvalue(product.getPrice() * product.getQuantity());

            System.out.println("Updating Product Details:");
            System.out.println("ID: " + product.getId());
            System.out.println("Discount Rate: " + product.getDiscountrate());
            System.out.println("Discount Price: " + discountPrice);

            productrepository.save(existingProduct);
        }
    }

     double calculateDiscountPrice(double price, double discountRate) {
        return price * discountRate / 100;
    }

    double calculateTax(double price) {
        double taxRate = 10.0; 
        return price * taxRate / 100;
    }
}
