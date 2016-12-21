package vend;

import static vend.Coin.isValidVendingMachineCoin;

public class VendingMachine {

    private double currentBalance = 0.0;

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void insert(Coin amount) {
        if (isValidVendingMachineCoin(amount)) {
            currentBalance += amount.getValue();
        } else throw new RuntimeException("Invalid coin detected!");
    }
}