package me.shirohana.cardinal.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class Players {

    private Players() { }

    /**
     * Gets the bow on hand which can launch a projectile
     * @param player
     * @return the bow in the main hand or off hand, null when no bow in the hands
     */
    public static ItemStack getBowOnHand(Player player) {
        PlayerInventory inv = player.getInventory();
        ItemStack bow = inv.getItemInMainHand();
        return bow != null ? bow : inv.getItemInOffHand();
    }

}
