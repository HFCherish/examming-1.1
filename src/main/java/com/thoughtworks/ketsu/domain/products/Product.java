package com.thoughtworks.ketsu.domain.products;

import com.thoughtworks.ketsu.domain.EntityId;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pzzheng on 5/21/17.
 */
public class Product implements Record{
    private String name;
    private String description;
    private double price;
    private EntityId id;

    private Product() {}

    public Product(String name, String description, double price) {
        this.id = new EntityId(IdGenerator.next());
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public EntityId getId() {
        return id;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap(){{
            put("name", name);
            put("description", description);
            put("price", price);
            put("id", id.id());
            put("uri", routes.productUrl(id.id()));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
