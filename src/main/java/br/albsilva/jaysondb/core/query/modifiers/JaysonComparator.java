package br.albsilva.jaysondb.core.query.modifiers;

import br.albsilva.jaysondb.core.JaysonReflection;

import java.util.Comparator;

public class JaysonComparator<D> implements Comparator<D> {

    public JaysonComparator(JaysonOrderByModifier orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public int compare(D document1, D document2) {
        Comparable valueDocument1 = (Comparable) JaysonReflection.getValueByField(orderBy.getField(), document1);
        Comparable valueDocument2 = (Comparable) JaysonReflection.getValueByField(orderBy.getField(), document2);

        return valueDocument1.compareTo(valueDocument2) * orderBy.getFactor();
    }

    private JaysonOrderByModifier orderBy;
}
