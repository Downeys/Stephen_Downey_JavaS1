package com.company.U1M5ChallengeDowneyStephen.daos;

import com.company.U1M5ChallengeDowneyStephen.dtos.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDaoJdbcTemplateImplTest {

    @Autowired
    PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        List<Publisher> publisherList = publisherDao.getAllPublishers();

        for (Publisher p: publisherList) {
            publisherDao.deletePublisher(p.getPublisherId());
        }

    }

    @Test
    public void shouldAddGetDeletePublisher() {
        //Arrange
        Publisher theOriginalPublisher = new Publisher();
        theOriginalPublisher.setName("Monkey Book Publishers Inc.");
        theOriginalPublisher.setStreet("123 Monkey Creek Ln.");
        theOriginalPublisher.setCity("Big Forrest");
        theOriginalPublisher.setState("BF");
        theOriginalPublisher.setPostalCode("88889");
        theOriginalPublisher.setPhone("987-654-3216");
        theOriginalPublisher.setEmail("info@monkeybooks.com");

        //Act
        theOriginalPublisher = publisherDao.addPublisher(theOriginalPublisher);

        Publisher thePublisherThatWasGotten = publisherDao.getPublisher(theOriginalPublisher.getPublisherId());

        //Assert
        assertEquals(theOriginalPublisher, thePublisherThatWasGotten);

        //Act
        publisherDao.deletePublisher(theOriginalPublisher.getPublisherId());

        thePublisherThatWasGotten = publisherDao.getPublisher(theOriginalPublisher.getPublisherId());

        //Assert
        assertNull(thePublisherThatWasGotten);

    }

    @Test
    public void shouldGetAllPublishers() {
        Publisher publisher1 = new Publisher();
        publisher1.setName("Monkey Book Publishers Inc.");
        publisher1.setStreet("123 Monkey Creek Ln.");
        publisher1.setCity("Big Forrest");
        publisher1.setState("BF");
        publisher1.setPostalCode("88889");
        publisher1.setPhone("987-654-3216");
        publisher1.setEmail("info@monkeybooks.com");

        Publisher publisher2 = new Publisher();
        publisher2.setName("Elephant  Pub. LLC");
        publisher2.setStreet("76 Trunk St.");
        publisher2.setCity("Little River");
        publisher2.setState("LR");
        publisher2.setPostalCode("15687");
        publisher2.setPhone("123-456-7896");
        publisher2.setEmail("info@elephantpub.com");

        Publisher publisher3 = new Publisher();
        publisher3.setName("Zebra Media Co");
        publisher3.setStreet("654 Stripe Ridge.");
        publisher3.setCity("Savannah");
        publisher3.setState("GA");
        publisher3.setPostalCode("63215");
        publisher3.setPhone("468-753-5168");
        publisher3.setEmail("info@zebramedia.com");

        publisher1 = publisherDao.addPublisher(publisher1);

        //Act
        List<Publisher> listOfGottenPublishers = publisherDao.getAllPublishers();

        //Assert
        assertEquals(1, listOfGottenPublishers.size());

        publisher2 = publisherDao.addPublisher(publisher2);

        //Act
        listOfGottenPublishers = publisherDao.getAllPublishers();

        //Assert
        assertEquals(2, listOfGottenPublishers.size());

        //Act
        publisher3 = publisherDao.addPublisher(publisher3);
        listOfGottenPublishers = publisherDao.getAllPublishers();

        //Assert
        assertEquals(3, listOfGottenPublishers.size());

        //Act
        for (Publisher p: listOfGottenPublishers) {
            publisherDao.deletePublisher(p.getPublisherId());
        }
        listOfGottenPublishers = publisherDao.getAllPublishers();

        //Assert
        assertEquals(0, listOfGottenPublishers.size());

    }

    @Test
    public void shouldUpdatePublisher() {
        //Arrange
        Publisher publisher1 = new Publisher();
        publisher1.setName("Monkey Book Publishers Inc.");
        publisher1.setStreet("123 Monkey Creek Ln.");
        publisher1.setCity("Big Forrest");
        publisher1.setState("BF");
        publisher1.setPostalCode("88889");
        publisher1.setPhone("987-654-3216");
        publisher1.setEmail("info@monkeybooks.com");

        Publisher expectedOutputFromTest = publisherDao.addPublisher(publisher1);

        expectedOutputFromTest.setName("Penguin Prints");
        expectedOutputFromTest.setStreet("33 Iceberg Ct");
        expectedOutputFromTest.setCity("Seaside");
        expectedOutputFromTest.setState("AN");
        expectedOutputFromTest.setPostalCode("22223");
        expectedOutputFromTest.setName("159-357-9874");
        expectedOutputFromTest.setEmail("info@penguinprints.com");

        //Act
        publisherDao.updatePublisher(expectedOutputFromTest);

        Publisher actualOutPutFromTest = publisherDao.getPublisher(publisher1.getPublisherId());

        //Assert
        assertEquals(expectedOutputFromTest, actualOutPutFromTest);

    }

    @Test
    public void shouldDeletePublisher() {
        //Arrange
        Publisher publisher1 = new Publisher();
        publisher1.setName("Monkey Book Publishers Inc.");
        publisher1.setStreet("123 Monkey Creek Ln.");
        publisher1.setCity("Big Forrest");
        publisher1.setState("BF");
        publisher1.setPostalCode("88889");
        publisher1.setPhone("987-654-3216");
        publisher1.setEmail("info@monkeybooks.com");

        Publisher publisher2 = new Publisher();
        publisher2.setName("Elephant  Pub. LLC");
        publisher2.setStreet("76 Trunk St.");
        publisher2.setCity("Little River");
        publisher2.setState("LR");
        publisher2.setPostalCode("15687");
        publisher2.setPhone("123-456-7896");
        publisher2.setEmail("info@elephantpub.com");

        Publisher publisher3 = new Publisher();
        publisher3.setName("Zebra Media Co");
        publisher3.setStreet("654 Stripe Ridge.");
        publisher3.setCity("Savannah");
        publisher3.setState("GA");
        publisher3.setPostalCode("63215");
        publisher3.setPhone("468-753-5168");
        publisher3.setEmail("info@zebramedia.com");

        publisher1 = publisherDao.addPublisher(publisher1);
        publisher2 = publisherDao.addPublisher(publisher2);
        publisher3 = publisherDao.addPublisher(publisher3);

        //Act
        publisherDao.deletePublisher(publisher2.getPublisherId());
        List<Publisher> publisherList = publisherDao.getAllPublishers();

        //Assert
        assertEquals(2, publisherList.size());
        assertNotEquals(publisher2, publisherList.get(0));
        assertNotEquals(publisher2, publisherList.get(1));

        //Act
        publisherDao.deletePublisher(publisher1.getPublisherId());
        publisherList = publisherDao.getAllPublishers();

        //Assert
        assertEquals(1, publisherList.size());
        assertNotEquals(publisher1, publisherList.get(0));

        //Act
        publisherDao.deletePublisher(publisher3.getPublisherId());
        publisherList = publisherDao.getAllPublishers();

        //Assert
        assertEquals(0, publisherList.size());
    }
}