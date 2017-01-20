package me.shirohana.cardinal;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import me.shirohana.cardinal.exceptions.CardinalException;
import me.shirohana.cardinal.managers.EventManager;
import me.shirohana.cardinal.modules.Module;

public final class Cardinal {

    private static Cardinal instance;
    private static JavaPlugin plugin;
    private final Map<String, Module> moduleMap = new HashMap<>();
    private static EventManager events;

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

    public void plugModule(Module module) {
        String name = module.getName();
        if (moduleMap.containsKey(name))
            throw new CardinalException("Module name exists: " + name);
        moduleMap.put(name, module);
    }

    public static EventManager getEventManager() {
        return events != null ? events : (events = EventManager.getInstance());
    }

}
