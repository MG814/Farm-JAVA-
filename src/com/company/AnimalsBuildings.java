package com.company;

public class AnimalsBuildings  extends Buildings{

    public int numberOfAnimals;

    public AnimalsBuildings(String name, double price) {
        super(name, price);

        switch (name) {
            case "chlew" -> numberOfAnimals = 50;
            case "obora" -> numberOfAnimals = 25;
            case "kurnik" -> numberOfAnimals = 100;
        }
    }

    @Override
    public String toString() {
        return "\n" + getName() +
                "\nPojemnośc budynku: " + numberOfAnimals + "\n";
    }
}
