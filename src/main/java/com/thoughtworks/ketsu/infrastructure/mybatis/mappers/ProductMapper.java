package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.products.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pzzheng on 5/21/17.
 */
public interface ProductMapper {
    void save(@Param("product") Product product);

    Product findById(@Param("pid") String id);

    List<Product> findAll();
}
