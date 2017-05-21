package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.products.Product;
import com.thoughtworks.ketsu.domain.products.ProductRepo;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static com.thoughtworks.ketsu.support.TestHelper.orderJsonForTest;
import static com.thoughtworks.ketsu.support.TestHelper.prepareProductWithDefaultInfo;
import static com.thoughtworks.ketsu.support.TestHelper.prepareUserWithDefaultInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/21/17.
 */
@RunWith(ApiTestRunner.class)
public class OrdersApiTest extends ApiSupport {

    @Inject
    UserRepo userRepo;

    @Inject
    ProductRepo productRepo;

    private User user;
    private Product product;

    public String getOrdersUrl(User user) {
        return "/users/" + user.getId().id() + "/orders";
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = prepareUserWithDefaultInfo(userRepo);
        product = prepareProductWithDefaultInfo(productRepo);
    }

    @Test
    public void should_201_when_create_order() {
        Response response = post(getOrdersUrl(user), orderJsonForTest(product.getId().id()));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString().matches(".*/[-a-zA-Z\\d]+$"), is(true));
    }
}