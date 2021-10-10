package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FileReader {

        private Scanner scanner;

        public FileReader(){
            File inputFile = new File("cateringsystem.csv"); //<<<<<<<<<<<<<PROVIDED FILE>>>>>>>>>>>>>>>>>>>>>>
                                                                                //**** USE ON OR OTHER ONLY******
          //  File inputFile = new File("cateringsystemTEST.csv"); //<<<<<<<<<<<<<TEST HARNESS FILE>>>>>>>>>>>>>>>>>>>
            try {
                scanner = new Scanner(inputFile);
            } catch (FileNotFoundException e) {
                System.out.println("File not Found.");
                    System.exit(0); //terminated with out errors
            }

        }

    public Map<String, CateringItem> loadInventory(){

            Map<String,CateringItem> cateringInventory = new TreeMap<>();  // create a map to hold items pulled in from fil reader.


            while (scanner.hasNextLine()){

                String inventoryLine = scanner.nextLine();

                String [] inventoryField = inventoryLine.split("\\|");

                String type = inventoryField[0];
                String code = inventoryField [1];
                String description = inventoryField [2];
                Double price = Double.parseDouble(inventoryField[3]);

                CateringItem cateringItem = new CateringItem(type, code, description, price);
                cateringItem.setQty(25);
                cateringInventory.put(code, cateringItem);
            }
            return  cateringInventory;
    }


}
