package br.albsilva.jaysondb.core.query.selectors.logical;

import br.albsilva.jaysondb.core.query.JaysonQuery;

public class JaysonANDSelector implements JaysonQuery {
    @Override
    public boolean validate(Object value) {
        return false;
    }
}
