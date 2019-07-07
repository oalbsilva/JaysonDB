package br.albsilva.jaysondb.core.query;

import br.albsilva.jaysondb.core.query.selectors.JaysonQuerySelectorComparation;
import br.albsilva.jaysondb.core.query.selectors.logical.JaysonANDSelector;
import com.google.gson.internal.LinkedTreeMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JaysonQueryBuilder<D> {

    public JaysonQueryBuilder(JaysonQuery query, List documentsCache) {
        this.query = query;
        this.documentsCache = documentsCache;
    }

    public List<D> list() {
        return getValidDocumentsByQuery();
    }

    private List<D> getValidDocumentsByQuery() {
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
        Object value = getValueByField(selector.getField(), document);
        return selector.validate(value);
    }

    private Object getValueByField(String fieldName, D document) {
        try {
            Field field = document.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private JaysonQuery query;
    private List<D> documentsCache;
}
