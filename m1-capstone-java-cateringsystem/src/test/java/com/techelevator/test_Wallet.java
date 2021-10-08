package com.techelevator;
import com.techelevator.view.CateringItem;
import com.techelevator.view.Wallet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class test_Wallet
{
    private Wallet  myWallet;
    private CateringSystemCLI cateringSystemCLI;

    @Before
    public void testSetup() { myWallet = new Wallet();}

    @Test
    public void test_balance_updates()
    {
        ////TEST ADD MONEY AND GET BALANCE

        double expectedBalance = 30;
        Assert.assertEquals(null,expectedBalance,myWallet.addMoney(30),.001);

        expectedBalance = 60;
        myWallet.addMoney(30);
        Assert.assertEquals(null,expectedBalance, myWallet.getMoneyOnHand(),.001);

        ////SUBTRACT
        expectedBalance = 50;

        Assert.assertEquals(null,expectedBalance, myWallet.subtractMoney(10),.001);


    }

/*    public void test_run_add_money(){

        double expectedBalance = 30;
        Assert.assertEquals(null,expectedBalance,cateringSystemCLI.runAddMoney(),.001);

    }
*/



}
