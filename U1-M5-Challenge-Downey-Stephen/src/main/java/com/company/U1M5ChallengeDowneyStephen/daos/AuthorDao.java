package com.company.U1M5ChallengeDowneyStephen.daos;

import com.company.U1M5ChallengeDowneyStephen.dtos.Author;

import java.util.List;

public interface AuthorDao {

    Author addAuthor(Author author);
    Author getAuthor(int id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthor(int id);

}
