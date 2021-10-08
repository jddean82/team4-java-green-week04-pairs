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
    public String getType(String productCode){

        String type;
        CateringItem cateringItem;
        cateringItem = cateringInventory.get(productCode);
        type = cateringItem.getType();

        return type;


    }

    public String getDescription(String productCode){

        String description;
        CateringItem cateringItem;
        cateringItem = cateringInventory.get(productCode);
        description = cateringItem.getDescription();

        return description;
    }

    public double getPrice(String productCode){

        double price;
        CateringItem cateringItem;
        cateringItem = cateringInventory.get(productCode);
        price = cateringItem.getPrice();

        return price;
    }

}
