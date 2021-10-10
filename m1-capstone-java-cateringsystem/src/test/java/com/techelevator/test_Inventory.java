package com.techelevator;

import com.techelevator.view.CateringItem;
import com.techelevator.view.Inventory;
import com.techelevator.view.Receipt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class test_Inventory {





    @Test
    public void test_Inventory()
            {

        Inventory inventory = new Inventory(); //CREATES file reader and loads from inventory reader cals.


        Map<String, CateringItem> cateringInventory = new TreeMap<>();

        //create CIs for map
        CateringItem itemB2 = new CateringItem("B","B2","Wine",3.05);
        itemB2.setQty(25);
        CateringItem itemB3 = new CateringItem("B","B3","Java Blue Beer",3.55);
        itemB3.setQty(25);
        CateringItem itemD2 = new CateringItem("D","D2","Cake",1.80);
        itemD2.setQty(25);


        //populate map
        cateringInventory.put("B2",itemB2);
        cateringInventory.put("B3",itemB3);
        cateringInventory.put("D2",itemD2);

        Inventory testInventory = new Inventory();
        testInventory.setCateringInventory(cateringInventory);

        ///TEST - INVENTORY CONSTRUCTOR
      // Assert.assertEquals(testInventory.getCateringInventory(),inventory.getCateringInventory());
                System.out.println(testInventory.equals(inventory));




    }


}
