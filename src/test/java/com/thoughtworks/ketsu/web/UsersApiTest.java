package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.UserRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Map;

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

//    @Test
//    public void should_400_if_id_not_valid() throws Exception {
//        Map<String, Object> userInfo = new HashMap<String, Object>() {{
//            put("email", "scxu@thoughtworks.com");
//            put("name", "Xu Shanchuan");
//            put("role", "Dev");
//        }};
//
//        final Response post = post("/users", userInfo);
//        assertThat(post.getStatus(), is(400));
//    }

//    @Test
//    public void should_get_user_by_id() throws Exception {
//        final User user = TestHelper.userForTest("123", "scxu", UserRole.DEV);
//        userRepo.save(user);
//
//        final Response response = get("/users/" + user.getUserId().id());
//        assertThat(response.getStatus(), is(200));
//        final Map userMap = response.readEntity(Map.class);
//        assertThat(userMap.get("id"), is(user.getUserId().id()));
//        assertThat(userMap.get("name"), is(user.getName()));
//        assertThat(userMap.get("email"), is(user.getEmail()));
//        List urls = (List) userMap.get("links");
//        assertThat(urls.size(), is(1));
//        assertThat(canFindLink(urls, "self", "/users/123"), is(true));
//    }
//
//    @Test
//    public void should_404_if_user_not_exist() throws Exception {
//        final Response response = get("/users/abc");
//        assertThat(response.getStatus(), is(404));
//    }
}
