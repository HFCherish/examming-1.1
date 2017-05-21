package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    void save(@Param("user") User user);

//    User findByUserName(@Param("userName") String userName);

    User findById(@Param("id") String id);
}
