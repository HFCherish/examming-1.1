package com.thoughtworks.ketsu.domain.products;

import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.productWithDefaultInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/21/17.
 */
@RunWith(DatabaseTestRunner.class)
public class ProductRepoTest {
    @Inject ProductRepo productRepo;

    @Test
    public void should_create_and_get_that_product_later() {
        Product saveProd = productRepo.save(productWithDefaultInfo());

        Optional<Product> byId = productRepo.findById(saveProd.getId().id());

        assertThat(byId.isPresent(), is(true));
        assertThat(saveProd.getId(), is(byId.get().getId()));
    }
}