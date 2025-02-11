package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.EntityId;
import com.thoughtworks.ketsu.domain.products.Product;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by pzzheng on 5/21/17.
 */
public class User implements Record {
    protected EntityId id;
    protected String name;

    private User() {}

    public User(String name) {
        this.id = new EntityId(IdGenerator.next());
        this.name = name;
    }

    public EntityId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap(){{
            put("id", id.id());
            put("name", name);
            put("uri", routes.userUrl(id.id()));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    @Inject
    OrderMapper orderMapper;
    public Order buildOrder(Order order) {
        checkOrderProductId(order);
        orderMapper.save(order, id.id());
        return orderMapper.findById(order.getId().id());
    }

    @Inject
    ProductMapper productMapper;
    private void checkOrderProductId(Order order) {
        order.getOrderItems().stream().forEach(
            orderItem -> {
                Product product = productMapper.findById(orderItem.getProductId());
                if(product == null)
                    throw new IllegalArgumentException("productId " + orderItem.getProductId() + " is invalid");
            }
        );

    }

    public Optional<Order> getOrder(String oid) {
        return Optional.ofNullable(orderMapper.findById(oid));
    }

    public List<Order> getOrders() {
        return orderMapper.findAllOf(id.id());
    }

}
