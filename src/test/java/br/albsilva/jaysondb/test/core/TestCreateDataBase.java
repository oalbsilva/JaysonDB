package br.albsilva.jaysondb.test.core;

import br.albsilva.jaysondb.JaysonClient;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class TestCreateDataBase extends Assert {

    @Test
    public void createDataBaseHappyDay() {
        JaysonClient.getDatabase(DB_NAME);
        File db = new File(PATH_DB_CREATED);
        assertTrue(db.exists());

        JaysonClient.dropDataBase(DB_NAME);
        assertFalse(db.exists());
    }

    @Test
    public void getDataBaseThatAlreadyExists() {
        JaysonClient.getDatabase(DB_NAME);
        JaysonClient.getDatabase(DB_NAME);

        File db = new File(PATH_DB_CREATED);
        assertTrue(db.exists());

        JaysonClient.dropDataBase(DB_NAME);
        assertFalse(db.exists());
    }

    private String DB_NAME = "my_db";
    private String PATH_DB_CREATED = "my_db_jaysondb";
    
}
