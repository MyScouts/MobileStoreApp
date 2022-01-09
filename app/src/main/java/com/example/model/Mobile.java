package com.example.model;

public class Mobile {
    private int MobileId;
    private String Name;
    private String Brand;
    private int ImageThumb;
    private String ScreenSize;
    private String Category;
    private String CPU;
    private String Storage;
    private String Ram;
    private String Pin;
    private double Price;

    public Mobile() {
    }

    public Mobile(int mobileId, String name, String brand, int imageThumb, String screenSize, String category, String CPU, String storage, String ram, String pin, double price) {
        MobileId = mobileId;
        Name = name;
        Brand = brand;
        ImageThumb = imageThumb;
        ScreenSize = screenSize;
        Category = category;
        this.CPU = CPU;
        Storage = storage;
        Ram = ram;
        Pin = pin;
        Price = price;
    }

    public int getMobileId() {
        return MobileId;
    }

    public void setMobileId(int mobileId) {
        MobileId = mobileId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public int getImageThumb() {
        return ImageThumb;
    }

    public void setImageThumb(int imageThumb) {
        ImageThumb = imageThumb;
    }

    public String getScreenSize() {
        return ScreenSize;
    }

    public void setScreenSize(String screenSize) {
        ScreenSize = screenSize;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getStorage() {
        return Storage;
    }

    public void setStorage(String storage) {
        Storage = storage;
    }

    public String getRam() {
        return Ram;
    }

    public void setRam(String ram) {
        Ram = ram;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}