package com.example.j4011.bottledispenser;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class BottleDispenser {
    private int bottles;

    private float money;
    String kuitti="";

    private ArrayList<Bottle> bottle_array;
    public ArrayList<String> drink_array;
    public ArrayList<Double> size_array;
    private static BottleDispenser bd = new BottleDispenser();

    public static BottleDispenser getInstance() {
        return bd;
    }



    public BottleDispenser() {

        bottles = 5;

        money = 0;


        bottle_array = new ArrayList<Bottle>();

        String n, manuf;

        double totE, s, p;

        for (int i = 0; i <= bottles; i++) {

            if (i < 2) {

                n = "Pepsi Max";

                manuf = "Pepsi";

                totE = 0.3;

                if (i < 1) {

                    s = 0.5;

                    p = 1.80;

                } else {

                    s = 1.5;

                    p = 2.20;

                }


            } else if (i < 4) {

                n = "Coca-Cola Zero";

                manuf = "Coca-Cola";

                totE = 0.4;

                if (i < 3) {

                    s = 0.5;

                    p = 2.00;

                } else {

                    s = 1.5;

                    p = 2.50;

                }

            } else {

                n = "Fanta Zero";

                manuf = "Coca-Cola";

                totE = 0.5;

                s = 0.5;

                p = 1.95;

            }

            bottle_array.add(i, new Bottle(n, manuf, totE, s, p));

        }
        drink_array = new ArrayList<String>();
            drink_array.add("Pepsi Max");
        drink_array.add("Coca-Cola Zero");
        drink_array.add("Fanta Zero");

        size_array = new ArrayList<Double>();
            size_array.add(0.5);
            size_array.add(1.5);
        }




    public float addMoney(float current) {

        money=money + current;

        System.out.println("Klink! Lisää rahaa laitteeseen!");
        return money;


    }

    public float getMoney() {
        return money;
    }



    /*public String buyBottle(int bt_index)   {


        Bottle bt = bottle_array.get(bt_index-1);

        if (bottles <= 0) {
            System.out.println("Pullot ovat loppuneet!");
            return ("Dispenser empty");

        }
        if (bottle_array.get(bt_index)==null){
            return ("Item not available");
        }

        else if (money <= bt.getPrize()) {

            System.out.println("Syötä rahaa ensin!");
            return "Add more money";

        }


        else {

            bottles -= 1;

            money -= bt.getPrize();

            System.out.println("KACHUNK! " + bt.getName() + " tipahti masiinasta!");


            bottle_array.remove(bt);
            return "KACHUNK! " + bt.getName() + " dropped from the machine!";

        }

    }*/

    public String getKuitti(){
        return kuitti;
    }

    public String buyBottle1(String drink, double size) {


        for (Bottle bt : bottle_array) {
            //Jos nimi JA koko täsmäävät
            if ((bt.getName().equals(drink)) && (bt.getSize() == size)) {
                if (bottles <= 0) {
                    System.out.println("Pullot ovat loppuneet!");
                    return ("Dispenser empty");

                } else if (money <= bt.getPrize()) {

                    System.out.println("Syötä rahaa ensin!");
                    return "Add more money";

                } else {

                    bottles -= 1;

                    money -= bt.getPrize();

                    System.out.println("KACHUNK! " + bt.getName() + " tipahti masiinasta!");


                    bottle_array.remove(bt);
                    kuitti="Viimeisin ostos: \n\n"+bt.getName()+"\nHinta: "+bt.getPrize();
                    return "KACHUNK! " + bt.getName() + " dropped from the machine!";
                }
            }
        }
        return "Bottle not found";
    }

    public String BottleList() {

        String BottleListText = "";

        for (int i = 0; i <= bottles; i++) {

            Bottle bt = bottle_array.get(i);

            BottleListText += (i + 1 + ". Nimi: " + bt.getName() + "\n" + "\tKoko: " + bt.getSize() + "\tHinta: " + bt.getPrize() + "\n");

        }
        if (bottles != 0){
            return BottleListText;
    }else{
            return ("Dispenser empty!");
        }

    }

    public void deleteBottle(Bottle bt) {

        bottle_array.remove(bt);

    }

    public   void   returnMoney()   {

        System.out.printf("Klink klink. Sinne menivät rahat! " + "Rahaa tuli ulos " +"%.2f" +"€\n", money);

        money = 0;

    }
}
