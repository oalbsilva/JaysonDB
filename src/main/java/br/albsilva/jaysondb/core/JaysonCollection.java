package br.albsilva.jaysondb.core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JaysonCollection<D> {

    public JaysonCollection(File collectionFile) {
        this.collectionFile = collectionFile;
    }

    public void insertOne(D document) throws IOException {
        insertMany(Arrays.asList(document));
    }

    public void insertMany(Collection<D> documents) throws IOException {
        readCollectionFile();
        documentsCache.addAll(documents);
        writeCollectionFile();
    }

    private void readCollectionFile() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(collectionFile));
        Type TYPE = new TypeToken<List<D>>() {
        }.getType();
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
}
