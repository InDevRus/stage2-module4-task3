package com.mjc.stage2.impl;


import com.mjc.stage2.FilteringStrategy;
import com.mjc.stage2.Product;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ManufactureFilteringStrategy implements FilteringStrategy {
    @NonNull
    private String manufacture;

    @Override
    public boolean filter(Product product) {
        return String.CASE_INSENSITIVE_ORDER.compare(product.getManufacture(), manufacture) == 0;
    }
}
