package com.techelevator;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class TestPlayground
{
    public static void main(String[] args) {


        //////////////////////////////////////////////////////////////FILE OUTPUT
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("example.txt", true)))) {

            out.println("This is sample text");

            out.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        double number = 9.99;


        /////////////////////////////////////////////////////////////MONEY
        NumberFormat toCurrency = NumberFormat.getCurrencyInstance();
        toCurrency.format(number);
        System.out.println(toCurrency.format(number));


        ///////////////////////////////////////AUDIT


        ///CHANGE
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter Amount");
        double total = userInput.nextDouble();
        System.out.println();
        System.out.println(total);


        int change20 = 0;
        int change10 = 0;
        int change5 = 0;
        int change1 = 0;
        int changeQtr = 0;
        int changeDime = 0;
        int changeNick = 0;


        if(total>=20)
        {
            change20 = (int)total / 20;
            total = total -(20 * change20);
        }
        if(total>=10)
        {
            change10 = (int)total / 10;
            total = total -(10* change10);
        }

        if(total>=5)
        {
            change5 = (int)total / 5;
            total = total -(5 * change5);
        }

        if(total>=1)
        {
            change1 = (int)total / 1;
            total = total -(change1);

        }
        total = total *100;
        System.out.println(total);

        if(total>=25)
        {
            changeQtr = (int)total / 25;
            total = total -(25 * changeQtr);
        }

        if(total>=10)
        {
            changeDime = (int)total / 10;
            total = total -(10 * changeDime);
        }

        if(total>=5)
        {
            changeNick = (int)total / 5;

        }


        System.out.println(change20 +" 20s/"+  change10+ " 10s/" +  change5+ " 5s/"+  change1+" 1s/"+changeQtr +" Qtrs/"+ " /Dimes"+ changeDime +" /Nicks"+ changeNick );


    }






}
