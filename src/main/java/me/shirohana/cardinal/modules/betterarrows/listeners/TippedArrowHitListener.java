package me.shirohana.cardinal.modules.betterarrows.listeners;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.TippedArrow;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitScheduler;

import me.shirohana.cardinal.Cardinal;
import me.shirohana.cardinal.exceptions.CardinalModuleException;
import me.shirohana.cardinal.modules.betterarrows.BetterArrows;
import me.shirohana.cardinal.utils.Entitys;
import me.shirohana.cardinal.utils.Players;

public final class TippedArrowHitListener implements Listener {

    private final BetterArrows module;
    private final Plugin plugin;

    private final int fireArrowTicks = -19;
    private final String arrowEnchantStrengthKey = "betterarrows:enchant-strength";
    private final List<Player> playerList = new LinkedList<>();

    public TippedArrowHitListener(BetterArrows module) {
        this.module = module;
        plugin = Cardinal.getPlugin();
    }

    @EventHandler
    public void onTippedArrowCauseDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity originalEntity = event.getEntity();

        if (damager instanceof TippedArrow && originalEntity instanceof LivingEntity) {
            TippedArrow arrow = (TippedArrow) damager;
            LivingEntity entity = (LivingEntity) originalEntity;

            final double damage = event.getOriginalDamage(DamageModifier.BASE)
                    + event.getOriginalDamage(DamageModifier.BLOCKING);
            boolean isOnFire = arrow.getFireTicks() == fireArrowTicks;
            List<PotionEffect> effects = arrow.getCustomEffects();

            double extraDamage = 0.0;
            double healingAmount = 0.0;
            double directDamage = 0.0;
            boolean disableDamage = true;

            // although TippedArrows are only have one PotionEffect currently ;)
            for (PotionEffect effect : effects) {
                PotionData potion = module.effectToPotion(effect);

                if (potion == null)
                    continue;

                switch (potion.getType()) {
                case INSTANT_DAMAGE:
                    if (damage != 0) {
                        if (!potion.isUpgraded())
                            extraDamage += damage / 4.0;
                        else
                            extraDamage += damage / 2.0;
                        disableDamage = false;
                    }
                    break;

                case INSTANT_HEAL:
                    if (damage != 0) {
                        if (!potion.isUpgraded())
                            healingAmount += 2 + damage / 4 + (isOnFire ? 2 : 0);
                        else
                            healingAmount += 4 + damage / 2 + (isOnFire ? 4 : 0);
                    }
                    break;

                case POISON:
                    if (damage != 0) {
                        if (!potion.isUpgraded())
                            directDamage += damage / 4.5;
                        else
                            directDamage += damage / 3.0;
                    }
                    break;

                case REGEN:
                    if (damage != 0) {
                        // TODO: move to module?
                        BukkitScheduler scheduler = Bukkit.getScheduler();

                        int duration = potion.isExtended() ? 5 : 3;
                        int amplifier = potion.isUpgraded() ? 2 : 1;

                        entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration*20, amplifier), true);
                        scheduler.scheduleSyncDelayedTask(plugin, () -> entity.addPotionEffect(effect), duration*20l);
                    }
                    break;

                case WATER_BREATHING:
                    if (damage != 0) {
                        entity.setRemainingAir(entity.getMaximumAir());
                    }
                    break;

                default:
                }
            }

            if (entity instanceof Player) {
                // prevent knockback
                playerList.add((Player) entity);

            } else if (entity instanceof Zombie || entity instanceof Skeleton) {
                healingAmount = -1.5 * healingAmount;
                extraDamage = -1.5 * extraDamage;
            }

            double healthOffset = healingAmount - directDamage;

            if (disableDamage) {
                event.setDamage(0);

            } else if (extraDamage != 0) {
                double newDamage = extraDamage + event.getDamage();

                if (newDamage < 0) {
                    healthOffset -= newDamage;
                    event.setDamage(0);
                } else {
                    event.setDamage(newDamage);
                }
            }

            Entitys.setHealth(entity, entity.getHealth() + healthOffset);
        }
    }

    /**
     * Disable default PotionEffect of {@link TippedArrow} when it hit in a
     * {@link LivingEntity}
     */
    @EventHandler
    public void onTippedArrowHit(ProjectileHitEvent event) {
        Projectile proj = event.getEntity();
        Entity entity = event.getHitEntity();

        if (proj instanceof TippedArrow && entity instanceof LivingEntity) {
            if (!(entity instanceof Player) || ((Player) entity).getGameMode() != GameMode.CREATIVE)
                ((TippedArrow) proj).setBasePotionData(new PotionData(PotionType.WATER));
        }
    }

    /**
     * Removes fire on a {@link TippedArrow} when launching, and records enchant
     * power level when shooter is a Player
     */
    @EventHandler
    public void onTippedArrowLaunch(ProjectileLaunchEvent event) {
        Projectile proj = event.getEntity();

        if (proj instanceof TippedArrow) {
            proj.setFireTicks(fireArrowTicks);
            ProjectileSource shooter = proj.getShooter();

            if (shooter instanceof Player) {
                ItemStack bow = Players.getBowOnHand((Player) shooter);

                if (bow != null) {
                    int powerLevel = bow.getEnchantmentLevel(Enchantment.ARROW_DAMAGE);
                    proj.setMetadata(arrowEnchantStrengthKey, new FixedMetadataValue(plugin, powerLevel));
                }
            }
        }
    }

    /**
     * Resets a TippedArrow entity to ItemStack when picking up
     */
    @EventHandler
    public void onPickupTippedArrow(PlayerPickupArrowEvent event) {
        Arrow arrow = event.getArrow();

        if (arrow instanceof TippedArrow) {
            PotionData potion = ((TippedArrow) arrow).getBasePotionData();
            ItemStack item = module.potionToArrow(potion);

            if (item == null) {
                new CardinalModuleException(module,
                        "Unknown tippedArrow potion type has been pickup: " + potion.toString()).printStackTrace();
            } else {
                item = item.clone();
                item.setAmount(1);
                event.getItem().setItemStack(item);
            }
        }
    }

    /**
     * Cancel player knockback event if the player just hit by a TippedArrow
     */
    @EventHandler
    public void onKnockbackByTippedArrow(PlayerVelocityEvent event) {
        int index = playerList.indexOf(event.getPlayer());

        if (index != -1) {
            playerList.remove(index);
            event.setCancelled(true);
        }
    }

}
