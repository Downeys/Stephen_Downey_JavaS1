package com.company.U1M5ChallengeDowneyStephen.daos;

import com.company.U1M5ChallengeDowneyStephen.dtos.Author;
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
public class AuthorDaoJdbcTemplateImplTest {

    @Autowired
    AuthorDao authorDao;

    @Before
    public void setUp() throws Exception {
        List<Author> authorList = authorDao.getAllAuthors();

        for (Author a : authorList) {
            authorDao.deleteAuthor(a.getAuthorId());
        }

    }

    @Test
    public void shouldAddGetDeleteAuthor() {
        //Arrange
        Author theOriginalAuthor = new Author();
        theOriginalAuthor.setFirstName("George");
        theOriginalAuthor.setLastName("Washington");
        theOriginalAuthor.setStreet("42 Continental St");
        theOriginalAuthor.setCity("Philadelphia");
        theOriginalAuthor.setState("PA");
        theOriginalAuthor.setPostalCode("32165");
        theOriginalAuthor.setPhone("987-654-3216");
        theOriginalAuthor.setEmail("info@washington.com");

        //Act
        theOriginalAuthor = authorDao.addAuthor(theOriginalAuthor);

        Author theAuthorThatWasGotten = authorDao.getAuthor(theOriginalAuthor.getAuthorId());

        //Assert
        assertEquals(theOriginalAuthor, theAuthorThatWasGotten);

        //Act
        authorDao.deleteAuthor(theOriginalAuthor.getAuthorId());

        theAuthorThatWasGotten = authorDao.getAuthor(theOriginalAuthor.getAuthorId());

        //Assert
        assertNull(theAuthorThatWasGotten);

    }

    @Test
    public void shouldGetAllAuthors() {
        //Arrange
        Author author1 = new Author();
        author1.setFirstName("George");
        author1.setLastName("Washington");
        author1.setStreet("42 Continental St");
        author1.setCity("Philadelphia");
        author1.setState("PA");
        author1.setPostalCode("32165");
        author1.setPhone("987-654-3216");
        author1.setEmail("info@washington.com");

        Author author2 = new Author();
        author2.setFirstName("Harry S.");
        author2.setLastName("Truman");
        author2.setStreet("651 Lambert St");
        author2.setCity("St. Louis");
        author2.setState("MO");
        author2.setPostalCode("65498");
        author2.setPhone("654-321-9875");
        author2.setEmail("info@truman.com");

        Author author3 = new Author();
        author3.setFirstName("Bill");
        author3.setLastName("Clinton");
        author3.setStreet("9 Hot Springs Ln");
        author3.setCity("Little Rock");
        author3.setState("AR");
        author3.setPostalCode("19765");
        author3.setPhone("123-456-7896");
        author3.setEmail("info@clinton.com");


        author1 = authorDao.addAuthor(author1);

        //Act
        List<Author> listOfGottenAuthors = authorDao.getAllAuthors();

        //Assert
        assertEquals(1, listOfGottenAuthors.size());

        author2 = authorDao.addAuthor(author2);

        //Act
        listOfGottenAuthors = authorDao.getAllAuthors();

        //Assert
        assertEquals(2, listOfGottenAuthors.size());

        //Act
        author3 = authorDao.addAuthor(author3);
        listOfGottenAuthors = authorDao.getAllAuthors();

        //Assert
        assertEquals(3, listOfGottenAuthors.size());

        //Act
        for (Author a : listOfGottenAuthors) {
            authorDao.deleteAuthor(a.getAuthorId());
        }

        listOfGottenAuthors = authorDao.getAllAuthors();

        //Assert
        assertEquals(0, listOfGottenAuthors.size());

    }

    @Test
    public void shouldUpdateAuthor() {
        //Arrange
        Author author1 = new Author();
        author1.setFirstName("George");
        author1.setLastName("Washington");
        author1.setStreet("42 Continental St");
        author1.setCity("Philadelphia");
        author1.setState("PA");
        author1.setPostalCode("32165");
        author1.setPhone("987-654-3216");
        author1.setEmail("info@washington.com");

        Author expectedOutputFromTest = authorDao.addAuthor(author1);

        expectedOutputFromTest.setFirstName("George");
        expectedOutputFromTest.setLastName("Bush");
        expectedOutputFromTest.setStreet("1234 7th St");
        expectedOutputFromTest.setCity("Austin");
        expectedOutputFromTest.setState("TX");
        expectedOutputFromTest.setPostalCode("98654");
        expectedOutputFromTest.setPhone("123-456-7896");
        expectedOutputFromTest.setEmail("info@Bush.com");

        //Act
        authorDao.updateAuthor(expectedOutputFromTest);

        Author actualOutPutFromTest = authorDao.getAuthor(author1.getAuthorId());

        //Assert
        assertEquals(expectedOutputFromTest, actualOutPutFromTest);

    }

    @Test
    public void shouldDeleteAuthor() {
        //Arrange
        Author author1 = new Author();
        author1.setFirstName("George");
        author1.setLastName("Washington");
        author1.setStreet("42 Continental St");
        author1.setCity("Philadelphia");
        author1.setState("PA");
        author1.setPostalCode("32165");
        author1.setPhone("987-654-3216");
        author1.setEmail("info@washington.com");

        Author author2 = new Author();
        author2.setFirstName("Harry S.");
        author2.setLastName("Truman");
        author2.setStreet("651 Lambert St");
        author2.setCity("St. Louis");
        author2.setState("MO");
        author2.setPostalCode("65498");
        author2.setPhone("654-321-9875");
        author2.setEmail("info@truman.com");

        Author author3 = new Author();
        author3.setFirstName("Bill");
        author3.setLastName("Clinton");
        author3.setStreet("9 Hot Springs Ln");
        author3.setCity("Little Rock");
        author3.setState("AR");
        author3.setPostalCode("19765");
        author3.setPhone("123-456-7896");
        author3.setEmail("info@clinton.com");

        author1 = authorDao.addAuthor(author1);
        author2 = authorDao.addAuthor(author2);
        author3 = authorDao.addAuthor(author3);

        //Act
        authorDao.deleteAuthor(author2.getAuthorId());
        List<Author> authorList = authorDao.getAllAuthors();

        //Assert
        assertEquals(2, authorList.size());
        assertNotEquals(author2, authorList.get(0));
        assertNotEquals(author2, authorList.get(1));

        //Act
        authorDao.deleteAuthor(author1.getAuthorId());
        authorList = authorDao.getAllAuthors();

        //Assert
        assertEquals(1, authorList.size());
        assertNotEquals(author1, authorList.get(0));

        //Act
        authorDao.deleteAuthor(author3.getAuthorId());
        authorList = authorDao.getAllAuthors();

        //Assert
        assertEquals(0, authorList.size());
    }
}