package com.mjc.stage2;

import java.util.List;

public interface ProductFilterer {
    List<Product> executeFilteringStrategy(FilteringStrategy strategy);
}
