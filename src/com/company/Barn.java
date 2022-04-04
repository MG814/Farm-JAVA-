package com.company;

public class Barn extends Buildings {
    private int capacity;

    public Barn(String name, double price) {
        super(name, price);
        capacity = 100000;
    }

    public void setCapacity(int capacity){this.capacity = capacity;}
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return "\nStodoła: " + "\nPojemność stodoły: " + String.format("%, d",capacity) + " kg\n";
    }
}
