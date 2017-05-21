package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepo;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.fieldNotEmpty;
import static com.thoughtworks.ketsu.web.validators.Validators.validate;

@Path("users")
public class UsersApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Map<String, Object> userInfo,
                               @Context UserRepo userRepo,
                               @Context Routes routes) {
        validate(userInfo, fieldNotEmpty("name"));

        User saveUser = userRepo.save(new User(userInfo.get("name").toString()));
        return Response.created(routes.userUrl(saveUser)).build();
    }

//    @Path("{userId}")
//    public UserApi getUser(@PathParam("userId") String userId,
//                           @Context UserRepo userRepo) {
//        return userRepo.ofId(new UserId(userId))
//                .map(UserApi::new)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }
}
