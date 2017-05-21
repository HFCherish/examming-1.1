package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.products.Product;
import com.thoughtworks.ketsu.domain.products.ProductRepo;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepo;

import java.util.HashMap;
import java.util.Map;

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
            put("order_items", new HashMap(){{
                put("product_id", prodId);
                put("quantity", 2);
            }});
        }};
    }
}
