package com.techelevator;

import com.techelevator.view.CateringItem;
import com.techelevator.view.Inventory;
import com.techelevator.view.Receipt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class test_Inventory {





    @Test
    public void test_Inventory()
            {

        Inventory inventory = new Inventory(); //CREATES file reader and loads from inventory reader cals.

        Inventory myInventory = null;
        Map<String, CateringItem> cateringInventory = new HashMap<>(); // catering item map

        //create CIs for map
        CateringItem itemB2 = new CateringItem("B","B2","Wine",3.05);
        CateringItem itemB3 = new CateringItem("B","B3","Java Go Blue Beer",3.55);
        CateringItem itemD2 = new CateringItem("D","D2","Cake",1.00);


        //populate map
        cateringInventory.put("B2",itemB2);
        cateringInventory.put("B3",itemB3);
        cateringInventory.put("D2",itemD2);

        myInventory.setCateringInventory(cateringInventory);

        ///TEST - INVENTORY CONSTRUCTOR
        Assert.assertEquals(myInventory.getCateringInventory(),inventory.getCateringInventory());




    }


}
