package us.haagen_dazs;

import us.haagen_dazs.domain.Client;
import us.haagen_dazs.domain.Ingredient;
import us.haagen_dazs.domain.Product;
import us.haagen_dazs.domain.Recipe;

import java.util.Arrays;
import java.util.Objects;

//This IceCream class models ice cream from the perspective of an ice cream producer who has
//ice cream factories.
public class IceCream extends Product {
    private Client[] clients;
    private String flavor; //IceCream also has a name under the product class, but name and flavor don't always match.
    private Ingredient[] rawMaterials; //measured in standard units of the Product
    private Recipe recipe;
    private Ingredient[] workInProgressInventory; //measured in the same unit as rawMaterials
    //We don't need a finishedProduct variable  because we can use .getUnitsInStock() for that.
    //For information regarding the factory this particular iceCream comes from, I would use .getSources()

    public IceCream(){};

    public IceCream(String flavor, Recipe recipe){
        this.flavor = flavor;
        this.recipe = recipe;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Ingredient[] getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(Ingredient[] rawMaterials) {
        this.rawMaterials = rawMaterials;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient[] getWorkInProgressInventory() {
        return workInProgressInventory;
    }

    public void setWorkInProgressInventory(Ingredient[] workInProgressInventory) {
        this.workInProgressInventory = workInProgressInventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IceCream iceCream = (IceCream) o;
        return Arrays.equals(clients, iceCream.clients) && Objects.equals(flavor, iceCream.flavor) && Arrays.equals(rawMaterials, iceCream.rawMaterials) && Objects.equals(recipe, iceCream.recipe) && Arrays.equals(workInProgressInventory, iceCream.workInProgressInventory);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), flavor, recipe);
        result = 31 * result + Arrays.hashCode(clients);
        result = 31 * result + Arrays.hashCode(rawMaterials);
        result = 31 * result + Arrays.hashCode(workInProgressInventory);
        return result;
    }

    @Override
    public String toString() {
        return "IceCream{" +
                "clients=" + Arrays.toString(clients) +
                ", flavor='" + flavor + '\'' +
                ", rawMaterials=" + Arrays.toString(rawMaterials) +
                ", recipe=" + recipe +
                ", workInProgressInventory=" + Arrays.toString(workInProgressInventory) +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", inStock=" + inStock +
                ", lotNumber='" + lotNumber + '\'' +
                ", margin=" + margin +
                ", name='" + name + '\'' +
                ", orderHistory=" + orderHistory +
                ", price=" + price +
                ", productId='" + productId + '\'' +
                ", sources=" + Arrays.toString(sources) +
                ", standardUnit=" + standardUnit +
                ", unitsInStock=" + unitsInStock +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                '}';
    }
}
