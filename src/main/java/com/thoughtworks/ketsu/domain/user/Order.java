package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.EntityId;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pzzheng on 5/21/17.
 */
public class Order implements Record{

    private EntityId id;
    private EntityId userId;
    private Contact contact;
    private List<OrderItem> orderItems;
    private Date createdAt;

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
        return new HashMap() {{
            put("name", contact.getName());
            put("phone", contact.getPhone());
            put("address", contact.getAddress());
            put("created_at", createdAt);
            put("total_price", getTotalPrice());
            put("uri", routes.orderUri(userId.id(), id.id()));
        }};
    }

    public double getTotalPrice() {
        return orderItems.stream()
                .map(orderItem -> orderItem.calPrice())
                .reduce(0.0, (a,b) -> a + b);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> res = toRefJson(routes);
        res.put("order_items", orderItems.stream().map(orderItem -> orderItem.toJson(routes)));
        return res;
    }
}
