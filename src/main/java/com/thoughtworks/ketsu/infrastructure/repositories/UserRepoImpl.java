package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepo;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Optional;

public class UserRepoImpl implements UserRepo {
    @Inject
    UserMapper mapper;

    @Override
    public com.thoughtworks.ketsu.domain.user.User save(com.thoughtworks.ketsu.domain.user.User user) {
        mapper.save(user);
        return mapper.findById(user.getId().id());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(mapper.findById(id));
    }
}
