package com.techelevator.view;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    // kind of looks like a good place to write some code if you ask me ;-)

    Wallet myWallet = new Wallet();

    public String printMainMenu() {

        Scanner mainInput = new Scanner(System.in);

        // Main Menu
        System.out.println("(1) Display Catering Items");
        System.out.println("(2) Order");
        System.out.println("(3) Quit");

        System.out.println("\n" + "Please make a main menu selection: ");

        return mainInput.nextLine();
    }

    public String printSubMenu() {

        Scanner subInput = new Scanner(System.in);

        // Sub Menu
        System.out.println("(1) Add Money");
        System.out.println("(2) Select Products");
        System.out.println("(3) Complete Transaction");
        System.out.println("Current Account Balance: $" + myWallet.getMoneyOnHand());

        System.out.println("\n" + "Please make a sub menu selection: ");

        return subInput.nextLine();
    }

    public double addFunds()
    {
        Scanner moneyInput = new Scanner(System.in);
        double amountToAdd; // RETURN VALUE

        System.out.println("How much money would you like to add, max is $100");
        amountToAdd = moneyInput.nextInt();
        moneyInput.nextLine();

        return amountToAdd;

    }

    public String selectProduct(){
        Scanner myScanner  = new Scanner(System.in);
        System.out.println("Please enter the desired product code");
        return myScanner.nextLine();
    }

    public int selectQuantity() {
        Scanner myScanner  = new Scanner(System.in);
        System.out.println("Please enter quantity");
        int qtyInput = myScanner.nextInt();
        myScanner.nextLine();
        return qtyInput;
    }
    public void displayInventory(List<CateringItem> cateringItems){

        System.out.printf("%-20s%-25s%-15s%-8s", "Product Code", "Description", "Qty", "Price");
        System.out.println("\n");

        for(CateringItem cateringItem : cateringItems ){
        System.out.println(String.format("%-20s%-25s%-15s%-8.2f", cateringItem.getCode(), cateringItem.getDescription(), cateringItem.getQty(), cateringItem.getPrice()));
    } System.out.println("\n");

}
}