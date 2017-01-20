package me.shirohana.cardinal.managers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import me.shirohana.cardinal.Cardinal;
import me.shirohana.cardinal.modules.Module;

public final class EventManager {

    private static EventManager instance;
    private final Plugin plugin;
    private final PluginManager manager;
    private final Map<Module, Set<Listener>> listenerMap = new HashMap<>();

    private EventManager() {
        plugin = Cardinal.getPlugin();
        manager = Bukkit.getPluginManager();
    }

    public static EventManager getInstance() {
        return instance != null ? instance : (instance = new EventManager());
    }

    public void registerListener(Listener listener, Module module) {
        Set<Listener> listeners = listenerMap.get(module);

        if (listeners == null)
            listenerMap.put(module, (listeners = new HashSet<>()));

        manager.registerEvents(listener, plugin);
        listeners.add(listener);
    }

}
