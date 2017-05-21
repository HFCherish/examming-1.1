package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.user.Order;
import org.apache.ibatis.annotations.Param;

/**
 * Created by pzzheng on 5/21/17.
 */
public interface OrderMapper {
    Order findById(@Param("oid") String id);

    void save(@Param("order") Order order, @Param("userId") String uid);
}
