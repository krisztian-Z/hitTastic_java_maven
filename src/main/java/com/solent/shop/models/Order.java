package com.solent.shop.models;

public class Order {

    int id;

    double order_amount;

    String order_status, Order_id;

    public Order() {
    }

    public Order(int id, double order_amount, String order_status, String Order_id) {
        this.id = id;
        this.order_amount = order_amount;
        this.order_status = order_status;
        this.Order_id = Order_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(double order_amount) {
        this.order_amount = order_amount;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(String Order_id) {
        this.Order_id = Order_id;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", order_amount=" + order_amount + ", order_status=" + order_status + ", Order_id=" + Order_id + '}';
    }
}
