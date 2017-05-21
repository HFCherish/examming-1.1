package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.products.Product;
import com.thoughtworks.ketsu.domain.products.ProductRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.prepareProductWithDefaultInfo;
import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/21/17.
 */
@RunWith(ApiTestRunner.class)
public class ProductsApiTest extends ApiSupport {

    @Inject
    ProductRepo productRepo;

    public String getProductsUrl() {
        return "/products";
    }

    @Test
    public void should_return_201_when_create_product() {
        Response response = post(getProductsUrl(), productJsonForTest());

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString().matches(".*/products/[-a-zA-Z\\d]+$"), is(true));
    }

    @Test
    public void should_400_when_input_incomplete() {
        Map<String, Object> badProductJsonForTest = productJsonForTest();
        badProductJsonForTest.remove("name");

        Response response = post(getProductsUrl(), badProductJsonForTest);

        assertThat(response.getStatus(), is(400));
    }


    @Test
    public void should_get_all_products() {
        prepareProductWithDefaultInfo(productRepo);
        Response response = get(getProductsUrl());

        assertThat(response.getStatus(), is(200));
        List<Map> res = response.readEntity(List.class);
        assertThat(res.size(), is(1));
    }

    @Test
    public void should_get_one_product() {
        Product savedProd = prepareProductWithDefaultInfo(productRepo);
        String one_uri = getProductsUrl() + "/" + savedProd.getId().id();
        Response response = get(one_uri);

        assertThat(response.getStatus(), is(200));
        Map<String, Object> res = response.readEntity(Map.class);
        assertThat(res.get("id"), is(savedProd.getId().id()));
        assertThat(res.get("name"), is(savedProd.getName()));
        assertThat(res.get("description"), is(savedProd.getDescription()));
        assertThat(res.get("price"), is(savedProd.getPrice()));
        assertThat(res.get("uri").toString().contains(one_uri), is(true));
    }


    @Test
    public void should_404_when_get_one_product_while_not_exists() {
        Product savedProd = prepareProductWithDefaultInfo(productRepo);
        Response response = get(getProductsUrl() + "/1" + savedProd.getId().id());

        assertThat(response.getStatus(), is(404));
    }
}