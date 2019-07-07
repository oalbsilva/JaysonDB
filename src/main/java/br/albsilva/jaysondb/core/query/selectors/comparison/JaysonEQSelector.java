package br.albsilva.jaysondb.core.query.selectors.comparison;

import br.albsilva.jaysondb.core.query.selectors.JaysonQuerySelectorComparation;

public class JaysonEQSelector extends JaysonQuerySelectorComparation {

    public JaysonEQSelector(String field, Object value) {
        super(field, value);
    }

    @Override
    public boolean validate(Object actual) {
        return value.equals(actual);
    }
}
