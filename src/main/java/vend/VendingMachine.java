package vend;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static vend.Coin.convertToCoins;
import static vend.Coin.isValidVendingMachineCoin;
import static vend.Snack.*;

public class VendingMachine {

    private double currentBalance = 0.0;
    private NumberFormat formatter = NumberFormat.getCurrencyInstance();
    private List<Coin> coinReturn = new ArrayList<Coin>();
    private Map<Snack, Integer> inventory = new HashMap<Snack, Integer>();
    private boolean exactChangeOnly;

    public VendingMachine(int numberOfColas, int numberOfBagsOfCandy, int numberOfBagsOfChips) {
        setInventory(COLA, numberOfColas);
        setInventory(CANDY, numberOfBagsOfCandy);
        setInventory(CHIPS, numberOfBagsOfChips);
    }

    public String getCurrentBalance() {
        if (currentBalance > 0) {
            return formatter.format(currentBalance);
        } else if (exactChangeOnly) {
            return "EXACT CHANGE ONLY";
        } else return "INSERT COIN";
    }

    public void insert(Coin amount) {
        if (isValidVendingMachineCoin(amount)) {
            currentBalance += amount.getValue();
        } else coinReturn.add(amount);
    }

    public String vend(Snack snack) {
        Integer numberOfSnacksInStock = inventory.get(snack);

        if (numberOfSnacksInStock > 0) {
            if (!exactChangeOnly) {
                double change = currentBalance - snack.getCost();
                makeChange(change);
            }

            setInventory(snack, numberOfSnacksInStock - 1);
            currentBalance = 0.0;

            return "THANK YOU";
        } else return "SOLD OUT";
    }

    public List<Coin> getCoinReturnContents() {
        return coinReturn;
    }

    public void returnCoins() {
        makeChange(currentBalance);

        currentBalance = 0.0;
    }

    private void makeChange(double change) {
        coinReturn.addAll(convertToCoins(change));
    }

    protected void setInventory(Snack snack, int numberInStock) {
        inventory.put(snack, numberInStock);
    }

    public void setExactChangeOnly(boolean exactChangeOnly) {
        this.exactChangeOnly = exactChangeOnly;
    }
}