package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.PayType;
import com.thoughtworks.ketsu.domain.user.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

/**
 * Created by pzzheng on 5/21/17.
 */
public class OrderApi {

    private Order order;

    public OrderApi(Order order) {
        this.order = order;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOne() {
        return order;
    }

    @Path("payment")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(Map<String, Object> payInfo) {
        order.pay(new Payment(PayType.valueOf(payInfo.get("pay_type").toString()), (double)payInfo.get("amount")));
        return Response.created(URI.create("")).build();
    }
}
