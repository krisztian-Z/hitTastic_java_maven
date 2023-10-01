package com.solent.shop.models;

import java.text.NumberFormat;
import java.util.List;

public class Product {

    int product_id, category_id, quantity;

    String name, image, product_description;

    double unit_price;

    List<Category> category;

    public Product() {
    }

    public Product(int category_id, String name, String product_description, String image, int quantity, double unit_price) {
        this.category_id = category_id;
        this.quantity = quantity;
        this.name = name;
        this.image = image;
        this.product_description = product_description;
        this.unit_price = unit_price;
    }

    public Product(int product_id, int category_id, String name, String product_description, String image, int quantity, double unit_price) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.quantity = quantity;
        this.name = name;
        this.image = image;
        this.product_description = product_description;
        this.unit_price = unit_price;
    }

    public Product(int product_id, int category_id, String name, String product_description, String image, int quantity, double unit_price, List category) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.quantity = quantity;
        this.name = name;
        this.image = image;
        this.category = category;
        this.product_description = product_description;
        this.unit_price = unit_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public void setCategory(List category) {
        this.category = category;
    }

    public List getCategory() {
        return this.category;
    }

    public double PriceFormater(double price) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return Double.parseDouble(nf.format(price));
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", category_id=" + category_id + ", quantity=" + quantity + ", name=" + name + ", image=" + image + ", product_description=" + product_description + ", unit_price=" + unit_price + '}';
    }
}
