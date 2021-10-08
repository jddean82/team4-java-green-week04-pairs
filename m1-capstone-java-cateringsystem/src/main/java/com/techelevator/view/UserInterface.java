package com.techelevator.view;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    // kind of looks like a good place to write some code if you ask me ;-)

    Wallet myWallet = new Wallet(); ////////////////MOVE TO MAIN ?
    NumberFormat toCurrency = NumberFormat.getCurrencyInstance();

    public String printMainMenu() {

        Scanner mainInput = new Scanner(System.in);

        // Main Menu
        System.out.println("(1) Display Catering Items");
        System.out.println("(2) Order");
        System.out.println("(3) Quit");

        System.out.println("\n" + "Please make a main menu selection: ");

        return mainInput.nextLine();
    }

    public String printSubMenu(double accountBalance) {

        Scanner subInput = new Scanner(System.in);

        //NumberFormat toCurrency = NumberFormat.getCurrencyInstance();
        toCurrency.format(accountBalance);

        // Sub Menu
        System.out.println("\n");
        System.out.println("(1) Add Money");
        System.out.println("(2) Select Products");
        System.out.println("(3) Complete Transaction");
        System.out.println("Current Account Balance: " + toCurrency.format(accountBalance));

        System.out.println("\n" + "Please make a sub menu selection: ");

        return subInput.nextLine();
    }

    public double addFunds() {  // ask use for amount they would like to add
        Scanner moneyInput = new Scanner(System.in);
        double amountToAdd; // RETURN VALUE

        System.out.println("How much money would you like to add, max is $100");
        amountToAdd = moneyInput.nextDouble();
        moneyInput.nextLine();

        return amountToAdd;

    }

    public String selectProductCode() { // ask user for product code for item to purchse
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the desired product code");
        return myScanner.nextLine();
    }

    public int selectQuantity() {  // ask user for a quantity to purchase
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter quantity");
        int qtyInput = myScanner.nextInt();
        myScanner.nextLine();
        return qtyInput;
    }

    public void displayInventory(List<CateringItem> cateringItems) {

        System.out.printf("%-15s%-25s%-10s%-5s", "Product Code", "Description", "Qty", "Price");
        System.out.println("\n");

        int quantity;
        String quantityAsString;

        for (CateringItem cateringItem : cateringItems) {
           quantity =  cateringItem.getQty();      //GET QTY TO TEST FOR 0 IF 0 insufficient stock

            if(quantity == 0)
                quantityAsString = "SOLD OUT";
            else
                quantityAsString = Integer.toString(quantity);
                                                                              ///changed to quantity
            System.out.println(String.format("%-15s%-25s%-10s$%6.2f", cateringItem.getCode(), cateringItem.getDescription(), quantityAsString, cateringItem.getPrice()));
        }
        System.out.println("\n");

    }

    public void printReceipt(List<Receipt> receipts)  // loop through an array of receipts and print out a display of each receipt object
    {
        double totalCost = 0;
        System.out.println("\n");
        for(Receipt receipt:receipts)
        { System.out.println(String.format("%-5d%-12s%-24s$%6.2f  $%6.2f", receipt.getQty() , receipt.getType() , receipt.getDescription(), +receipt.getPrice(), receipt.getTotalPrice()));
            totalCost += receipt.getTotalPrice();
        } System.out.println("\n");
        String totalCostFormat = String.format("Total: $%,.2f", totalCost);
        System.out.println(totalCostFormat);
        System.out.println("\n");
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}