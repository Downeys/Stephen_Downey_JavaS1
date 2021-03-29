package com.company.U1M5ChallengeDowneyStephen.daos;

import com.company.U1M5ChallengeDowneyStephen.dtos.Book;

import java.util.List;

public interface BookDao {

    Book addBook(Book book);
    Book getBook(int id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(int id);
    List<Book> getBooksByAuthor(int authorId);

}
