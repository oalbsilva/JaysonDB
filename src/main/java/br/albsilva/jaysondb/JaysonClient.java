package br.albsilva.jaysondb;

import br.albsilva.jaysondb.core.JaysonDatabase;

import java.io.File;

public class JaysonClient {

    public static JaysonDatabase getDatabase(String url) {
        String dbPath = url + JAYSON_NAME_DIR;
        File db = new File(dbPath);

        if (!db.exists())
            db.mkdir();
        return new JaysonDatabase(dbPath);
    }

    public static void dropDataBase(String url) {
        File db = new File(url + JAYSON_NAME_DIR);
        if (db.exists())
            dropCollections(db);
    }

    private static void dropCollections(File db) {
        for (File file : db.listFiles())
            file.delete();
        db.delete();
    }

    private static String JAYSON_NAME_DIR = "_jaysondb";
}
