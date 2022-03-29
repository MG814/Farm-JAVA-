package com.company;

import java.util.ArrayList;
import java.util.List;

public class Farmer {
    private final String name;
    private double money;
    public Farms myFarm;
    private final List <Plants> myPlants = new ArrayList<>();
    private final List <Buildings> myBuildings = new ArrayList<>();

    public Farmer(String name, double money){
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public double getMoney() { return money; }

    public void setMoney(double money) { this.money = money; }

    public List<Buildings> getMyBuildings() { return myBuildings; }

    public void buyFarm(Farms farm){
        money -= farm.getPrice();
        myFarm = farm;
        System.out.println("Zakupiono farmę za kwotę " + String.format("%, d",farm.getPrice()) + " PLN\n");
    }

    public void buyPlant(Plants plant, double size){
        if(money >= plant.getPrice() * size){
            money -= plant.getPrice() * size;

            plant.setAmount(plant.getAmountPerHectare() * size + plant.getAmount());

            if(!myPlants.contains(plant))
                myPlants.add(plant);

            System.out.println("Kupiono " + plant.getName() + "\nNasion/sadzonek wystarczy na "+ size + " ha");
        }
        else
            System.out.println("Za mało pieniędzy.");
    }

    public void sow(Plants plant, double size){
        if(myFarm.getArea() - plant.getSown() >= size && plant.getAmount() >= plant.getAmountPerHectare() * size){
            money -= plant.getPlantingCost() * size;
            plant.setSown(plant.getSown() + size);
            plant.setAmount(plant.getAmount() - plant.getAmountPerHectare() * size);
            System.out.println("Zasiano/zasadzono " + plant.getName() + " na obszarze " + size + " ha");
        }
        else
            System.out.println("Za malo ziemi aby zasadzić/zasiać lub brak wystarczającej ilości nasion/sadzonek.\n");
    }

    public void harvest(Plants plant){
        if(money >= plant.getHarvestCost() * plant.getSown()){
            if (plant.getGrowStage() >= plant.getGrowth()){
                money -= plant.getHarvestCost() * plant.getSown();
                plant.setSupplies(plant.getSupplies() + (plant.getSown() * plant.getYieldP()));
                if(!plant.getName().equals("jabłoń"))
                    plant.setSown(0.0);
                plant.setGrowStage(0);
                System.out.println("Zebrano " + String.format("%, .2f", plant.getSupplies()) + " kg");
            }
            else
                System.out.println("Nie gotowe do zbioru");
        }
        else
            System.out.println("Za mało pieniędzy.");
    }

    public void sellPlant(Plants plant, double amountCrops){
        if(plant.getSupplies() >= amountCrops && plant.getSupplies() != 0){
            money += plant.getPurchase() * amountCrops;
            plant.setSupplies(plant.getSupplies() - amountCrops);
            System.out.println("Sprzedano plony za " + String.format("%, .2f", plant.getPurchase() * amountCrops ) + " PLN");
        }
        else
            System.out.println("Brak takiej ilości plonów");
    }

    public void buyField(double size){
        if(money >= size * 53675.0){
            money -= size * 53675.0;
            myFarm.setArea(myFarm.getArea() + size);
            System.out.println("Zakupiono " + size + " ha ziemi za " + String.format("%, .2f",size * 53675.0)  + " PLN.");
        }
        else
            System.out.println("Za mało pieniędzy.");
    }

    public void buyNewBarn(Barn barn){
        if(money >= barn.getPrice()){
            money -= barn.getPrice();
            if(myBuildings.contains(barn)){
                barn.setCapacity(barn.getCapacity() + 100000);
                System.out.println("Powiększono pojemność stodoły o 100 000 kg");
            }
            else{
                myBuildings.add(barn);
                System.out.println("Kupiono stodołę o pojemności 100 000 kg");
            }
        }
        else
            System.out.println("Za mało pieniędzy.");
    }

    @Override
    public String toString() {
        return "Gracz:\n" + "Imię: '" + name + "\nSaldo: " + money + " PLN\n\n" + myFarm + "\n\n" + myPlants + "\n\n" + myBuildings + "\n\n";
    }
}
