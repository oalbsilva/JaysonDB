package br.albsilva.jaysondb.core.query.modifiers;

public enum JaysonOrder {
    DESCENDING(-1), ASCENDING(1);

    public int getFactor() {
        return this.factor;
    }

    JaysonOrder(int factor) {
        this.factor = factor;
    }

    private int factor;
}
