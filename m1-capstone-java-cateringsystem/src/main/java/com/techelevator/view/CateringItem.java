package com.techelevator.view;

public class CateringItem {

    private String type;
    private String code;
    private String description;
    private double price;
    private final int qty = 25;


    public CateringItem(String type, String code, String description, double price){
        this.type = type;
        this.code = code;
        this.description = description;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
