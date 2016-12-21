package vend;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static vend.Coin.isValidVendingMachineCoin;

public class VendingMachine {

    private double currentBalance = 0.0;
    private List<Coin> coinReturn = new ArrayList<Coin>();
    private NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public String getCurrentBalance() {
        if (currentBalance > 0) {
            return formatter.format(currentBalance);
        }

        return "INSERT COIN";
    }

    public void insert(Coin amount) {
        if (isValidVendingMachineCoin(amount)) {
            currentBalance += amount.getValue();
        } else coinReturn.add(amount);
    }

    public String vend(Snack snack) {
        double change = currentBalance - snack.getCost();
        currentBalance = 0.0;

        coinReturn.addAll(Coin.convertToCoins(change));

        return "THANK YOU";
    }

    public List<Coin> getCoinReturnContents() {
        return coinReturn;
    }
}