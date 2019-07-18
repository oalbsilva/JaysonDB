package br.albsilva.jaysondb.core;

import java.lang.reflect.Field;

public class JaysonReflection {
    public static Object getValueByField(String fieldName, Object document) {
        try {
            String[] treePath = fieldName.split("\\.", 2);
            Field field = document.getClass().getDeclaredField(treePath[0]);
            field.setAccessible(true);
            Object valueField = field.get(document);
            if (treePath.length == 1)
                return valueField;
            return getValueByField(treePath[1], valueField);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
