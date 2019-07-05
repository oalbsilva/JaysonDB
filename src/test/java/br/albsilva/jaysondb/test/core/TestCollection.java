package br.albsilva.jaysondb.test.core;

import br.albsilva.jaysondb.JaysonClient;
import br.albsilva.jaysondb.core.JaysonCollection;
import br.albsilva.jaysondb.core.JaysonDatabase;
import br.albsilva.jaysondb.test.model.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestCollection extends Assert {


    @Before
    public void createDataBase() {
        db = JaysonClient.getDatabase(DB_NAME);
    }

    // TODO dropDataBase not working when have collections files.
    @After
    public void dropDataBase() {
        JaysonClient.dropDataBase(DB_NAME);
    }

    @Test
    public void getCollectionHappyDay() {
        try {
            db.getCollection("person");
            File db = new File(PATH_PERSON_COLLECTION_NAME);
            assertTrue(db.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertPersonOnCollection() {
        try {
            JaysonCollection<Person> personCollection = db.getCollection("person");
            String jsonTextGenerate = Files.lines(Paths.get(PATH_PERSON_COLLECTION_NAME)).findFirst().get();
            assertEquals("[]", jsonTextGenerate);

            personCollection.insertOne(createPersonAlexandre());
            personCollection.insertOne(createPersonSonia());

            jsonTextGenerate = Files.lines(Paths.get(PATH_PERSON_COLLECTION_NAME)).findFirst().get();
            assertEquals("[{\"name\":\"Alexandre Silva\",\"age\":27.0},{\"name\":\"Sonia Mara\",\"age\":56}]", jsonTextGenerate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Person createPersonAlexandre() {
        Person person = new Person();
        person.setName("Alexandre Silva");
        person.setAge(27);
        return person;
    }

    private Person createPersonSonia() {
        Person person = new Person();
        person.setName("Sonia Mara");
        person.setAge(56);
        return person;
    }

    private JaysonDatabase db;
    private String DB_NAME = "my_db";
    private String PATH_PERSON_COLLECTION_NAME = "my_db_jaysondb/person.collection.json";
}
