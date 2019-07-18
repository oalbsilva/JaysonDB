package br.albsilva.jaysondb.core.query.modifiers;

public class JaysonOrderByModifier {

    public JaysonOrderByModifier(String field, JaysonOrder order) {
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return this.field;
    }

    public int getFactor() {
        return this.order.getFactor();
    }

    private String field;
    private JaysonOrder order;
}
