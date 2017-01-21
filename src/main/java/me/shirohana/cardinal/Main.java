package me.shirohana.cardinal;

import org.bukkit.plugin.java.JavaPlugin;

import me.shirohana.cardinal.modules.betterarrows.BetterArrows;

public final class Main extends JavaPlugin {

    private final Cardinal cardinal;

    public Main() {
        cardinal = Cardinal.getInstance();
    }

    @Override
    public void onEnable() {
        cardinal.setPlugin(this);
        cardinal.plugModule(new BetterArrows(cardinal));

        Cardinal.getRecipeManager().bake();
    }

    @Override
    public void onDisable() { }

}
