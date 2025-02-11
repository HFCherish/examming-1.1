package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.products.Product;
import com.thoughtworks.ketsu.domain.products.ProductRepo;
import com.thoughtworks.ketsu.domain.user.*;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.*;
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

    @Test
    public void should_400_when_input_incomplete() {
        Map<String, Object> incompleteInput = orderJsonForTest(product.getId().id());
        incompleteInput.remove("name");

        Response response = post(getOrdersUrl(user), incompleteInput);

        assertThat(response.getStatus(), is(400));
    }


    @Test
    public void should_400_when_product_not_exists() {
        Map<String, Object> withInvalidProductId = orderJsonForTest("invalid_product_id");

        Response response = post(getOrdersUrl(user), withInvalidProductId);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_200_when_get_all() {
        Order saveOrder = prepareOrderWithDefaultInfo(user, productRepo);

        Response response = get(getOrdersUrl(user));

        assertThat(response.getStatus(), is(200));
        List<Map> orders = response.readEntity(List.class);
        assertThat(orders.size(), is(1));
        Map<String, Object> order = orders.get(0);
        assertThat(order.get("total_price"), is(saveOrder.getTotalPrice()));
        assertThat(order.get("order_items"), is(nullValue()));
    }


    @Test
    public void should_200_when_get_one() {
        Order saveOrder = prepareOrderWithDefaultInfo(user, productRepo);

        Response response = get(getOrdersUrl(user) + "/" + saveOrder.getId().id());

        assertThat(response.getStatus(), is(200));
        Map<String, Object> order = response.readEntity(Map.class);
        assertThat(order.get("total_price"), is(saveOrder.getTotalPrice()));
        assertThat(order.get("order_items"), is(notNullValue()));
    }

    @Test
    public void should_404_when_get_one_not_exists() {
        Order saveOrder = prepareOrderWithDefaultInfo(user, productRepo);

        Response response = get(getOrdersUrl(user) + "/not_exists_id");

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_201_when_pay() {
        Order saveOrder = prepareOrderWithDefaultInfo(user, productRepo);

        Response response = post(getOrdersUrl(user) + "/" + saveOrder.getId().id() + "/payment", paymentJsonForTest());

        assertThat(response.getStatus(), is(201));
    }


    @Test
    public void should_400_when_pay_given_invalid_input() {
        Order saveOrder = prepareOrderWithDefaultInfo(user, productRepo);

        Map<String, Object> invalidInput = paymentJsonForTest();
        invalidInput.remove("amount");
        invalidInput.replace("pay_type", "badtype");

        Response response = post(getOrdersUrl(user) + "/" + saveOrder.getId().id() + "/payment", invalidInput);

        assertThat(response.getStatus(), is(400));
    }


    @Test
    public void should_200_when_get_payment() {
        Order saveOrder = prepareOrderWithDefaultInfo(user, productRepo);
        saveOrder.pay(new Payment(PayType.CASH, 100.0));

        Response response = get(getOrdersUrl(user) + "/" + saveOrder.getId().id() + "/payment");

        assertThat(response.getStatus(), is(200));
        Map<String, Object> payment = response.readEntity(Map.class);
        assertThat(payment.get("order_uri"), is(notNullValue()));
    }

    @Test
    public void should_404_when_get_payment_if_not_pay() {
        Order saveOrder = prepareOrderWithDefaultInfo(user, productRepo);

        Response response = get(getOrdersUrl(user) + "/" + saveOrder.getId().id() + "/payment");

        assertThat(response.getStatus(), is(404));
    }

}