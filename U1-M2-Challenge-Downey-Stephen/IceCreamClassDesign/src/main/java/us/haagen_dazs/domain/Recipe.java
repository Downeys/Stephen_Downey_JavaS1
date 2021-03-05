package us.haagen_dazs.domain;

import java.util.Arrays;
import java.util.Objects;

public class Recipe {
    private String description;
    private Ingredient[] ingredients;
    private String name;

    public Recipe(){};

    public Recipe(String name, Ingredient[] ingredients, String description) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public Ingredient getIngredient(int index){
        return ingredients[index];
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(description, recipe.description) && Arrays.equals(ingredients, recipe.ingredients) && Objects.equals(name, recipe.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(description, name);
        result = 31 * result + Arrays.hashCode(ingredients);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "description='" + description + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", name='" + name + '\'' +
                '}';
    }
}
