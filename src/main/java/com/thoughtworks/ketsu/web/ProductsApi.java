package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.products.Product;
import com.thoughtworks.ketsu.domain.products.ProductRepo;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

/**
 * Created by pzzheng on 5/21/17.
 */
@Path("/products")
public class ProductsApi {



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object> prodBody,
                           @Context ProductRepo productRepo,
                           @Context Routes routes) {
        validate(prodBody, all(fieldNotEmpty("name"),
                fieldNotEmpty("description"),
                fieldNotEmpty("price")));

        Product saveProd = productRepo.save(new Product(prodBody.get("name").toString(),
                prodBody.get("description").toString(),
                (double) prodBody.get("price")));

        return Response.created(routes.productUrl(saveProd.getId().id())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll(@Context ProductRepo productRepo) {
        return productRepo.findAll();
    }

    @Path("{pid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product getOne(@PathParam("pid") String pid,
                          @Context ProductRepo productRepo) {
        return productRepo.findById(pid).orElseThrow(() -> new NotFoundException("products " + pid + " is not exist"));
    }
}
