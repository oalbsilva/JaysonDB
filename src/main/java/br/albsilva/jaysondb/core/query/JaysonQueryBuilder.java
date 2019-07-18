package br.albsilva.jaysondb.core.query;

import br.albsilva.jaysondb.core.JaysonReflection;
import br.albsilva.jaysondb.core.query.modifiers.JaysonComparator;
import br.albsilva.jaysondb.core.query.modifiers.JaysonOrderByModifier;
import br.albsilva.jaysondb.core.query.selectors.JaysonQuerySelectorComparation;
import br.albsilva.jaysondb.core.query.selectors.logical.JaysonANDSelector;
import com.google.gson.internal.LinkedTreeMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JaysonQueryBuilder<D> {

    public JaysonQueryBuilder(JaysonQuery query, JaysonOrderByModifier orderBy, List documentsCache) {
        this.query = query;
        this.documentsCache = documentsCache;
        this.orderBy = orderBy;
    }

    public List<D> list() {

        List<D> documentsValid = getValidDocumentsByQuery();
        if (orderBy == null)
            return documentsValid;

        JaysonComparator jaysonComparator = new JaysonComparator(orderBy);
        documentsValid.sort(jaysonComparator);
        return documentsValid;
    }

    private List<D> getValidDocumentsByQuery() {
        if (query == null)
            return documentsCache;

        List<D> results = new ArrayList<>();
        for (D document : documentsCache) {
            if (validateDocument(query, document))
                results.add(document);
        }
        return results;
    }

    private boolean validateDocument(JaysonQuery query, D document) {
        if (query instanceof JaysonANDSelector) {
        }
        JaysonQuerySelectorComparation selector = (JaysonQuerySelectorComparation) query;
        Object value = JaysonReflection.getValueByField(selector.getField(), document);
        return selector.validate(value);
    }

    private JaysonQuery query;
    private JaysonOrderByModifier orderBy;
    private List<D> documentsCache;
}
