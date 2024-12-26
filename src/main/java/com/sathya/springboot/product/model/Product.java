package com.sathya.springboot.product.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
	@NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @NotBlank(message = "Made in field cannot be blank")
    private String madein;

    @Positive(message = "Price must be greater than zero")
    private double price;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @DecimalMax(value = "100.0", message = "Discount rate cannot exceed 100")
    private int discountrate;


}
