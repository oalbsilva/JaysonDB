package br.albsilva.jaysondb.core.query.selectors;

import br.albsilva.jaysondb.core.query.JaysonQuery;

public abstract class JaysonQuerySelectorComparation implements JaysonQuery {

    public JaysonQuerySelectorComparation(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public String getField(){
        return this.field;
    }

    protected String field;
    protected Object value;
}
