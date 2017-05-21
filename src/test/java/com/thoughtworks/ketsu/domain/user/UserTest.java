package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.products.ProductRepo;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/21/17.
 */
@RunWith(DatabaseTestRunner.class)
public class UserTest {


    @Inject
    UserRepo userRepo;

    @Inject
    ProductRepo productRepo;

    private User user;


    @Before
    public void setUp() {
        user = prepareUserWithDefaultInfo(userRepo);
    }

    @Test
    public void should_create_order_and_get_that_one_later() {
        Order saveOrder = user.buildOrder(orderWithDefaultInfo(prepareProductWithDefaultInfo(productRepo),
                prepareProductWithDefaultInfo(productRepo)));

        Optional<Order> orderById = user.findOrderById(saveOrder.getId().id());

        assertThat(orderById.isPresent(), is(true));
        assertThat(orderById.get().getId(), is(saveOrder.getId()));
    }
}