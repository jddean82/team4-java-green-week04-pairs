package com.techelevator.view;

public class Receipt {  // create  Receipt object that can take in all of the parameters necessary to print out on the receipt.

    private int qty;
    private String type;
    private String description;
    private double price;
    private double totalPrice;

    public Receipt(int qty,String type, String description, double price, double totalPrice){
        this.qty = qty;
        this.type = type;
        this.description = description;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public int getQty() {
        return qty;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
