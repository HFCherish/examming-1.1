package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.products.Product;
import com.thoughtworks.ketsu.domain.products.ProductRepo;
import com.thoughtworks.ketsu.domain.user.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TestHelper {

    public static Map<String, Object> userJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "pzzheng");
        }};
    }

    public static User userWithDefaultInfo() {
        return new User("pzzheng");
    }

    public static User prepareUserWithDefaultInfo(UserRepo userRepo) {
        return userRepo.save(userWithDefaultInfo());
    }


    public static Map<String, Object> productJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "shoe");
            put("description", "shoe desc");
            put("price", 1.2);
        }};
    }

    public static Product productWithDefaultInfo() {
        return new Product("shoe", "shoe desc", 1.2);
    }

    public static Product prepareProductWithDefaultInfo(ProductRepo productRepo) {
        return productRepo.save(productWithDefaultInfo());
    }


    public static Map<String, Object> orderJsonForTest(String prodId) {
        return new HashMap<String, Object>() {{
            put("name", "pzzheng");
            put("address", "beijing");
            put("phone", "12132");
            put("order_items", Arrays.asList(
                    new HashMap(){{
                        put("product_id", prodId);
                        put("quantity", 2);
                    }}
            ));
        }};
    }

    public static Order orderWithDefaultInfo(Product... products) {
        return new Order(new Contact("pzzheng", "beijing", "12132"),
                Arrays.asList(products).stream().map(
                        product -> new OrderItem(product.getId().id(), 1)
                ).collect(Collectors.toList()));
    }

    public static Order prepareOrderWithDefaultInfo(User user, ProductRepo productRepo) {
        return user.buildOrder(orderWithDefaultInfo(prepareProductWithDefaultInfo(productRepo), prepareProductWithDefaultInfo(productRepo)));
    }


    public static Map<String, Object> paymentJsonForTest() {
        return new HashMap<String, Object>() {{
            put("pay_type", PayType.CASH);
            put("amount", 100);
        }};
    }
}
