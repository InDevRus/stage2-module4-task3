package com.mjc.stage2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShopStock implements ProductFilterer {
    private final List<Product> productList;

    public ShopStock(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public List<Product> executeFilteringStrategy(FilteringStrategy strategy) {
        return productList.stream().filter(strategy::filter)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
