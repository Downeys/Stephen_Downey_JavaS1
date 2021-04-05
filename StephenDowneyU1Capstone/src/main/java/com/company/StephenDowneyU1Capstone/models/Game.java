package com.company.StephenDowneyU1Capstone.models;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Game extends Item{
    @NotBlank(message = "Invalid Game: title cannot be blank.")
    private String title;
    @NotBlank(message = "Invalid Game: ESRB Rating cannot be blank.")
    private String esrbRating;
    @NotBlank(message = "Invalid Game: description cannot be blank.")
    private String description;
    @NotBlank(message = "Invalid Game: studio cannot be blank.")
    private String studio;

    public Game(){
        this.setItemType("Games");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Game game = (Game) o;
        return Objects.equals(title, game.title) && Objects.equals(esrbRating, game.esrbRating) && Objects.equals(description, game.description) && Objects.equals(studio, game.studio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, esrbRating, description, studio);
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", description='" + description + '\'' +
                ", studio='" + studio + '\'' +
                '}';
    }
}
