package com.company;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    public void menu(Farmer f){
        Scanner scan = new Scanner(System.in);
        Random r = new Random();

        int mainMenu;
        int choice;
        double area;
        double number;
        int week = 1;
        int year = 2020;

        Plants[] plants = new Plants[]{new Plants("pszenica"),new Plants("jabłoń"),new Plants("sałata")};
        Farms[] tabFarms = new Farms[3];
        Barn barn = new Barn("stodoła", 450000.0);

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

        System.out.println("Wybierz farmę: ");
        f.buyFarm(tabFarms[scan.nextInt()-1]);

        while(true){

            System.out.println("Tydzień: " + week + " Rok: " + year);
            System.out.println("0 - Zakończ grę");
            System.out.println("1 - Następny tydzień");
            System.out.println("2 - Rośliny");
            System.out.println("3 - Kupno ziemi");
            System.out.println("4 - Kupno budynków");
            System.out.println("5 - Informacje");
            mainMenu = scan.nextInt();

            if (mainMenu == 0)
                break;

            if (mainMenu == 1){
                week+=1;
                if(week > 52){
                    week = 1;
                    year +=1;
                }

                for (Plants plant : plants) {
                    if (plant.getSown() > 0) {
                        f.setMoney(f.getMoney() - plant.getPesticidiesCost() * plant.getSown());
                        plant.setGrowStage(plant.getGrowStage()+1);
                        if (plant.getGrowStage() >= plant.getGrowth())
                            System.out.println(plant.getName() + ": gotowe do zbioru");
                    }
                }
            }

            while(mainMenu == 2){
                System.out.println("0 - Wyjdź");
                System.out.println("1 - Kup rośliny");
                System.out.println("2 - Zasiej/zasadź");
                System.out.println("3 - Zbierz plony");
                System.out.println("4 - Sprzedaj");

                int branchingMenu = scan.nextInt();

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

                            System.out.println("Całkowity koszt to: " + plants[choice-1].getPrice() * area + " PLN");

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

                            if(plants[choice-1].getSown() > 0)
                                if(plants[choice-1].getGrowStage() < plants[choice-1].getGrowth()+3){
                                    f.harvest(plants[choice-1]);

                                    if(!f.getMyBuildings().contains(barn) || barn.getCapacity() <= plants[choice-1].getSupplies())
                                        f.sellPlant(plants[choice-1],plants[choice-1].getSupplies());
                                }
                                else{
                                    plants[choice-1].setSown(0.0);
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
                        System.out.println("Cochcesz sprzedać?");
                        System.out.println("\n1 - pszenica " + plants[0].getSupplies() + " kg\tCena za 1 kg: " +
                                plants[0].getPurchase() + " PLN\n2 - jabłoń " +
                                plants[1].getSupplies() + " kg\tCena za 1 kg: " +
                                plants[1].getPurchase() + " PLN\n3 - sałata " +
                                plants[2].getSupplies() + " kg\tCena za 1 kg: " +
                                plants[2].getPurchase() + " PLN");
                        choice = scan.nextInt();

                        if(choice <=3 && choice > 0){
                            System.out.println("Ile chcesz sprzedać?\n1 - wszystko\n2 - podaj ilość");
                            number = scan.nextDouble();
                            if(number == 1)
                                f.sellPlant(plants[choice-1], plants[choice-1].getSupplies());
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

            if(mainMenu == 3){
                System.out.println("Cena za hektar 53675.0 PLN");
                System.out.println("Ile ziemi chcesz kupić?");
                area = scan.nextDouble();
                f.buyField(area);
            }

            if(mainMenu == 4){
                System.out.println("Jaki budynek chcesz postawić?");
                System.out.println("1 - Stodoła (450 000 PLN), 2 - Chlew, 3 - Obora, 4 - Kurnik");
                choice = scan.nextInt();

                if(choice == 1){
                    f.buyNewBarn(barn);
                }
            }

            if (mainMenu == 5){
                System.out.println(f);
            }
        }
    }
}
