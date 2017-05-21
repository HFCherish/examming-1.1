package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.EntityId;
import com.thoughtworks.ketsu.util.IdGenerator;

import java.util.List;

/**
 * Created by pzzheng on 5/21/17.
 */
public class Order {

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
}
