package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.Contact;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.OrderItem;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

/**
 * Created by pzzheng on 5/21/17.
 */
public class OrdersApi {

    private User user;

    public OrdersApi(User user) {
        this.user = user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object> orderInfo,
                           @Context Routes routes) {

        validate(orderInfo, all(
                fieldNotEmpty("name"),
                fieldNotEmpty("address"),
                fieldNotEmpty("phone"),
                collectionNotEmpty("order_items")
        ));

        for(Map orderItem: (List<Map>) orderInfo.get("order_items")) {
            validate(orderItem, all(
                    fieldNotEmpty("product_id"),
                    fieldNotEmpty("quantity")
            ));
        }

        Contact contact = new Contact(orderInfo.get("name").toString(),
                orderInfo.get("address").toString(),
                orderInfo.get("phone").toString());
        Order order = user.buildOrder(new Order(contact,
                ((List<Map<String, Object>>) orderInfo.get("order_items")).stream()
                        .map(order_item -> new OrderItem(order_item.get("product_id").toString(),
                                (int) order_item.get("quantity")))
                        .collect(Collectors.toList())));
        return Response.created(routes.orderUri(user.getId().id(), order.getId().id())).build();
    }
}
