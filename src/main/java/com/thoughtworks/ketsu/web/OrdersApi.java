package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

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
        return Response.created(routes.orderUri(user.getId().id(), "")).build();
    }
}
