package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.prepareUserWithDefaultInfo;
import static com.thoughtworks.ketsu.support.TestHelper.userJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {
    @Inject
    UserRepo userRepo;

    public String getUsersUrl() {
        return "/users";
    }

    @Test
    public void should_import_user_success() throws Exception {
        Map<String, Object> userInfo = userJsonForTest();

        final Response post = post(getUsersUrl(), userInfo);
        assertThat(post.getStatus(), is(201));
    }

    @Test
    public void should_400_if_create_with_invalid_info() throws Exception {
        Map<String, Object> invalidUser = userJsonForTest();
        invalidUser.remove("name");

        final Response post = post(getUsersUrl(), invalidUser);
        assertThat(post.getStatus(), is(400));
    }

    @Test
    public void should_get_user_by_id() throws Exception {
        User saveUser = prepareUserWithDefaultInfo(userRepo);

        String oneUrl = getUsersUrl() + "/" + saveUser.getId().id();
        final Response response = get(oneUrl);
        assertThat(response.getStatus(), is(200));
        final Map userMap = response.readEntity(Map.class);
        assertThat(userMap.get("id"), is(saveUser.getId().id()));
        assertThat(userMap.get("name"), is(saveUser.getName()));
        assertThat(userMap.get("uri").toString().contains(oneUrl), is(true));
    }

    @Test
    public void should_404_if_user_not_exist() throws Exception {
        final Response response = get("/users/abc");
        assertThat(response.getStatus(), is(404));
    }
}
