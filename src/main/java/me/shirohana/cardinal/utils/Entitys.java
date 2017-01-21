package me.shirohana.cardinal.utils;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public final class Entitys {

    private Entitys() { }

    /**
     * Sets the entity's health in range 0 ~ maxHealth
     * @param entity The new health sets for
     * @param health New health to be set
     * @return 0 if 0 < health < max, otherwise the offset to the bounds
     */
    public static double setHealth(LivingEntity entity, double health) {
        if (health <= 0) {
            entity.setHealth(0);
            return health;

        } else {
            double max = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

            if (health <= max) {
                entity.setHealth(health);
                return 0;
            } else {
                entity.setHealth(max);
                return health - max;
            }
        }
    }

}
