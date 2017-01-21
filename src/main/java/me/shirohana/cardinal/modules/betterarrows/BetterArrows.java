package me.shirohana.cardinal.modules.betterarrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.Dye;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import me.shirohana.cardinal.Cardinal;
import me.shirohana.cardinal.managers.EventManager;
import me.shirohana.cardinal.managers.RecipeManager;
import me.shirohana.cardinal.modules.CardinalModule;
import me.shirohana.cardinal.modules.betterarrows.listeners.TippedArrowCraftListener;
import me.shirohana.cardinal.modules.betterarrows.listeners.TippedArrowHitListener;
import me.shirohana.cardinal.utils.LangParser;

public final class BetterArrows extends CardinalModule {

    private static final String MODULE_NAME = "BetterArrows";
    private static final String VERSION = "0.1";

    @SuppressWarnings("unused")
    private final Cardinal cardinal;

    private static ItemStack newTippedArrow;

    // TODO: find some pretty way
    static {
        RecipeManager recipes = Cardinal.getRecipeManager();

        newTippedArrow = new ItemStack(Material.TIPPED_ARROW, 4);
        PotionMeta meta = (PotionMeta) newTippedArrow.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.WATER));
        newTippedArrow.setItemMeta(meta);

        ItemStack oldTippedArrow = new ItemStack(Material.TIPPED_ARROW, 8);
        oldTippedArrow.setItemMeta(meta);

        recipes.removeDefaults(oldTippedArrow);

        final String[][] shapes = new String[][] {
            new String[] {"fad", "apa", " a "},
            new String[] {" a ", "apa", "fad"},
            new String[] {"fa ", "apa", "da "},
            new String[] {"da ", "apa", "fa "},
            new String[] {"fa ", "apa", " ad"},
            new String[] {"da ", "apa", " af"}
        };

        for (String[] shape : shapes) {
            ShapedRecipe newRecipe = new ShapedRecipe(newTippedArrow);
            newRecipe.shape(shape);
            newRecipe.setIngredient('a', Material.ARROW);
            newRecipe.setIngredient('d', new Dye(DyeColor.WHITE));
            newRecipe.setIngredient('f', Material.FLINT);
            newRecipe.setIngredient('p', Material.POTION);
            recipes.add(newRecipe);
        }
    }

    public BetterArrows(Cardinal cardinal) {
        this.cardinal = cardinal;
        name = MODULE_NAME;
        version = VERSION;

        EventManager events = Cardinal.getEventManager();
        events.registerListener(new TippedArrowCraftListener(this, newTippedArrow), this);
        events.registerListener(new TippedArrowHitListener(this), this);
    }

    public ItemStack potionToArrow(PotionData potion) {
        return ArrowManager.potionToArrowMap.get(potion);
    }

    public PotionData effectToPotion(PotionEffect effect) {
        return ArrowManager.effectToPotionMap.get(effect);
    }

    private static final class ArrowManager {

        static final Map<PotionData, ItemStack> potionToArrowMap = new HashMap<>();
        static final Map<PotionEffect, PotionData> effectToPotionMap = new HashMap<>();

        static {
            put(new PotionData(PotionType.AWKWARD, false, false), null);
            put(new PotionData(PotionType.FIRE_RESISTANCE, false, false), new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 45*20, 0));
            put(new PotionData(PotionType.FIRE_RESISTANCE, true, false), new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 120*20, 0));
            put(new PotionData(PotionType.INSTANT_DAMAGE, false, false), new PotionEffect(PotionEffectType.HARM, 0, 0));
            put(new PotionData(PotionType.INSTANT_DAMAGE, false, true), new PotionEffect(PotionEffectType.HARM, 0, 1));
            put(new PotionData(PotionType.INSTANT_HEAL, false, false), new PotionEffect(PotionEffectType.HEAL, 0, 0));
            put(new PotionData(PotionType.INSTANT_HEAL, false, true), new PotionEffect(PotionEffectType.HEAL, 0, 1));
            put(new PotionData(PotionType.INVISIBILITY, false, false), new PotionEffect(PotionEffectType.INVISIBILITY, 45*20, 0));
            put(new PotionData(PotionType.INVISIBILITY, true, false), new PotionEffect(PotionEffectType.INVISIBILITY, 120*20, 0));
            put(new PotionData(PotionType.JUMP, false, false), new PotionEffect(PotionEffectType.JUMP, 45*20, 0));
            put(new PotionData(PotionType.JUMP, true, false), new PotionEffect(PotionEffectType.JUMP, 120*20, 0));
            put(new PotionData(PotionType.JUMP, false, true), new PotionEffect(PotionEffectType.JUMP, 22*20, 1));
            put(new PotionData(PotionType.LUCK, false, false), new PotionEffect(PotionEffectType.LUCK, 75*20, 0));
            put(new PotionData(PotionType.MUNDANE, false, false), null);
            put(new PotionData(PotionType.NIGHT_VISION, false, false), new PotionEffect(PotionEffectType.NIGHT_VISION, 45*20, 0));
            put(new PotionData(PotionType.NIGHT_VISION, true, false), new PotionEffect(PotionEffectType.NIGHT_VISION, 120*20, 0));
            put(new PotionData(PotionType.POISON, false, false), new PotionEffect(PotionEffectType.POISON, 11*20, 0));
            put(new PotionData(PotionType.POISON, true, false), new PotionEffect(PotionEffectType.POISON, 22*20, 0));
            put(new PotionData(PotionType.POISON, false, true), new PotionEffect(PotionEffectType.POISON, 5*20, 1));
            put(new PotionData(PotionType.REGEN, false, false), new PotionEffect(PotionEffectType.REGENERATION, 11*20, 0));
            put(new PotionData(PotionType.REGEN, true, false), new PotionEffect(PotionEffectType.REGENERATION, 22*20, 0));
            put(new PotionData(PotionType.REGEN, false, true), new PotionEffect(PotionEffectType.REGENERATION, 5*20, 1));
            put(new PotionData(PotionType.SLOWNESS, false, false), new PotionEffect(PotionEffectType.SLOW, 22*20, 0));
            put(new PotionData(PotionType.SLOWNESS, true, false), new PotionEffect(PotionEffectType.SLOW, 60*20, 0));
            put(new PotionData(PotionType.SPEED, false, false), new PotionEffect(PotionEffectType.SPEED, 45*20, 0));
            put(new PotionData(PotionType.SPEED, true, false), new PotionEffect(PotionEffectType.SPEED, 120*20, 0));
            put(new PotionData(PotionType.SPEED, false, true), new PotionEffect(PotionEffectType.SPEED, 22*20, 1));
            put(new PotionData(PotionType.STRENGTH, false, false), new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 45*20, 0));
            put(new PotionData(PotionType.STRENGTH, true, false), new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120*20, 0));
            put(new PotionData(PotionType.STRENGTH, false, true), new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 22*20, 1));
            put(new PotionData(PotionType.THICK, false, false), null);
            put(new PotionData(PotionType.UNCRAFTABLE, false, false), null);
            put(new PotionData(PotionType.WATER, false, false), null);
            put(new PotionData(PotionType.WATER_BREATHING, false, false), new PotionEffect(PotionEffectType.WATER_BREATHING, 45*20, 0));
            put(new PotionData(PotionType.WATER_BREATHING, true, false), new PotionEffect(PotionEffectType.WATER_BREATHING, 120*20, 0));
            put(new PotionData(PotionType.WEAKNESS, false, false), new PotionEffect(PotionEffectType.WEAKNESS, 22*20, 0));
            put(new PotionData(PotionType.WEAKNESS, true, false), new PotionEffect(PotionEffectType.WEAKNESS, 60*20, 0));
        }

        static void put(PotionData potion, PotionEffect effect) {
            potionToArrowMap.put(potion, buildArrow(potion, effect));
            effectToPotionMap.put(effect, potion);
        }

        static ItemStack buildArrow(PotionData base, PotionEffect customEffect) {
            ItemStack arrow = new ItemStack(Material.TIPPED_ARROW, 4);
            PotionMeta meta = (PotionMeta) arrow.getItemMeta();

            meta.setBasePotionData(base);
            if (customEffect != null) {
                meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                meta.addCustomEffect(customEffect, true);
                meta.setLore(buildPotionLore(customEffect));
            }

            arrow.setItemMeta(meta);
            return arrow;
        }

        static List<String> buildPotionLore(PotionEffect effect) {
            String name = LangParser.parse(effect.getType());
            int amp = effect.getAmplifier();
            int duration = effect.getDuration() / 20;

            // TODO: improve performance
            String result;

            if (duration == 0) {
                if (amp == 0)
                    result = ChatColor.BLUE + name;
                else
                    result = ChatColor.BLUE + name + " II";
            } else {
                if (amp == 0)
                    result = ChatColor.BLUE + name + String.format(" (%d:%02d)", duration / 60, duration % 60);
                else
                    result = ChatColor.BLUE + name + " II" + String.format(" (%d:%02d)", duration / 60, duration % 60);
            }

            return Arrays.asList(result);
        }

    }

}
