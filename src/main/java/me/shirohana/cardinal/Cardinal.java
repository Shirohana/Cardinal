package me.shirohana.cardinal;

import org.bukkit.plugin.java.JavaPlugin;

public final class Cardinal {

    private static Cardinal instance;
    private static JavaPlugin plugin;

    private Cardinal() { }

    public static Cardinal getInstance() {
        return instance != null ? instance : (instance = new Cardinal());
    }

    /**
     * Get the singleton plugin instance
     * @return The {@link JavaPlugin} which set by {@link #setPlugin()}
     */
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Set the singleton plugin
     */
    public void setPlugin(JavaPlugin plugin) {
        Cardinal.plugin = plugin;
    }

}
