package vend;

public enum Snack {
    COLA(1),
    CHIPS(.5),
    CANDY(.65);

    private double cost;

    Snack(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}
