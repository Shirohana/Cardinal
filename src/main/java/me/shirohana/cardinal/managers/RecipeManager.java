package me.shirohana.cardinal.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public final class RecipeManager {

    private static RecipeManager instance;
    private final Server server;

    // TODO: manage recipes for modules
    private final ArrayList<Recipe> newRecipes = new ArrayList<>();
    private final List<ItemStack> removeItems = new LinkedList<>();

    private RecipeManager() {
        server = Bukkit.getServer();
    }

    public static RecipeManager getInstance() {
        return instance != null ? instance : (instance = new RecipeManager());
    }

    public void add(Recipe recipe) {
        newRecipes.add(recipe);
    }

    public void removeDefaults(ItemStack item) {
        removeItems.add(item);
    }

    public void bake() {
        removeExistsRecipes();
        addCustomRecipes();
    }

    public void reset() {
        server.resetRecipes();
    }

    private void removeExistsRecipes() {
        Iterator<Recipe> it = server.recipeIterator();
        Logger logger = server.getLogger();

        List<ItemStack> items = new LinkedList<>(removeItems);
        while (items.size() != 0 && it.hasNext()) {
            ItemStack lookingFor = it.next().getResult();

            for (int i = 0; i < items.size(); ++i) {
                if (items.get(i).equals(lookingFor)) {
                    it.remove();
                    items.remove(i);
                    break;
                }
            }
        }

        for (ItemStack item : items) {
            // TODO: update warning message
            logger.warning("Item could not be found: " + item.toString());
        }
    }

    private void addCustomRecipes() {
        for (Recipe recipe : newRecipes)
            server.addRecipe(recipe);
    }

}
