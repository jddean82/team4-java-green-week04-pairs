package com.techelevator.view;

import java.util.Map;

public class Inventory {

    private Map<String, CateringItem> cateringInventory;

    public Inventory(){

        FileReader inventoryReader = new FileReader();
        cateringInventory = inventoryReader.loadInventory();

    }

  //  public displayInventory(){




    //}


}
