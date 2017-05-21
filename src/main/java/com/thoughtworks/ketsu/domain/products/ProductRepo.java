package com.thoughtworks.ketsu.domain.products;

import java.util.List;
import java.util.Optional;

/**
 * Created by pzzheng on 5/21/17.
 */
public interface ProductRepo {
    Product save(Product product);

    Optional<Product> findById(String prodId);

    List<Product> findAll();
}
