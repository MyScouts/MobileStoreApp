package com.example.model;

public class Cart {
    private int CartId;
    private int AccountId;
    private int MobileId;
    private int ImageThumb;
    private String MobileName;
    private double Price;
    private int Num;

    public Cart() {
    }

    public Cart(int cartId, int accountId, int mobileId, int imageThumb, String mobileName, double price, int num) {
        CartId = cartId;
        AccountId = accountId;
        MobileId = mobileId;
        ImageThumb = imageThumb;
        MobileName = mobileName;
        Price = price;
        Num = num;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
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

    public int getImageThumb() {
        return ImageThumb;
    }

    public void setImageThumb(int imageThumb) {
        ImageThumb = imageThumb;
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