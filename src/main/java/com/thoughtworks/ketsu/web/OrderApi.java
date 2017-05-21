package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.PayType;
import com.thoughtworks.ketsu.domain.user.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

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
        validate(payInfo, all(
                fieldNotEmpty("pay_type"),
                fieldIsEnum("pay_type", PayType.class),
                fieldNotEmpty("amount")
        ));

        order.pay(new Payment(PayType.valueOf(payInfo.get("pay_type").toString()), (double)payInfo.get("amount")));
        return Response.created(URI.create("")).build();
    }


    @Path("payment")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Payment getPayment() {
        return order.getPayment().orElseThrow(() -> new NotFoundException("the order is not payed yet"));
    }
}
