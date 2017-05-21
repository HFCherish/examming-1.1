package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pzzheng on 5/21/17.
 */
public class Payment implements Record{
    private PayType payType;
    private double amount;
    private Order order;
    private DateTime createdAt;

    private Payment() {}

    public Payment(PayType payType, double amount) {
        this.payType = payType;
        this.amount = amount;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("amount", amount);
            put("pay_type", payType.toString());
            put("created_at", createdAt.getMillis());
            put("order_uri", routes.orderUri(order.getUserId().id(), order.getId().id()));
            put("uri", routes.paymentUri(order.getUserId().id(), order.getId().id()));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
