package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Farmer {
    private final String name;
    public double money;
    public Farms myFarm;
    private final List <Plants> myPlants = new ArrayList<>();
    private final List <Buildings> myBuildings = new ArrayList<>();
    public final List <Animals> myAnimals = new ArrayList<>();

    public Farmer(String name, double money){
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public List<Buildings> getMyBuildings() { return myBuildings; }

    public List<Plants> getMyPlants() { return myPlants; }

    public void buyFarm(Farms farm){
        money -= farm.getPrice();
        myFarm = farm;
        System.out.println("Zakupiono farmę za kwotę " + String.format("%, d",farm.getPrice()) + " PLN\n");
    }

    public void buyPlant(Plants plant, double size){
        if(money >= plant.getPrice() * size){
            money -= plant.getPrice() * size;

            plant.amount = plant.getAmountPerHectare() * size + plant.amount;

            if(!myPlants.contains(plant))
                myPlants.add(plant);

            System.out.println("Kupiono " + plant.getName() + "\nNasion/sadzonek wystarczy na "+ size + " ha\n");
        }
        else
            System.out.println("Za mało pieniędzy.");
    }

    public void sow(Plants plant, double size){
        if(myFarm.getArea() - plant.sown >= size && plant.amount >= plant.getAmountPerHectare() * size){
            money -= plant.getPlantingCost() * size;
            plant.sown = plant.sown + size;
            plant.amount = plant.amount - plant.getAmountPerHectare() * size;
            System.out.println("Zasiano/zasadzono " + plant.getName() + " na obszarze " + size + " ha\n");
        }
        else
            System.out.println("Za malo ziemi aby zasadzić/zasiać lub brak wystarczającej ilości nasion/sadzonek.\n");
    }

    public void harvest(Plants plant){
        if(money >= plant.getHarvestCost() * plant.sown){
            if (plant.growStage >= plant.getGrowth()){
                money -= plant.getHarvestCost() * plant.sown;
                plant.supplies = plant.supplies + (plant.sown * plant.getYieldP());
                if(!plant.getName().equals("jabłoń"))
                    plant.sown = 0.0;
                plant.growStage = 0;
                System.out.println("Zebrano " + String.format("%, .2f", plant.supplies) + " kg\n");
            }
            else
                System.out.println("Nie gotowe do zbioru\n");
        }
        else
            System.out.println("Za mało pieniędzy.\n");
    }

    public void sellPlant(Plants plant, double amountCrops){
        if(plant.supplies >= amountCrops && plant.supplies != 0){
            money += plant.getPurchase() * amountCrops;
            plant.supplies = plant.supplies - amountCrops;
            System.out.println("Sprzedano plony za " + String.format("%, .2f", plant.getPurchase() * amountCrops ) + " PLN\n");
        }
        else
            System.out.println("Brak takiej ilości plonów.");
    }

    public void buyField(double size){
        if(money >= size * 53675.0){
            money -= size * 53675.0;
            myFarm.setArea(myFarm.getArea() + size);
            System.out.println("Zakupiono " + size + " ha ziemi za " + String.format("%, .2f",size * 53675.0)  + " PLN.\n");
        }
        else
            System.out.println("Za mało pieniędzy.\n");
    }

    public void buyNewBarn(Barn barn){
        if(money >= barn.getPrice()){
            money -= barn.getPrice();
            if(myBuildings.contains(barn)){
                barn.setCapacity(barn.getCapacity() + 100000);
                System.out.println("Powiększono pojemność stodoły o 100 000 kg\n");
            }
            else{
                myBuildings.add(barn);
                System.out.println("Kupiono stodołę o pojemności 100 000 kg\n");
            }
        }
        else
            System.out.println("Za mało pieniędzy.\n");
    }

    public void buyAnimalPen(AnimalsBuildings animBuild){
        if(money >= animBuild.getPrice() && !myBuildings.contains(animBuild)){
            money -= animBuild.getPrice();
            myBuildings.add(animBuild);
            System.out.println("Kupiono " + animBuild.getName() + " za " + String.format("%, .2f", animBuild.getPrice()) + " PLN\n");
        }
        else
            System.out.println("Za mało pieniędzy lub posiadasz już tą zgrodę dla zwierząt.\n");
    }

    public void buyAnimal(String animalSpecies, int amountAnimal){
        Animals animal = new Animals(animalSpecies);

        if(money >= animal.getPrice() * amountAnimal){
            money -= animal.getPrice() * amountAnimal;

            if(!myAnimals.contains(animal)){
                animal.amount += amountAnimal;
                myAnimals.add(animal);
            }
            else
                animal.amount = animal.amount + amountAnimal;

            System.out.println("Kupiono " + amountAnimal + " " + animal.getSpecies() + " za " + animal.getPrice() * amountAnimal + " PLN.\n");
        }
        else
            System.out.println("Za mało pieniędzy.\n");
    }

    public void sellAnimal(String animalSpacies, int amountAnimal){
        int count = 0;

        if(myAnimals.size() == 0)
            System.out.println("Nie masz żadnych zwierząt.");

        for(Animals animal : myAnimals){
            count++;

            if(animal.getSpecies().equals(animalSpacies)){
                if(animal.stageGrowth >= animal.getGrowth() && animal.amount >= amountAnimal){
                    money += animal.getSellingPrice() * amountAnimal;

                    animal.amount -= amountAnimal;

                    System.out.println("Sprzedano " + amountAnimal + " " + animalSpacies + " za " + String.format("%, .2f", animal.getSellingPrice() * amountAnimal) + " PLN\n");

                    if(animal.amount == 0){
                        myAnimals.remove(animal);
                        break;
                    }
                }
                else if(count == myAnimals.size())
                    System.out.println("Nie masz dorosłego zwierzęcia i/lub podana ilość jest za duża.\n");
            }
            else
                System.out.println("Nie masz takiego zwierzęcia.\n");
        }
    }

    @Override
    public String toString() {
        return "Gracz:\n" + "Imię: '" + name + "\nSaldo: " + String.format("%, .2f",money) + " PLN\n\n" + myFarm + "\n\n"
                + myPlants + "\n\n" + "Posiadane budynki:\n" + myBuildings + "\n\n" + myAnimals + "\n\n";
    }
}
