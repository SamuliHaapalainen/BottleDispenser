package com.example.j4011.bottledispenser;

public class Bottle {

    private String name;

    private String manufacturer;

    private double total_energy;

    private double size;

    private double prize;



    public Bottle () {

        name = "Pepsi Max";

        manufacturer = "Pepsi";

        total_energy = 0.3;

        size = 0.5;

        prize = 1.8;

    }



    public Bottle (String n, String manuf, double totE, double s, double p) {

        name = n;

        manufacturer = manuf;

        total_energy = totE;

        size = s;

        prize = p;

    }

    public String getName() {

        return name;

    }



    public String getManufacturer() {

        return manufacturer;

    }



    public double getEnergy() {

        return total_energy;

    }





    public double getPrize() {

        return prize;

    }

    public double getSize() {

        return size;

    }

}
