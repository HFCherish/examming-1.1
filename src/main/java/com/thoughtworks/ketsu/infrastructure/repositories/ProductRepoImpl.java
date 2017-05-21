package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.products.Product;
import com.thoughtworks.ketsu.domain.products.ProductRepo;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by pzzheng on 5/21/17.
 */
public class ProductRepoImpl implements ProductRepo {
    @Inject
    ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        productMapper.save(product);
        return productMapper.findById(product.getId().id());
    }

    @Override
    public Optional<Product> findById(String prodId) {
        return Optional.ofNullable(productMapper.findById(prodId));
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }
}
