package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.userWithDefaultInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/21/17.
 */
@RunWith(DatabaseTestRunner.class)
public class UserRepoTest {

    @Inject
    UserRepo userRepo;

    @Test
    public void should_create_and_get_that_user() {
        User saveUser = userRepo.save(userWithDefaultInfo());

        Optional<User> byId = userRepo.findById(saveUser.getId().id());

        assertThat(byId.isPresent(), is(true));
        assertThat(byId.get().getId(), is(saveUser.getId()));
    }
}