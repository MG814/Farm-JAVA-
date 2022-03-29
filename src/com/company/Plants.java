package com.company;

public class Plants {
    private double supplies; // ilośc posiadanych plonów w kg
    private double price; // koszt zakupu nasion/sadzonek na 1 hektar
    private final String name;
    private double sown; // ilość obsianych hektarów
    private double amount; // ilosc posiadanych nasion/sadzonek
    private double growth; // okres od posadzenia do zbiorów w tygodniach
    private double yieldP; // Jest to przykładowa wydajność (wartośc podana w kilogramach na hektar)
    private int growStage;
    private double purchase; // cena za kilogram
    private int startPlanting; // poczatek okresu siewów
    private int stopPlanting; // koniec okresu siewów
    private double plantingCost;
    private double pesticidiesCost;
    private double harvestCost;
    private String plantingSeason;
    private int amountPerHectare; // ilość nasion/sadzonek potrzebnych na 1 ha


    public Plants(String name) {
        this.name = name;
        // okres siewów
        switch (this.name) {
            case "pszenica" -> {
                amount = 0.0;
                supplies = 0.0;
                sown = 0.0;
                price = 252.0; // cena za 140 kg
                amountPerHectare = 140;
                plantingCost = 1454.5;
                pesticidiesCost = 331.79;
                harvestCost = 390.0;
                yieldP = 7500.0;
                growth = 4.0;
                growStage = 0;
                startPlanting = 38;
                stopPlanting = 40;
                plantingSeason = "Okres siewów: od 38 do 40 tygodnia roku.";
                purchase = 0.94;
            }
            case "jabłoń" -> {
                amount = 0.0;
                supplies = 0.0;
                sown = 0.0;
                price = 20000.0; // cena za 2000 sadzonek
                amountPerHectare = 2000;
                plantingCost = 13138.97;
                pesticidiesCost = 1347.99;
                harvestCost = 4063.0;
                yieldP = 40000.0; // Jest to przykładowa wydajność (wartośc podana w kilogramach na hektar)
                growth = 159.0;   //159.0;
                growStage = 0;
                startPlanting = 38;
                stopPlanting = 46;
                plantingSeason = "Okres sadzenia jabloni: od 38 do 46 tygodnia roku."; // do pierwszych przymrosków
                purchase = 0.60;
            }
            case "sałata" -> {
                amount = 0.0;
                supplies = 0.0;
                sown = 0.0;
                price = 1196.0; //cena za 400 g
                amountPerHectare = 400;
                plantingCost = 2935.5;
                pesticidiesCost = 1572.9;
                harvestCost = 13300.0;
                yieldP = 17850.0; // Jest to przykładowa wydajność podana w kilogramach przy założeniu, że jedna główka sałaty waży 210 g)
                growth = 6.0;
                growStage = 0;
                startPlanting = 9;
                stopPlanting = 30;
                plantingSeason = "Okres siania sałaty: od 9 do 30 tygodnia roku.";
                purchase = 1.30;
            }
            default -> System.out.println("Nie mamy takiej rośliny.");
        }
    }

    public String getName() { return name; }

    public double getSown() { return sown; }

    public void setSown(double sown) { this.sown = sown; }

    public double getPrice() { return price; }

    public void setAmount(double amount) { this.amount = amount; }

    public double getAmount() {
        return amount;
    }

    public double getGrowth() { return growth; }

    public double getSupplies() { return supplies; }

    public void setSupplies(double supplies) { this.supplies = supplies; }

    public double getYieldP() { return yieldP; }

    public void setGrowStage(int growStage) { this.growStage = growStage; }

    public int getGrowStage() { return growStage; }

    public double getPurchase() { return purchase; }

    public int getStartPlanting() { return startPlanting; }

    public int getStopPlanting() { return stopPlanting; }

    public double getHarvestCost() { return harvestCost; }

    public double getPesticidiesCost() { return pesticidiesCost; }

    public double getPlantingCost() { return plantingCost; }

    public int getAmountPerHectare() { return amountPerHectare; }

    @Override
    public String toString() {
        return  "\n\n" + name +
                "\nPosiadane plony: " + String.format("%, .2f",supplies) + " kg" +
                "\nZasiano/zasadzono: " + sown + " ha" +
                "\nIlość nasion/sadzonek: " + amount +
                "\nNa 1 hektar potrzeba: " + amountPerHectare +
                "\nOkres dojrzewania (w tygodniach): " + growth +
                "\n" + plantingSeason +
                "\nWydajność (kg/ha): " + yieldP +
                "\nCena za 1 kg plonów: " + purchase + " PLN";
    }
}
