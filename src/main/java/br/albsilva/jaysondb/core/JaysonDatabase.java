package br.albsilva.jaysondb.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JaysonDatabase {

    public JaysonDatabase(String dbDir){
        this.dbDir = dbDir;
    }

    public String getName(){
        return dbDir;
    }

    public JaysonCollection getCollection(String name, Class type) throws IOException {
        String collectionFilePath = String.format("%s/%s.collection.json", dbDir, name);
        File collectionFile = new File(collectionFilePath);
        if(!collectionFile.exists())
            createCollection(collectionFile);
        return new JaysonCollection(collectionFile, type);
    }

    private void createCollection(File config) throws IOException {
        try(FileWriter writer = new FileWriter(config)) {
            Gson gson = new Gson();
            gson.toJson(new JsonArray(), writer);
            writer.flush();
        }
    }

    private String dbDir;
}
