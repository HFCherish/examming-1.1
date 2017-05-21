package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.EntityId;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.List;
import java.util.Map;

/**
 * Created by pzzheng on 5/21/17.
 */
public class Order implements Record{

    private EntityId id;
    private Contact contact;
    private List<OrderItem> orderItems;

    private Order() {}

    public Order(Contact contact, List<OrderItem> orderItems) {
        id = new EntityId(IdGenerator.next());
        this.contact = contact;
        this.orderItems = orderItems;
    }

    public Contact getContact() {
        return contact;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public EntityId getId() {
        return id;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return null;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return null;
    }
}
