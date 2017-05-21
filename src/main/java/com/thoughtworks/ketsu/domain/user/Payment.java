package com.thoughtworks.ketsu.domain.user;

/**
 * Created by pzzheng on 5/21/17.
 */
public class Payment {
    private PayType payType;
    private double amount;

    private Payment() {}
    public Payment(PayType payType, double amount) {
        this.payType = payType;
        this.amount = amount;
    }
}
