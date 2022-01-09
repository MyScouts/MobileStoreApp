package com.example.model;

public class Order {
    private int OrderId;
    private int AccountId;
    private int MobileId;
    private String MobileName;
    private double Price;
    private int Num;

    public Order() {
    }

    public Order(int orderId, int accountId, int mobileId, String mobileName, double price, int num) {
        OrderId = orderId;
        AccountId = accountId;
        MobileId = mobileId;
        MobileName = mobileName;
        Price = price;
        Num = num;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public int getMobileId() {
        return MobileId;
    }

    public void setMobileId(int mobileId) {
        MobileId = mobileId;
    }

    public String getMobileName() {
        return MobileName;
    }

    public void setMobileName(String mobileName) {
        MobileName = mobileName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }
}