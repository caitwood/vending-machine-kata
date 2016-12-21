package vend;

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
}
