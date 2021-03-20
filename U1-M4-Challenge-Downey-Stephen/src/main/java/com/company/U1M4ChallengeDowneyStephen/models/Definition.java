package com.company.U1M4ChallengeDowneyStephen.models;

import java.util.Objects;

public class Definition {
    private String id;
    private String word;
    private String definition;

    private static int idCtr = 1;

    public Definition(){}

    public Definition(String word, String definition) {
        this.id = Integer.toString(idCtr++);
        this.word = word;
        this.definition = definition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Definition definition1 = (Definition) o;
        return Objects.equals(id, definition1.id) && Objects.equals(word, definition1.word) && Objects.equals(definition, definition1.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, definition);
    }

    @Override
    public String toString() {
        return "Word{" +
                "id='" + id + '\'' +
                ", word='" + word + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
