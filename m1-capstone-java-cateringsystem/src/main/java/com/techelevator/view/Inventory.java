package com.techelevator.view;

import java.util.*;

public class Inventory {

    private int qtyOnHand;
    private String productCode;
    private Map<String, CateringItem> cateringInventory;

    public Inventory() {

        FileReader inventoryReader = new FileReader();
        cateringInventory = inventoryReader.loadInventory();


    }

    public List<CateringItem> displayInventory() {

        List<CateringItem> listOfCateringItems = new ArrayList<>();


        Set<String> inventoryKeys = new HashSet<String>();
        inventoryKeys = cateringInventory.keySet();

        for (String inventoryKey : inventoryKeys) {
            CateringItem item = cateringInventory.get(inventoryKey);
            listOfCateringItems.add(item);

        }

        return listOfCateringItems;
    }


    public void subtractInventory(String productCode, int qtyInput)
    {

        CateringItem updateInventory;
        updateInventory =  cateringInventory.get(productCode);

        if(updateInventory.getQty()>= qtyInput)
        {
            updateInventory.setQty(updateInventory.getQty() - qtyInput);
        }

    }

    public int getInventory(String productCode)
    {
        int quantity=0;
        CateringItem  cateringItem;
        cateringItem = cateringInventory.get(productCode);
        quantity = cateringItem.getQty();

        return quantity;

    }


}
