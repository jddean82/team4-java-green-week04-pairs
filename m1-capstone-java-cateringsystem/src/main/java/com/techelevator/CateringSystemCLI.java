package com.techelevator;


import com.techelevator.view.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CateringSystemCLI {

    //MAIN MENU
    private static final String DISPLAY_CATERING_ITEMS = "1";
    private static final String ORDER = "2";
    private static final String QUIT = "3";

    //SUBMENU
    private static final String ADD_MONEY = "1";
    private static final String SELECT_PRODUCTS = "2";
    private static final String COMPLETE_TRANSACTION = "3";


    public static void main(String[] args) {
        CateringSystemCLI cli = new CateringSystemCLI();
        cli.run();
    }

    UserInterface userInterface = new UserInterface();    //use in subMenu & mainMenu
    Wallet myWallet = new Wallet();                        //used in subMenu
    Inventory inventory = new Inventory();
    List<Receipt> receipts = new ArrayList<>(); // List to store user transaction for our receipt
    NumberFormat toCurrency = NumberFormat.getCurrencyInstance();


    public void run() {

        /////////////////LOOP for MAIN MENU
        while (true) {

            //Captures user input on Main Menu
            String userSelection = userInterface.printMainMenu();

            if (userSelection.equals(DISPLAY_CATERING_ITEMS)) {
                displayInventory();
            }
            else if (userSelection.equals(ORDER)) {
                runSubMenu();
            }
            else if (userSelection.equals(QUIT)) {
                break;
            }
            else
                { System.out.println("Invalid Selection, Please Try Again"+"\n");}
        }
    }

    /////////////////////////////////////////////////SUB MENU
    private void runSubMenu() {

        while (true) {

            double accountBalance = myWallet.getMoneyOnHand();
            //Captures user input on Sub Menu
            String subUserSelection = userInterface.printSubMenu(accountBalance);


            if (subUserSelection.equals(ADD_MONEY)) {
                addMoney();


            }
            else if (subUserSelection.equals(SELECT_PRODUCTS))
            {
                selectProducts();

            }
            else if (subUserSelection.equals(COMPLETE_TRANSACTION))
            {
                userInterface.printReceipt(receipts);
                calculateChange(myWallet.getMoneyOnHand());
                receipts = new ArrayList<>(); // reinitialize array to receipt for next receipt.
                myWallet.subtractMoney(myWallet.getMoneyOnHand());
                break;
            }
            else
            { System.out.println("Invalid Selection, Please Try Again"+"\n");}

        }
    }

    public void addMoney() {

        double fundsToAdd = userInterface.addFunds(); //Call user interface, get funds, return quantity (UI screens for 100 / 1000 limits)


        if (fundsToAdd > 100) {                                         // check if amount added is > $100, return error message
            System.out.println("Amount should be less than $100");

        } else if ((myWallet.getMoneyOnHand() + fundsToAdd > 1000)) {   // check if amount added  + current balance  >$1000, return error message
            System.out.println("Amount on hand exceeds $1000, Please add less than $" + (myWallet.getMoneyOnHand() + fundsToAdd - 1000));

        } else if (fundsToAdd % 1 != 0) {                               // check if amount added is a whole number, return error message
            System.out.println("Please enter a whole number amount.");

        } else if (fundsToAdd < 0){                                     // check if amount added is positive, return error message
            System.out.println("Cannot remove funds from current balance, please enter a positive amount to add");
        } else  myWallet.addMoney(fundsToAdd);                           // add new funds to current balance.

        // add action to audit log

        String fFundsToAdd = String.format("%,.2f", fundsToAdd);
        String fCurrentBalance = String.format("%,.2f", (myWallet.getMoneyOnHand()));
        auditLog(" ADD MONEY: $" + fFundsToAdd + " $" + fCurrentBalance);
    }


    private void displayInventory() {    // create a list of catering items and send list to UI to print.

        List<CateringItem> cateringItemList = new ArrayList<>();

        cateringItemList = inventory.displayInventory();

        userInterface.displayInventory(cateringItemList);
    }

    public void selectProducts() {    // take product code and quantity input by user. Remove item from inventory, add item to users cart.
        displayInventory();
        int quantity = 0;
        int existingQuantity;
        String productCode;

        productCode = userInterface.selectProductCode();

        if (inventory.hasProductKey(productCode))                                                       //IF PRODUCT CODE EXISTS... EXECUTE - ELSE ERROR
        {
            quantity = userInterface.selectQuantity();
            existingQuantity = inventory.getQty(productCode);

            if (existingQuantity <= 0)
                System.out.println("Sorry, we are all out of that product.");                           //  TEST OUT OF PRODUCT, Do NOTHING

            else if (existingQuantity >= quantity)                                                      /////Qty ON HAND > REQUESTED QTY - EXECUTE MAIN CODE
            {

                double costOfProduct = quantity * inventory.getPrice(productCode); //Calc total Cost

                if (costOfProduct <= myWallet.getMoneyOnHand())                     // we have enough money for total cost
                {                                                                   //we have the money and the qty so ...
                    myWallet.subtractMoney(costOfProduct);                              // PAY - subtract Money
                    inventory.subtractInventory(productCode, quantity);

                    // add to audit log
                    String fCostOfProduct = String.format("%,.2f", costOfProduct);// UPDATE inventory
                    String fCurrentBalance = String.format("%,.2f", (myWallet.getMoneyOnHand()));
                    auditLog(" " + quantity + " " + inventory.getDescription(productCode) + " " + productCode + " $" + fCostOfProduct + " $" + fCurrentBalance);

                    //PRINT RECEIPT
                    printReceipt(productCode, quantity);


                } else
                    System.out.println("The purchase cost is " + toCurrency.format(costOfProduct) + " which is greater than your current account balance of " + toCurrency.format(myWallet.getMoneyOnHand()) + ". Please enter more funds or select a lower quantity.");
                //^^ERROR - Not enough money

            } else                                                                                                      //ERROR-  Qty ON hand to low
                System.out.println("Your request of " + quantity + " exceeds our current inventory of " + existingQuantity);


        } else
            System.out.println("Please enter a valid Product Code, " + productCode + " isn't a valid.");               //ERROR - No such Product

    }

    public void printReceipt(String product, int quantity) {
        String productType = inventory.getType(product);
        String productDescription = inventory.getDescription(product);
        double productPrice = inventory.getPrice(product);
        String productCategory = null;


        // need to change product type to display the whole word on the receipt >>><<<ELSE IF ADDED WITH ELSE>>>

        // update product type to full type for display on receipt.

        if (productType.equals("A")) {
            productCategory = "Appetizer";
        }
        else if (productType.equals("B")) {
            productCategory = "Beverage";
        }
        else if (productType.equals("D")) {
            productCategory = "Dessert";
        }
        else if (productType.equals("E")) {
            productCategory = "Entree";
        }
        else
        {productCategory = "Undefined Product";}
        Receipt thisReceipt = new Receipt(quantity, productCategory, productDescription, productPrice, productPrice * quantity);  // create an instance of a receipt then at that instance to an array

        // create an instance of a receipt then add that instance to an array

        receipts.add(thisReceipt);
    }

    public void calculateChange(double total)   //Calc Total Amount to return to customer
    {

        int change20 = 0;
        int change10 = 0;
        int change5 = 0;
        int change1 = 0;
        int changeQtr = 0;
        int changeDime = 0;
        int changeNick = 0;


        List<Receipt> receiptItems = new ArrayList<>();   //////////////// do we need this?

        if (total >= 20) {
            change20 = (int) total / 20;
            total = total - (20 * change20);
        }
        if (total >= 10) {
            change10 = (int) total / 10;
            total = total - (10 * change10);
        }
        if (total >= 5) {
            change5 = (int) total / 5;
            total = total - (5 * change5);
        }
        if (total >= 1) {
            change1 = (int) total / 1;
            total = total - (change1);
        }
        total = total * 100;

        if (total >= 25) {
            changeQtr = (int) total / 25;
            total = total - (25 * changeQtr);
        }
        if (total >= 10) {
            changeDime = (int) total / 10;
            total = total - (10 * changeDime);
        }
        if (total >= 5) {
            changeNick = (int) total / 5;
        }
        System.out.println("You Received (" + change20 + ") Twenties, (" + change10 + ") Tens, (" + change5 + ") Fives, (" + change1 + ") Ones, (" + changeQtr + ") Quarters, (" + changeDime + ") Dimes, (" + changeNick + ") Nickels" + "\n");

        // add to audit log
        String fProductPrice = String.format("%,.2f", myWallet.getMoneyOnHand());
        String fCurrentBalance = String.format("%,.2f", myWallet.subtractMoney(myWallet.getMoneyOnHand()));
        auditLog(" GIVE CHANGE: $" + fProductPrice + " $" + fCurrentBalance);
    }



    public void auditLog(String auditString) {   // create an audit log to display every instance of Adding Money, Selecting A Product and Giving Change


        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)))) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");  // set the format for date / time display
            Calendar calendar = Calendar.getInstance();                                                 // get the current calender date
            out.println((simpleDateFormat.format(calendar.getTime()) + auditString));

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}