package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj imię: ");
        Farmer f = new Farmer(scan.nextLine(),300000000.0);
        System.out.println(f.getName());

        Menu menu = new Menu();
        menu.menu(f);
        System.out.println(f.myFarm.getArea());
    }
}
