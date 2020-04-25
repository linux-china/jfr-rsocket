package org.mvnsearch.jfr;

import jdk.jfr.Category;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

/**
 * Place Order event
 *
 * @author linux_china
 */
@Name("org.mvnsearch.jfr.PlaceOrderEvent")
@Category("Transaction")
public class PlaceOrderEvent extends Event {
    @Label("sellerId")
    private long sellerId;
    @Label("buyerId")
    private long buyerId;
    @Label("amount")
    private long amount;
    @Label("orderNo")
    private String orderNo;

    public PlaceOrderEvent() {
    }

    public PlaceOrderEvent(long buyerId, long sellerId, long amount) {
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.amount = amount;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
