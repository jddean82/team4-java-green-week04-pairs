package com.techelevator.view;

import java.util.*;

public class Inventory {

    private Map<String, CateringItem> cateringInventory;

    public Inventory(){

        FileReader inventoryReader = new FileReader();
        cateringInventory = inventoryReader.loadInventory();


    }

   public List<CateringItem> displayInventory(){

       List<CateringItem> listOfCateringItems = new ArrayList<>();


        Set<String> inventoryKeys = new HashSet<String>();
        inventoryKeys = cateringInventory.keySet();

        for(String inventoryKey: inventoryKeys)
        {
            CateringItem item = cateringInventory.get(inventoryKey);
            listOfCateringItems.add(item);

        }

        return listOfCateringItems;


   }


}
