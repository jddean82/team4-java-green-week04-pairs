package com.techelevator;


import com.techelevator.view.Inventory;
import com.techelevator.view.UserInterface;

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
		UserInterface userInterface = new UserInterface();


			while(true){

				String userSelection = userInterface.printMainMenu();

				if (userSelection.equals(DISPLAY_CATERING_ITEMS)){
					System.out.println(DISPLAY_CATERING_ITEMS);
				}
				if (userSelection.equals(ORDER)){

					while (true){
						String subUserSelection = userInterface.printSubMenu();

						if (subUserSelection.equals(ADD_MONEY)){
							System.out.println(ADD_MONEY);
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
