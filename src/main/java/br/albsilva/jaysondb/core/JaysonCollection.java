package br.albsilva.jaysondb.core;

import br.albsilva.jaysondb.core.query.JaysonQuery;
import br.albsilva.jaysondb.core.query.JaysonQueryBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JaysonCollection<D> {

    public JaysonCollection(File collectionFile, Class<D> type) {
        this.collectionFile = collectionFile;
        this.type = type;
    }

    public void insertOne(D document) throws IOException {
        insertMany(Arrays.asList(document));
    }

    public void insertMany(Collection<D> documents) throws IOException {
        readCollectionFile();
        documentsCache.addAll(documents);
        writeCollectionFile();
    }

    public List<D> find() throws FileNotFoundException {
        readCollectionFile();
        return documentsCache;
    }

    public List<D> find(JaysonQuery query) throws FileNotFoundException {
        readCollectionFile();
        JaysonQueryBuilder queryBuilder = new JaysonQueryBuilder<D>(query, documentsCache);
        return queryBuilder.list();
    }

    private void readCollectionFile() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(collectionFile));
        Type TYPE = TypeToken.getParameterized(List.class, this.type).getType();
        documentsCache = gson.fromJson(reader, TYPE);
    }

    private void writeCollectionFile() throws IOException {
        try (FileWriter writer = new FileWriter(collectionFile)) {
            gson.toJson(documentsCache, writer);
            writer.flush();
        }
    }

    private File collectionFile;
    private List<D> documentsCache;
    private Gson gson = new Gson();
    private final Class<D> type;
}