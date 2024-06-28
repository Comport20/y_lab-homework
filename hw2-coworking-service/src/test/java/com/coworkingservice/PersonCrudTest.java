package com.coworkingservice;

import com.coworkingservice.entity.*;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.memorydb.MemoryDB;
import com.coworkingservice.memorydb.PersonCRUD;

import com.coworkingservice.service.verify.Registration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonCrudTest {
    private Map<Credential, Person> personMapTable = MemoryDB.getInstance().getPersonMapTable();
    @Mock
    private EntityFamilyFabric entityFamilyFabric;
    @Mock
    private Registration registration;
    private PersonCRUD personCRUD;
    @Mock
    private Person person;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Before
    public void setUp() throws Exception {
        personMapTable.put(new Credential("test","test"),person);
        System.setOut(new PrintStream(outputStreamCaptor));
        this.personCRUD = new PersonCRUD(entityFamilyFabric);
    }
    @Test
    public void createSuccessfullyRegisteredTest(){
        Credential credential = mock(Credential.class);
        personCRUD.create(credential);
        Assertions.assertEquals("The user has been successfully registered.", outputStreamCaptor.toString().trim());
    }
    @Test
    public void createNotSuccessfullyRegisteredTest(){
        personCRUD.create(new Credential("test","test"));
        Assertions.assertEquals("Such a user already exists.", outputStreamCaptor.toString().trim());
    }
    @Test
    public void updateRecordTest(){
        Credential credential = new Credential("test","test");
        when(entityFamilyFabric.createPerson()).thenReturn(new Tenant("Recreate","person"));
        personCRUD.update(credential);
        Person person = personMapTable.get(credential);
        Assertions.assertEquals("Recreate person", person.getFirstname() + " " + person.getLastname());
    }
    @Test
    public void readWhenRecordExistsTest(){
        Assertions.assertInstanceOf(Person.class,personCRUD.read(new Credential("test","test")));
    }
    @Test
    public void readWhenRecordNotExistsTest(){
        Assertions.assertNull(personCRUD.read(new Credential("","")));
    }
    @Test
    public void deleteRecordTest(){
        Credential credential = new Credential("test","test");
        if(personMapTable.containsKey(credential)){
            personCRUD.delete(credential);
            Assertions.assertFalse(personMapTable.containsKey(credential));
        }
    }
    @After
    public void tearDown() throws Exception {
        personMapTable.clear();
    }
}
