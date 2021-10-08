package com.techelevator.view;

public class Wallet
{
    private double moneyOnHand;



    public double addMoney(double moneyIn) {
        this.moneyOnHand += moneyIn;
        return this.moneyOnHand;
    }

    public double subtractMoney(double moneyOut)
    {
        this.moneyOnHand -= moneyOut;
        return this.moneyOnHand;
    }

    public double getMoneyOnHand() {
        return this.moneyOnHand;
    }


}
