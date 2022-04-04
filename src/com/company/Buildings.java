package com.company;

abstract public class Buildings {
    private String name;
    private double price;

    public Buildings(String name, double price){
           this.name = name;
           this.price = price;
    }
    public double getPrice() { return price; }

    public String getName() { return name; }
}
