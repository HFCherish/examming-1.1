package com.thoughtworks.ketsu.domain.user;

import java.util.Optional;

public interface UserRepo {
    User save(User user);

    Optional<User> findById(String id);

//    User findUserByName(String userName);
}
