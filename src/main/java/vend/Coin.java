package vend;

import java.util.ArrayList;
import java.util.List;

public enum Coin {
    QUARTER(.25),
    DIME(.10),
    NICKEL(.05),
    PENNY(.01);

    private double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static boolean isValidVendingMachineCoin(Coin coin) {
        return !coin.equals(PENNY);
    }

    public static List<Coin> convertToCoins(double value) {
        List<Coin> coins = new ArrayList<Coin>();
        double currentValue = value;

        List<Coin> quarters = convertToCoins(QUARTER, currentValue);
        coins.addAll(quarters);
        currentValue -= quarters.size() * QUARTER.getValue();


        List<Coin> dimes = convertToCoins(DIME, currentValue);
        coins.addAll(dimes);
        currentValue -= dimes.size() * DIME.getValue();


        List<Coin> nickels = convertToCoins(NICKEL, currentValue);
        coins.addAll(nickels);
        currentValue -= nickels.size() * NICKEL.getValue();


        List<Coin> pennies = convertToCoins(PENNY, currentValue);
        coins.addAll(pennies);

        return coins;
    }

    private static List<Coin> convertToCoins(Coin type, double value) {
        List<Coin> coins = new ArrayList<Coin>();

        int numberOfCoins = (int)(value / type.getValue());
        for (int i = 0; i < numberOfCoins; i++) {
            coins.add(type);
        }

        return coins;
    }
}
