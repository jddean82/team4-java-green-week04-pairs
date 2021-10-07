package com.techelevator;


import com.techelevator.view.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
    FileReader fileReader = new FileReader();
    Inventory inventory = new Inventory();

    public void run() {


        //probably should do stuff here... ;-)


        //LOOP for MAIN MENU
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

    private void runSubMenu() {

        while (true) {

            //Captures user input on Sub Menu
            String subUserSelection = userInterface.printSubMenu();

            if (subUserSelection.equals(ADD_MONEY)) {
                runAddMoney();
            } else if (subUserSelection.equals(SELECT_PRODUCTS)) {
                displayInventory();
                selectProducts();
            } else if (subUserSelection.equals(COMPLETE_TRANSACTION)) {
                System.out.println(COMPLETE_TRANSACTION);
                break;
            }
        }
    }

    private void runAddMoney() {
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
        Inventory inventory = new Inventory();
        List<CateringItem> cateringItemList = new ArrayList<>();

        cateringItemList = inventory.displayInventory();

        userInterface.displayInventory(cateringItemList);
    }

    public void selectProducts(){

        userInterface.selectProduct();
        userInterface.selectQuantity();

    }
}