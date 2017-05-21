package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.user.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * Created by pzzheng on 5/21/17.
 */
public interface PaymentMapper {
    void payFor(@Param("payment") Payment payment, @Param("oid") String id);
}
