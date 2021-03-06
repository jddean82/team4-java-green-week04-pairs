package com.techelevator.view;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {


    NumberFormat toCurrency = NumberFormat.getCurrencyInstance(); //FORMAT CURRENCY ( we tried a couple of different approaches )


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

        // Sub Menu
        System.out.println("\n");
        System.out.println("(1) Add Money");
        System.out.println("(2) Select Products");
        System.out.println("(3) Complete Transaction");
        System.out.println("Current Account Balance: " + toCurrency.format(accountBalance)); //account balance from main passed in (from myWallet.bal)

        System.out.println("\n" + "Please make a sub menu selection: ");
        return subInput.nextLine();
    }


    public double addFunds() {
        // ask use for amount they would like to add
        Scanner moneyInput = new Scanner(System.in);
        double amountToAdd; // RETURN VALUE -only accept numeric

        System.out.println("How much money would you like to add, max is $100");
       try {
           amountToAdd = moneyInput.nextDouble();
           moneyInput.nextLine();

           return amountToAdd;
       }
       catch(InputMismatchException e){
            System.out.println("Please enter a valid quantity of currency");
            return 0;
        }

    }


    public String selectProductCode() { // ask user for product code for item to purchase
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the desired product code");
        return myScanner.nextLine();
    }


    public int selectQuantity() {  // ask user for a quantity to purchase - only take numeric
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter quantity");
        try {
            int qtyInput = myScanner.nextInt();
            myScanner.nextLine();
            return qtyInput;
        }
        catch(InputMismatchException e){
            System.out.println("Please enter a valid, whole number, numeric quantity");
            return 0;
        }
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


    public void printReceipt(List<Receipt> receipts)  // loop through an array of receipts(on sub menu exit) and print out a display of each receipt object
    {
        double totalCost = 0; //to sum cost of products

        for(Receipt receipt:receipts)

        { System.out.println(String.format("%-5d%-12s%-24s$%6.2f  $%6.2f", receipt.getQty() , receipt.getType() , receipt.getDescription(), +receipt.getPrice(), receipt.getTotalPrice()));
            totalCost += receipt.getTotalPrice();
        } System.out.println("\n");


        String totalCostFormat = String.format("Total: $%,.2f", totalCost);//output mask for total cost
        System.out.println(totalCostFormat);
        System.out.println();                                       //ADD space
    }

    public void printMessage(String message){   ////ERROR MESSAGES to UI
        System.out.println(message);
    }
}