package com.techelevator;


import com.techelevator.view.Inventory;
import com.techelevator.view.UserInterface;
import com.techelevator.view.Wallet;

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

	
	
	public void run() {


		//probably should do stuff here... ;-)
		Inventory inventory = new Inventory();
		UserInterface userInterface = new UserInterface(); 	//use in subMenu & mainMenu
		Wallet myWallet = new Wallet();						//used in subMenu

			//LOOP for MAIN MENU
			while(true){

				//Captures user input on Main Menu
				String userSelection = userInterface.printMainMenu();

				if (userSelection.equals(DISPLAY_CATERING_ITEMS)){
					System.out.println(DISPLAY_CATERING_ITEMS);
				}
				if (userSelection.equals(ORDER)){

					while (true){

						//Captures user input on Sub Menu
						String subUserSelection = userInterface.printSubMenu();

						if (subUserSelection.equals(ADD_MONEY)){

							double fundsToAdd = userInterface.addFunds(); //Call user interface, get funds, return quantity (UI screens for 100 / 1000 limits)

							myWallet.addMoney(fundsToAdd);	// IF user interface see 100 / 1000 violation, returns $0 to add to quantity - also sends error message
															//ELSE funds are added via Wallet.addMoney
							System.out.println(myWallet.getMoneyOnHand()); ///QA LINE
						}
						 else if (subUserSelection.equals(SELECT_PRODUCTS)){
							System.out.println(SELECT_PRODUCTS);
						}
						 else if (subUserSelection.equals(COMPLETE_TRANSACTION)){
							System.out.println(COMPLETE_TRANSACTION);
							break;
						}

					}

				}
				 if (userSelection.equals(QUIT)){
					System.out.println(QUIT);
					break;
				}


			}





	}
	
	



}
