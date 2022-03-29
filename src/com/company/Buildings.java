package com.company;

abstract public class Buildings {
    private String name;
    private double price;

    public Buildings(String name, double price){
        this.name = name;
        switch (this.name) {
            case "chlew", "obora", "stodoła", "kurnik" -> this.price = price;
        }
    }

    public double getPrice() { return price; }
}
