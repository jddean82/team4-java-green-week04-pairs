package com.techelevator;

import com.techelevator.view.CateringItem;
import com.techelevator.view.Receipt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class test_CateringItem
{


    private CateringItem item;

    @Before
    public void testSetup()
    {

        item = new CateringItem("B1", "B", "Beverage", 5.25);
    }

        @Test
        public void test_ci_Constructor()
        {
            ////Gets Get = Test Constructor


            Assert.assertEquals("B1", item.getType());
            Assert.assertEquals("B", item.getCode());
            Assert.assertEquals("Beverage", item.getDescription());
            Assert.assertEquals(null, 5.25, item.getPrice(), .001);


        }

}