package com.company.U1M5ChallengeDowneyStephen.daos;

import com.company.U1M5ChallengeDowneyStephen.dtos.Author;
import com.company.U1M5ChallengeDowneyStephen.dtos.Book;
import com.company.U1M5ChallengeDowneyStephen.dtos.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoJdbcTemplateImplTest {

    @Autowired
    PublisherDao publisherDao;

    @Autowired
    AuthorDao authorDao;

    @Autowired
    BookDao bookDao;

    @Before
    public void setUp() throws Exception {
        List<Book> bookList = bookDao.getAllBooks();
        List<Author> authorList = authorDao.getAllAuthors();
        List<Publisher> publisherList = publisherDao.getAllPublishers();

        for (Book b: bookList) {
            bookDao.deleteBook(b.getBookId());
        }

        for (Author a: authorList) {
            authorDao.deleteAuthor(a.getAuthorId());
        }

        for (Publisher p: publisherList) {
            publisherDao.deletePublisher(p.getPublisherId());
        }

    }

    @Test
    public void shouldAddGetDeleteBook() {
        //Arrange
        Publisher theOriginalPublisher = new Publisher();
        theOriginalPublisher.setName("Monkey Book Publishers Inc.");
        theOriginalPublisher.setStreet("123 Monkey Creek Ln.");
        theOriginalPublisher.setCity("Big Forrest");
        theOriginalPublisher.setState("BF");
        theOriginalPublisher.setPostalCode("88889");
        theOriginalPublisher.setPhone("987-654-3216");
        theOriginalPublisher.setEmail("info@monkeybooks.com");

        Author theOriginalAuthor = new Author();
        theOriginalAuthor.setFirstName("George");
        theOriginalAuthor.setLastName("Washington");
        theOriginalAuthor.setStreet("42 Continental St");
        theOriginalAuthor.setCity("Philadelphia");
        theOriginalAuthor.setState("PA");
        theOriginalAuthor.setPostalCode("32165");
        theOriginalAuthor.setPhone("987-654-3216");
        theOriginalAuthor.setEmail("info@washington.com");

        theOriginalPublisher = publisherDao.addPublisher(theOriginalPublisher);
        theOriginalAuthor = authorDao.addAuthor(theOriginalAuthor);

        Book theOriginalBook = new Book();
        theOriginalBook.setIsbn("HIS670");
        theOriginalBook.setPublishDate(LocalDate.of(1990,3,12));
        theOriginalBook.setAuthorId(theOriginalAuthor.getAuthorId());
        theOriginalBook.setTitle("Book Number One");
        theOriginalBook.setPublisherId(theOriginalPublisher.getPublisherId());
        theOriginalBook.setPrice(new BigDecimal("1.05"));

        //Act
        theOriginalBook = bookDao.addBook(theOriginalBook);

        Book theBookThatWasGotten = bookDao.getBook(theOriginalBook.getBookId());

        //Assert
        assertEquals(theOriginalBook, theBookThatWasGotten);

        //Act
        bookDao.deleteBook(theOriginalBook.getBookId());

        theBookThatWasGotten = bookDao.getBook(theOriginalBook.getBookId());

        //Assert
        assertNull(theBookThatWasGotten);

    }

    @Test
    public void shouldGetAllBooks() {
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

        publisher1 = publisherDao.addPublisher(publisher1);
        publisher2 = publisherDao.addPublisher(publisher2);
        publisher3 = publisherDao.addPublisher(publisher3);
        author1 = authorDao.addAuthor(author1);
        author2 = authorDao.addAuthor(author2);
        author3 = authorDao.addAuthor(author3);

        Book testBook1 = new Book();
        testBook1.setIsbn("HIS670");
        testBook1.setPublishDate(LocalDate.of(1990,3,12));
        testBook1.setAuthorId(author1.getAuthorId());
        testBook1.setTitle("Book Number One");
        testBook1.setPublisherId(publisher1.getPublisherId());
        testBook1.setPrice(new BigDecimal("1.05"));

        Book testBook2 = new Book();
        testBook2.setIsbn("SCI126");
        testBook2.setPublishDate(LocalDate.of(1690,12,31));
        testBook2.setAuthorId(author2.getAuthorId());
        testBook2.setTitle("Book Number Two");
        testBook2.setPublisherId(publisher2.getPublisherId());
        testBook2.setPrice(new BigDecimal("42.99"));

        Book testBook3 = new Book();
        testBook3.setIsbn("MAT964");
        testBook3.setPublishDate(LocalDate.of(2001,6,23));
        testBook3.setAuthorId(author3.getAuthorId());
        testBook3.setTitle("Book Number One");
        testBook3.setPublisherId(publisher3.getPublisherId());
        testBook3.setPrice(new BigDecimal("27.99"));

        testBook1 = bookDao.addBook(testBook1);

        //Act
        List<Book> listOfGottenBooks = bookDao.getAllBooks();

        //Assert
        assertEquals(1, listOfGottenBooks.size());

        testBook2 = bookDao.addBook(testBook2);

        //Act
        listOfGottenBooks = bookDao.getAllBooks();

        //Assert
        assertEquals(2, listOfGottenBooks.size());

        //Act
        testBook3 = bookDao.addBook(testBook3);
        listOfGottenBooks = bookDao.getAllBooks();

        //Assert
        assertEquals(3, listOfGottenBooks.size());

        //Act
        for (Book b: listOfGottenBooks) {
            bookDao.deleteBook(b.getBookId());
        }
        listOfGottenBooks = bookDao.getAllBooks();

        //Assert
        assertEquals(0, listOfGottenBooks.size());

    }

    @Test
    public void shouldUpdateBook() {
        //Arrange
        Publisher theOriginalPublisher = new Publisher();
        theOriginalPublisher.setName("Monkey Book Publishers Inc.");
        theOriginalPublisher.setStreet("123 Monkey Creek Ln.");
        theOriginalPublisher.setCity("Big Forrest");
        theOriginalPublisher.setState("BF");
        theOriginalPublisher.setPostalCode("88889");
        theOriginalPublisher.setPhone("987-654-3216");
        theOriginalPublisher.setEmail("info@monkeybooks.com");

        Author theOriginalAuthor = new Author();
        theOriginalAuthor.setFirstName("George");
        theOriginalAuthor.setLastName("Washington");
        theOriginalAuthor.setStreet("42 Continental St");
        theOriginalAuthor.setCity("Philadelphia");
        theOriginalAuthor.setState("PA");
        theOriginalAuthor.setPostalCode("32165");
        theOriginalAuthor.setPhone("987-654-3216");
        theOriginalAuthor.setEmail("info@washington.com");

        theOriginalPublisher = publisherDao.addPublisher(theOriginalPublisher);
        theOriginalAuthor = authorDao.addAuthor(theOriginalAuthor);

        Book theOriginalBook = new Book();
        theOriginalBook.setIsbn("HIS670");
        theOriginalBook.setPublishDate(LocalDate.of(1990,3,12));
        theOriginalBook.setAuthorId(theOriginalAuthor.getAuthorId());
        theOriginalBook.setTitle("Book Number One");
        theOriginalBook.setPublisherId(theOriginalPublisher.getPublisherId());
        theOriginalBook.setPrice(new BigDecimal("1.05"));

        Book expectedOutputFromTest = bookDao.addBook(theOriginalBook);
        expectedOutputFromTest.setIsbn("FNA987");
        expectedOutputFromTest.setPublishDate(LocalDate.of(2013,4,19));
        expectedOutputFromTest.setAuthorId(theOriginalAuthor.getAuthorId());
        expectedOutputFromTest.setTitle("A Modified Book of One");
        expectedOutputFromTest.setPublisherId(theOriginalPublisher.getPublisherId());
        expectedOutputFromTest.setPrice(new BigDecimal("111.11"));

        //Act
        bookDao.updateBook(expectedOutputFromTest);

        Book actualOutPutFromTest = bookDao.getBook(theOriginalBook.getBookId());

        //Assert
        assertEquals(expectedOutputFromTest, actualOutPutFromTest);

    }

    @Test
    public void shouldDeleteBook() {
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

        publisher1 = publisherDao.addPublisher(publisher1);
        publisher2 = publisherDao.addPublisher(publisher2);
        publisher3 = publisherDao.addPublisher(publisher3);
        author1 = authorDao.addAuthor(author1);
        author2 = authorDao.addAuthor(author2);
        author3 = authorDao.addAuthor(author3);

        Book testBook1 = new Book();
        testBook1.setIsbn("HIS670");
        testBook1.setPublishDate(LocalDate.of(1990,3,12));
        testBook1.setAuthorId(author1.getAuthorId());
        testBook1.setTitle("Book Number One");
        testBook1.setPublisherId(publisher1.getPublisherId());
        testBook1.setPrice(new BigDecimal("1.05"));

        Book testBook2 = new Book();
        testBook2.setIsbn("SCI126");
        testBook2.setPublishDate(LocalDate.of(1690,12,31));
        testBook2.setAuthorId(author2.getAuthorId());
        testBook2.setTitle("Book Number Two");
        testBook2.setPublisherId(publisher2.getPublisherId());
        testBook2.setPrice(new BigDecimal("42.99"));

        Book testBook3 = new Book();
        testBook3.setIsbn("MAT964");
        testBook3.setPublishDate(LocalDate.of(2001,6,23));
        testBook3.setAuthorId(author3.getAuthorId());
        testBook3.setTitle("Book Number One");
        testBook3.setPublisherId(publisher3.getPublisherId());
        testBook3.setPrice(new BigDecimal("27.99"));

        testBook1 = bookDao.addBook(testBook1);
        testBook2 = bookDao.addBook(testBook2);
        testBook3 = bookDao.addBook(testBook3);


        //Act
        bookDao.deleteBook(testBook2.getBookId());
        List<Book> bookList = bookDao.getAllBooks();

        //Assert
        assertEquals(2, bookList.size());
        assertNotEquals(testBook2, bookList.get(0));
        assertNotEquals(testBook2, bookList.get(1));

        //Act
        bookDao.deleteBook(testBook1.getBookId());
        bookList = bookDao.getAllBooks();

        //Assert
        assertEquals(1, bookList.size());
        assertNotEquals(testBook1, bookList.get(0));

        //Act
        bookDao.deleteBook(testBook3.getBookId());
        bookList = bookDao.getAllBooks();

        //Assert
        assertEquals(0, bookList.size());
    }

    @Test
    public void shouldGetBookByAuthor(){
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

        publisher1 = publisherDao.addPublisher(publisher1);
        publisher2 = publisherDao.addPublisher(publisher2);
        publisher3 = publisherDao.addPublisher(publisher3);
        author1 = authorDao.addAuthor(author1);
        author2 = authorDao.addAuthor(author2);

        Book testBook1 = new Book();
        testBook1.setIsbn("HIS670");
        testBook1.setPublishDate(LocalDate.of(1990,3,12));
        testBook1.setAuthorId(author1.getAuthorId());
        testBook1.setTitle("Book Number One");
        testBook1.setPublisherId(publisher1.getPublisherId());
        testBook1.setPrice(new BigDecimal("1.05"));

        Book testBook2 = new Book();
        testBook2.setIsbn("SCI126");
        testBook2.setPublishDate(LocalDate.of(1690,12,31));
        testBook2.setAuthorId(author2.getAuthorId());
        testBook2.setTitle("Book Number Two");
        testBook2.setPublisherId(publisher2.getPublisherId());
        testBook2.setPrice(new BigDecimal("42.99"));

        Book testBook3 = new Book();
        testBook3.setIsbn("MAT964");
        testBook3.setPublishDate(LocalDate.of(2001,6,23));
        testBook3.setAuthorId(author2.getAuthorId());
        testBook3.setTitle("Book Number One");
        testBook3.setPublisherId(publisher3.getPublisherId());
        testBook3.setPrice(new BigDecimal("27.99"));

        testBook1 = bookDao.addBook(testBook1);
        testBook2 = bookDao.addBook(testBook2);
        testBook3 = bookDao.addBook(testBook3);

        //Act
        List<Book> resultFromTest1 = bookDao.getBooksByAuthor(author1.getAuthorId());
        List<Book> resultFromTest2 = bookDao.getBooksByAuthor(author2.getAuthorId());

        //Assert
        assertEquals(1, resultFromTest1.size());
        assertEquals(author1.getAuthorId(), resultFromTest1.get(0).getAuthorId());

        assertEquals(2, resultFromTest2.size());
        assertEquals(author2.getAuthorId(), resultFromTest2.get(0).getAuthorId());
        assertEquals(author2.getAuthorId(), resultFromTest2.get(1).getAuthorId());

    }
}