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

        int numberOfQuarters = (int)(value / QUARTER.getValue());
        for (int i = 0; i < numberOfQuarters; i++) {
            coins.add(QUARTER);
        }
        value -= (numberOfQuarters * QUARTER.getValue());


        int numberOfDimes = (int)(value / DIME.getValue());
        for (int i = 0; i < numberOfDimes; i++) {
            coins.add(DIME);
        }
        value -= (numberOfDimes * DIME.getValue());


        int numberOfNickels = (int)(value / NICKEL.getValue());
        for (int i = 0; i < numberOfNickels; i++) {
            coins.add(NICKEL);
        }
        value -= (numberOfNickels * NICKEL.getValue());

        int numberOfPennies = (int)(value / PENNY.getValue());
        for (int i = 0; i < numberOfPennies; i++) {
            coins.add(PENNY);
        }

        return coins;
    }
}
