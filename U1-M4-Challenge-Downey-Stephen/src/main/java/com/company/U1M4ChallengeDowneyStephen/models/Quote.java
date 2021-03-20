package com.company.U1M4ChallengeDowneyStephen.models;

import java.util.Objects;

public class Quote {
    private String id;
    private String author;
    private String quote;

    private static int idCtr = 1;

    public Quote(){}

    public Quote(String author, String quote) {
        this.id = Integer.toString(idCtr++);
        this.author = author;
        this.quote = quote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote1 = (Quote) o;
        return Objects.equals(id, quote1.id) && Objects.equals(author, quote1.author) && Objects.equals(quote, quote1.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, quote);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }
}
