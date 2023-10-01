package com.solent.shop.models;

public class OrdersDetail {

    int product_quantity, product_id;

    double product_price;

    String user_name, shipping_address, user_email, product_name, product_image, billing_address, Order_id;

    public OrdersDetail() {
    }

    public OrdersDetail(int product_quantity, int product_id, double product_price, String user_name, String shipping_address, String user_email, String product_name, String product_image, String billing_address, String Order_id) {
        this.product_quantity = product_quantity;
        this.product_id = product_id;
        this.product_price = product_price;
        this.user_name = user_name;
        this.shipping_address = shipping_address;
        this.user_email = user_email;
        this.product_name = product_name;
        this.product_image = product_image;
        this.billing_address = billing_address;
        this.Order_id = Order_id;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(String Order_id) {
        this.Order_id = Order_id;
    }

    @Override
    public String toString() {
        return "OrdersDetail{" + "product_quantity=" + product_quantity + ", product_id=" + product_id + ", product_price=" + product_price + ", user_name=" + user_name + ", shipping_address=" + shipping_address + ", user_email=" + user_email + ", product_name=" + product_name + ", product_image=" + product_image + ", billing_address=" + billing_address + ", Order_id=" + Order_id + '}';
    }
}
