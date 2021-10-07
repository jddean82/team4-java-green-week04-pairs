package com.techelevator.view;

public class Wallet
{
    private double moneyOnHand;



    public double addMoney(double moneyIn) {

        moneyOnHand += moneyIn;

        return moneyOnHand;
    }

    public double subtractMoney(double moneyOut)
    {
        moneyOnHand -= moneyOut;
        return moneyOnHand;
    }

    public double getMoneyOnHand() {
        return moneyOnHand;
    }


}
