package com.techelevator;


import com.techelevator.view.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CateringSystemCLI {

    //MAIN MENU
    private static final String DISPLAY_CATERING_ITEMS = "1" ;
    private static final String ORDER = "2" ;
    private static final String QUIT = "3" ;

    //SUBMENU
    private static final String ADD_MONEY = "1" ;
    private static final String SELECT_PRODUCTS = "2" ;
    private static final String COMPLETE_TRANSACTION = "3" ;


    public static void main(String[] args) {
        CateringSystemCLI cli = new CateringSystemCLI();
        cli.run();
    }

    UserInterface userInterface = new UserInterface();    //use in subMenu & mainMenu
    Wallet myWallet = new Wallet();                        //used in subMenu
    FileReader fileReader = new FileReader();
    Inventory inventory = new Inventory();
    List<Receipt> receipts = new ArrayList<>();           // List to store user transaction for our receipt


    public void run() {


        //probably should do stuff here... ;-)


        /////////////////LOOP for MAIN MENU
        while (true) {

            //Captures user input on Main Menu
            String userSelection = userInterface.printMainMenu();

            if (userSelection.equals(DISPLAY_CATERING_ITEMS)) {
                displayInventory();
            }
            if (userSelection.equals(ORDER)) {
                runSubMenu();
            }
            if (userSelection.equals(QUIT)) {
                break;
            }
        }
    }
        /////////////////////////////////////////////////SUB MENU
    private void runSubMenu() {

        while (true) {

            //Captures user input on Sub Menu
            String subUserSelection = userInterface.printSubMenu();

            if (subUserSelection.equals(ADD_MONEY)) {
                runAddMoney();

            } else if (subUserSelection.equals(SELECT_PRODUCTS)) {
                selectProducts();


            } else if (subUserSelection.equals(COMPLETE_TRANSACTION)) {
                userInterface.printReceipt(receipts);
                calculateChange(2);///<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>> CHANGE THIS
                break;
            }
        }
    }

    public void runAddMoney() {

        double fundsToAdd = userInterface.addFunds(); //Call user interface, get funds, return quantity (UI screens for 100 / 1000 limits)
        // IF user interface see 100 / 1000 violation, returns $0 to add to quantity - also sends error message
        //ELSE funds are added via Wallet.addMoney

        try {
            if (fundsToAdd > 100) {
                System.out.println("Amount should be less than $100");
            } else if ((myWallet.getMoneyOnHand() + fundsToAdd > 1000)) {
                System.out.println("Amount on hand exceeds $1000 Please add less than " + (myWallet.getMoneyOnHand() + fundsToAdd - 1000));
            } else myWallet.addMoney(fundsToAdd);
            System.out.println(myWallet.getMoneyOnHand()); ///QA LINE
        } catch (InputMismatchException ex) {
            System.out.println("Please enter a whole number amount");
        }
    }

    private void displayInventory() {

        List<CateringItem> cateringItemList = new ArrayList<>();

        cateringItemList = inventory.displayInventory();

        userInterface.displayInventory(cateringItemList);
    }

    public void selectProducts() {
        displayInventory();
        int quantity;
        int existingQuantity;
        String productCode;


        productCode = userInterface.selectProductCode();

        if(inventory.hasProductKey(productCode)) {
            quantity = userInterface.selectQuantity();
            existingQuantity = inventory.getInventory(productCode);

            if (existingQuantity <= 0)
                System.out.println("Sorry, We are all out of that product.");
            else if (existingQuantity >= quantity) {
                inventory.subtractInventory(productCode, quantity);
                printReceipt(productCode, quantity);
            } else
                System.out.println("Your request of " + quantity + " Exceeds what we have:" + existingQuantity);
        }
        else/////////////////////////////JEFF
            System.out.println("Please Enter A valid Product Code "+ productCode + " isn't a valid product code");

    }


    public void printReceipt(String product, int quantity) {
        String productType = inventory.getType(product);
        String productDescription = inventory.getDescription(product);
        double productPrice = inventory.getPrice(product);
        String productCategory=null;


        if (productType.equals("A")) {
            productCategory = "Appetizer" ;
        }
        if (productType.equals("B")) {
            productCategory = "Beverage" ;
        }
        if (productType.equals("D")) {
            productCategory = "Dessert" ;
        }
        if (productType.equals("E")) {
            productCategory = "Entree" ;
        }

        Receipt thisReceipt = new Receipt(quantity,productCategory,productDescription,productPrice,productPrice*quantity);
        receipts.add(thisReceipt);

    }

    public void calculateChange(double total)   //Calc Total Amount to return to customer
    {

        total = 76.40; /////////////////////////////////CHANGE THIS < REMOVE

        int change20 = 0;
        int change10 = 0;
        int change5 = 0;
        int change1 = 0;
        int changeQtr = 0;
        int changeDime = 0;
        int changeNick = 0;


       List<Receipt> receiptItems = new ArrayList<>();
      // Receipt receipt = new Receipt(quantity, )

        if(total>=20)
        {
            change20 = (int)total / 20;
            total = total -(20 * change20);
        }
        if(total>=10)
        {
            change10 = (int)total / 10;
            total = total -(10* change10);
        }
        if(total>=5)
        {
            change5 = (int)total / 5;
            total = total -(5 * change5);
        }
        if(total>=1)
        {
            change1 = (int)total / 1;
            total = total -(change1);

        }
        total = total *100;

        if(total>=25)
        {
            changeQtr = (int)total / 25;
            total = total -(25 * changeQtr);
        }
        if(total>=10)
        {
            changeDime = (int)total / 10;
            total = total -(10 * changeDime);
        }
        if(total>=5)
        {
            changeNick = (int)total / 5;

        }

        System.out.println("You Received (" +change20 +") Twenties, ("+  change10 + ") Tens, (" +  change5+ ") Fives, ("+  change1+") Ones, ("+changeQtr +") Quarters, ("+ ") Dimes, ("+ changeDime +") Nickels, ("+ changeNick +")"+"\n");

    }

}