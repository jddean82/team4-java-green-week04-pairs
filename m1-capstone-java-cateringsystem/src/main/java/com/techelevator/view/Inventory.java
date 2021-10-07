package com.techelevator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {

    private Map<String, CateringItem> cateringInventory;

    public Inventory(){

        FileReader inventoryReader = new FileReader();
        cateringInventory = inventoryReader.loadInventory();

    }

   public List<CateringItem> displayInventory(){

        List<CateringItem> listOfCateringItems = new ArrayList<>();




    }


}
