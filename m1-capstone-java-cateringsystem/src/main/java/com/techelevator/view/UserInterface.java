package com.techelevator.view;

import java.util.Scanner;

public class UserInterface {

    // kind of looks like a good place to write some code if you ask me ;-)

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
        System.out.println("Current Account Balance: $");

        System.out.println("\n" + "Please make a sub menu selection: ");

        return subInput.nextLine();
    }


}
