package com.company;

import java.util.Random;
import java.util.Scanner;

public class Menu {

    public void menu(Farmer f){
        Scanner scan = new Scanner(System.in);
        Random r = new Random();

        int mainMenu;
        int branchingMenu;
        int choice;
        boolean isTrue;
        double area;
        double number;
        int week = 1;
        int year = 2020;

        Plants[] plants = new Plants[]{new Plants("pszenica"),new Plants("jabłoń"),new Plants("sałata")};
        Animals[] animalsTab = new Animals[]{new Animals("krowa"), new Animals("świnia"), new Animals("kura")};

        Farms[] tabFarms = new Farms[3];

        Barn barn = new Barn("stodoła",450000.0);
        AnimalsBuildings[] animalsBuildings = new AnimalsBuildings[]{new AnimalsBuildings("obora",250000.0),
                new AnimalsBuildings("chlew",70000.0), new AnimalsBuildings("kurnik",50000.0)};

        for(int i=0;i<tabFarms.length;i++){
            double randArea = 1.0+r.nextDouble()*(10.0-1.0);
            int randprice;
            int randBuild = r.nextInt(10-1)+1;

            if(randArea > 5.0 && randBuild > 1)
                randprice = r.nextInt(1000000 - 300001) + 300001;
            else
                randprice = r.nextInt(300000 - 100000) + 100000;

            tabFarms[i] = new Farms(randprice,randBuild, (double) Math.round(randArea*100)/100);
            System.out.println(i+1 + String.format(" Cena:%, d",tabFarms[i].getPrice()) +
                    " PLN Liczba budynków: " + tabFarms[i].getNumberOfBildings() + " Powierzchnia: " + tabFarms[i].getArea() + " ha");
            System.out.println();
        }

        while(true){
            System.out.println("Wybierz farmę: ");
            choice = scan.nextInt();
            if(choice > 0 && choice < 4){
                f.buyFarm(tabFarms[choice-1]);
                break;
            }
            else
                System.out.println("Wpisz liczbę od 1 do 3.");
        }


        while(true){

            System.out.println("Tydzień: " + week + " Rok: " + year);
            System.out.println("0 - Zakończ grę");
            System.out.println("1 - Następny tydzień");
            System.out.println("2 - Rośliny");
            System.out.println("3 - Zwierzęta");
            System.out.println("4 - Kupno ziemi");
            System.out.println("5 - Kupno budynków");
            System.out.println("6 - Informacje");
            mainMenu = scan.nextInt();

            if (mainMenu == 0)
                break;

            if (mainMenu == 1){
                week+=1;
                if(week > 52){
                    week = 1;
                    year +=1;
                }

                isTrue = false;

                for (Plants plant : plants) {
                    if (plant.sown > 0) {
                        f.money = f.money - plant.getPesticidiesCost() * plant.sown;
                        plant.growStage += 1;
                        if (plant.growStage >= plant.getGrowth())
                            System.out.println(plant.getName() + ": gotowe do zbioru\n");
                    }
                }

                for(Animals animal : f.myAnimals){
                    animal.stageGrowth++;

                    for(Plants plant : f.getMyPlants()) {
                        if (animal.getTypeOfFood()[0].equals(plant.getName()) && plant.supplies >= animal.getFood()) {
                            plant.supplies -= animal.getFood();
                            isTrue = true;
                            if (animal.stageGrowth <= animal.getGrowth())
                                animal.weight += animal.getRateOfWeight();
                            break;
                        }
                    }
                    if(!isTrue){
                        if(f.money >= 80.0 * animal.amount ){
                            f.money -= 80.0 * animal.amount; //80 to uśredniona cena paszy dla jednego zwierzęcia
                            if(animal.stageGrowth <= animal.getGrowth())
                                animal.weight += animal.getRateOfWeight();
                        }
                        else{
                            animal.weight -= animal.getRateOfWeight();

                            if(animal.weight <= 0) {
                                System.out.println("Zwierzę umarło.\n");
                                f.myAnimals.remove(animal);
                                break;
                            }
                        }
                    }

                    if(animal.stageGrowth >= animal.getGrowth())
                    {
                        System.out.println("Niektóre zwierzęta dorosły.\n");
                        if(animal.getSpecies().equals("krowa")){
                            System.out.println("Sprzedano mleko za: " + String.format("% .2f",1.83 * 210 * animal.amount) + " PLN\n");
                            f.money += 1.83 * 210 * animal.amount; // 1.83 * 210 to cana razy ilośc mleka jaką 1 krowa daje w tygodniu
                        }
                        else if(animal.getSpecies().equals("kura")){
                            System.out.println("Sprzedano jajka za: " + 7 * animal.amount + " PLN\n");
                            f.money += 7 * animal.amount; //przyjeta cena to 1 PLN
                        }

                        if(animal.amount >= 2){
                            int random = r.nextInt(5-1)+1;

                            if(random == 1){
                                Animals newAnimal = new Animals(animal.getSpecies());
                                f.myAnimals.add(newAnimal);
                                newAnimal.amount++;
                                break;
                            }
                        }
                    }
                }
            }

            while(mainMenu == 2){
                System.out.println("0 - Wyjdź");
                System.out.println("1 - Kup rośliny");
                System.out.println("2 - Zasiej/zasadź");
                System.out.println("3 - Zbierz plony");
                System.out.println("4 - Sprzedaj");

                branchingMenu = scan.nextInt();

                if(branchingMenu == 0)
                    break;

                else if(branchingMenu == 1){
                    while (true){
                        System.out.println("0 - Wyjdź");
                        System.out.println("Co chcesz kupić? (1 - pszenica, 2 - jabłoń, 3 - sałata)");
                        choice = scan.nextInt();

                        if(choice <= 3 && choice > 0){
                            System.out.println("Na jaki areał potrzebujesz nasion?");
                            area = scan.nextDouble();

                            System.out.println("Całkowity koszt to: " +  String.format("%, .2f",plants[choice-1].getPrice() * area)  + " PLN");

                            System.out.println("Potwierdzasz zakup?\ny/n");
                            String yesOrNo = scan.next();

                            if(yesOrNo.equals("y"))
                                f.buyPlant(plants[choice-1],area);
                            break;
                        }
                        else if(choice == 0)
                            break;
                        else
                            System.out.println("\nWybierz 1, 2 lub 3.");
                    }
                }

                else if(branchingMenu == 2) {
                    while (true) {
                        System.out.println("0 - Wyjdź");
                        System.out.println("Co chcesz zasiać/zasadzić? (1 - pszenica, 2 - jabłoń, 3 - sałata)");
                        choice = scan.nextInt();

                        if(choice <=3 && choice > 0){

                            if (plants[choice - 1].getStartPlanting() <= week && week <= plants[choice - 1].getStopPlanting()) {
                                System.out.println("Jaki areał?");
                                area = scan.nextDouble();
                                f.sow(plants[choice - 1], area);
                                break;
                            } else
                                System.out.println("To nie jest okres siewów/sadzenia " + plants[choice - 1].getName());
                        }
                        else if(choice == 0)
                            break;
                        else
                            System.out.println("\nWybierz 1, 2 lub 3.");
                    }
                }

                else if(branchingMenu == 3){
                    while (true){
                        System.out.println("0 - Wyjdź");
                        System.out.println("Co chcesz zebrać? (1 - pszenica, 2 - jabłoń, 3 - sałata)");
                        choice = scan.nextInt();

                        if(choice <=3 && choice > 0){

                            if(plants[choice-1].sown > 0)
                                if(plants[choice-1].growStage < plants[choice-1].getGrowth()+3){
                                    f.harvest(plants[choice-1]);

                                    if(!f.getMyBuildings().contains(barn) || barn.getCapacity() <= plants[choice-1].supplies){
                                        f.sellPlant(plants[choice-1],plants[choice-1].supplies);
                                        System.out.println("Zbiory zostały sprzedane automatycznie ponieważ nie masz stodoły lub jest ona zapełniona.");
                                    }
                                }
                                else{
                                    plants[choice-1].sown = 0.0;
                                    System.out.println("Za późno na zbiory. Wszystkie plony zostały zniszczone.");
                                }
                            else
                                System.out.println("Nie zasiano/zasadzono takiej rośliny");
                            break;
                        }
                        else if(choice == 0)
                            break;
                        else
                            System.out.println("\nWybierz 1, 2 lub 3.");
                    }
                }

                else if(branchingMenu == 4){
                    while(true){
                        System.out.println("\n0 - Wyjdź");
                        System.out.println("1 - pszenica " + plants[0].supplies + " kg\tCena za 1 kg: " +
                                plants[0].getPurchase() + " PLN\n2 - jabłoń " +
                                plants[1].supplies + " kg\tCena za 1 kg: " +
                                plants[1].getPurchase() + " PLN\n3 - sałata " +
                                plants[2].supplies + " kg\tCena za 1 kg: " +
                                plants[2].getPurchase() + " PLN");
                        System.out.println("Cochcesz sprzedać?");
                        choice = scan.nextInt();

                        if(choice <=3 && choice > 0){
                            System.out.println("Ile chcesz sprzedać?\n1 - wszystko\n2 - podaj ilość");
                            number = scan.nextDouble();
                            if(number == 1)
                                f.sellPlant(plants[choice-1], plants[choice-1].supplies);
                            else if(number == 2){
                                number = scan.nextDouble();
                                f.sellPlant(plants[choice-1],number);
                            }
                            else
                                System.out.println("Proszę wpisać 1 lub 2");
                        }
                        else if(choice == 0)
                            break;
                        else
                            System.out.println("\nWybierz 1, 2 lub 3.");
                    }
                }
            }

            while(mainMenu == 3){
                System.out.println("0 - Wyjdź");
                System.out.println("1 - Kup zwierzęta");
                System.out.println("2 - Sprzedaj");

                String speciesAnimal;
                int numberAnimals;

                branchingMenu = scan.nextInt();

                if(branchingMenu == 0)
                    break;

                if(branchingMenu == 1){
                    while(true) {
                        System.out.println("Jakie zwierzę chcesz kupić? (1 - krowa, 2 - świnia, 3 - kura)");
                        choice = scan.nextInt();

                        if (choice == 1){
                            speciesAnimal = "krowa";
                            break;
                        }
                        else if (choice == 2){
                            speciesAnimal = "świnia";
                            break;
                        }
                        else if (choice == 3){
                            speciesAnimal = "kura";
                            break;
                        }
                        else
                            System.out.println("Proszę wybrać liczbę od 1 do 3.");
                    }

                        if(f.getMyBuildings().size() == 0)
                            System.out.println("Najpierw kup odpowiedni budynek: " + animalsTab[choice-1].getTypeOfBuilding() + "\n");

                        int count = 0;

                        for (Buildings building : f.getMyBuildings()) {
                            count++;
                            if(building.getName().equals(animalsTab[choice - 1].getTypeOfBuilding())){
                                System.out.println("Ile zwierzat chcesz kupić?");
                                numberAnimals = scan.nextInt();
                                f.buyAnimal(speciesAnimal,numberAnimals);
                                break;
                            }
                            else if(count == f.getMyBuildings().size())
                                System.out.println("Najpierw kup odpowiedni budynek: " + animalsTab[choice-1].getTypeOfBuilding() + "\n");
                        }
                }

                if(branchingMenu == 2){
                    while(true){
                        System.out.println("Jakie zwierzę chcesz sprzedać? (1 - krowa, 2 - świnia, 3 - kura)");
                        choice = scan.nextInt();

                        if (choice == 1){
                            speciesAnimal = "krowa";
                            break;
                        }
                        else if (choice == 2){
                            speciesAnimal = "świnia";
                            break;
                        }
                        else if (choice == 3){
                            speciesAnimal = "kura";
                            break;
                        }
                        else
                            System.out.println("Proszę wybrać liczbę od 1 do 3.");
                    }

                    System.out.println("Ile chcesz sprzedać?");
                    numberAnimals = scan.nextInt();

                    f.sellAnimal(speciesAnimal,numberAnimals);
                }
            }

            if(mainMenu == 4){
                System.out.println("Cena za hektar 53675.0 PLN");

                System.out.println("Ile ziemi chcesz kupić?");
                area = scan.nextDouble();

                f.buyField(area);
            }

            while(mainMenu == 5){
                System.out.println("Jaki budynek chcesz postawić?");
                System.out.println("0 - Wyjdź");
                System.out.println("1 - obora (250 000 PLN) , 2 - chlew (70 000 PLN), 3 - kurnik (50 000 PLN), 4 - stodoła (450 000 PLN)");
                choice = scan.nextInt();

                if(choice >= 1 && choice < 4)
                    f.buyAnimalPen(animalsBuildings[choice-1]);
                else if(choice == 4)
                    f.buyNewBarn(barn);
                else if(choice == 0)
                    break;
                else
                    System.out.println("Wprowadź liczbę od 1 do 4, aby kupić budynek.");
            }

            if (mainMenu == 6){
                System.out.println(f);
            }

            if(mainMenu == 7){
                System.out.println("Ile kasy?");
                choice = scan.nextInt();
                f.money -=choice;
            }
        }
    }
}
