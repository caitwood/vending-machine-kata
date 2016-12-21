package vend;

import java.text.NumberFormat;

import static vend.Coin.isValidVendingMachineCoin;

public class VendingMachine {

    private double currentBalance = 0.0;
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
        } else throw new RuntimeException("Invalid coin detected!");
    }
}