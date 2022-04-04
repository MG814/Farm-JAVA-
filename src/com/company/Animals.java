package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animals {
    private final String species;
    private double price;
    private double sellingPrice;
    private double rateOfWeight;  // Tempo przybierania na wadze (na tydzień)
    private double growth; //czas dorastania w tygodniach
    private double food; //ilość potrzebnego jedzenia na tydzień
    private String [] typeOfFood;
    private  String typeOfBuilding;
    public double weight; //aktualna waga
    public int amount;
    public int stageGrowth; //aktualne stadium wzrostu

    public Animals(String species){
        this.species = species;
        switch (this.species) {
            case "krowa" -> {
                amount = 0;
                price = 1200.0;
                sellingPrice = 5000.0;
                weight = 40.5;
                rateOfWeight = 5.6;  // waga w kg
                growth = 81.0;
                stageGrowth = 0;
                food = 385.0;  // waga w kg
                typeOfFood = new String[]{"sałata", "pasza dla krów"};
                typeOfBuilding = "obora";
            }
            case "kura" -> {
                amount = 0;
                price = 5.0;
                sellingPrice = 10.0;
                weight = 0.42;
                rateOfWeight = 0.28;  // waga w kg
                growth = 5.0;
                stageGrowth = 0;
                food = 0.45;  // waga w kg
                typeOfFood = new String[]{"pszenica", "pasza dla kur"};
                typeOfBuilding = "kurnik";
            }
            case "świnia" ->{
                amount = 0;
                price = 250.0;
                sellingPrice = 756.0;
                weight = 2.0;
                rateOfWeight = 1.5;
                growth = 60.0;
                food = 3.5;
                typeOfFood = new String[]{"pszenica", "pasza dla świń"};
                typeOfBuilding = "chlew";
            }
                default -> System.out.println("Nie mamy takiego zwierzęcia.");
            }
        }

    public double getPrice() { return price; }

    public String getSpecies() { return species; }

    public String getTypeOfBuilding() { return typeOfBuilding; }

    public String[] getTypeOfFood() { return typeOfFood; }

    public double getGrowth() { return growth; }

    public double getFood() { return food; }

    public double getSellingPrice() { return sellingPrice; }

    public double getRateOfWeight() { return rateOfWeight; }

    @Override
    public String toString() {
        return "\n" + species +
                "\nIlość: " + amount +
                "\nTempo przybierania na wadzę (na tydzień): " + rateOfWeight + " kg" +
                "\nCzas dorastania: " + growth + " tygodni" +
                "\nAktualny tydzień wzrostu: " + stageGrowth +
                "\nAktualna waga: " + String.format("% .2f",weight) +
                "\nIlośc potrzebnego pokarmu: " + food + " kg na tydzień" +
                "\nRodzaje przyjmowanego pokarmu: " + Arrays.toString(typeOfFood) +
                "\nBudynek potrzebny do trzymania danego zwierzęcia: " + typeOfBuilding + "\n";
    }
}
