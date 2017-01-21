package me.shirohana.cardinal.modules.betterarrows.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import me.shirohana.cardinal.exceptions.CardinalModuleException;
import me.shirohana.cardinal.modules.betterarrows.BetterArrows;

public final class TippedArrowCraftListener implements Listener {

    private final BetterArrows module;
    private final ItemStack newTippedArrow;

    public TippedArrowCraftListener(BetterArrows module, ItemStack newTippedArrow) {
        this.module = module;
        this.newTippedArrow = newTippedArrow;
    }

    @EventHandler
    public void prepareCraft(PrepareItemCraftEvent event) {
        CraftingInventory inv = event.getInventory();
        ItemStack arrow = inv.getResult();

        if (!arrow.equals(newTippedArrow))
            return;

        // TODO: Ensure it will not conflict with another recipe
        ItemStack potion = inv.getItem(5);
        if (potion == null) {
            new CardinalModuleException(module,
                    "Recipe result conflicted with: " + inv.getContents().toString()).printStackTrace();
            return;
        }

        PotionData potionData = ((PotionMeta) potion.getItemMeta()).getBasePotionData();
        ItemStack item = module.potionToArrow(potionData);

        if (item == null) {
            inv.setResult(new ItemStack(Material.AIR));
            new CardinalModuleException(module,
                    "Unhandled tippedarrow recipe: " + inv.toString()).printStackTrace();
        } else {
            inv.setResult(item);
        }
    }

}
