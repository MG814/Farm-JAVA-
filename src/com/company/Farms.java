package com.company;

public class Farms {
    private final int price;
    private final int numberOfBildings;
    private double area;

    public Farms(int price, int numberOfBuildings, double area){
        this.price = price;
        this.numberOfBildings = numberOfBuildings;
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberOfBildings() {
        return numberOfBildings;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) { this.area = area; }

    @Override
    public String toString() {
        return "Farma:" +
                "\nIlość budynków: " + numberOfBildings +
                "\nAreał: " + area + " ha";
    }
}
