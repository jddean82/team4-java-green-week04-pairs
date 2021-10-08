package com.techelevator;

import com.techelevator.view.Receipt;
import com.techelevator.view.Wallet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class test_Receipt
{
    private Receipt myReceipt;

    @Before
    public void testSetup() { myReceipt = new Receipt(5,"B1","Beverage",5,25);}

    @Test
    public void test_constructor_updates()
    {
        ////Gets Get = Test Constructor


        Assert.assertEquals(5,myReceipt.getQty());
        Assert.assertEquals("B1",myReceipt.getType());
        Assert.assertEquals("Beverage",myReceipt.getDescription());
        Assert.assertEquals(null,5,myReceipt.getPrice(),.001);
        Assert.assertEquals(null,25,myReceipt.getTotalPrice(),.001);

        Assert.assertEquals(null,50, myReceipt.setTotalPrice(50.00),.001);






    }


}
