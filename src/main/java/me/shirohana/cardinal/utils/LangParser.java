package me.shirohana.cardinal.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.potion.PotionEffectType;

public enum LangParser {

    // PotionEffectType
    ABSORPTION ("吸收", PotionEffectType.ABSORPTION),
    BLINDNESS ("失明", PotionEffectType.BLINDNESS),
    CONFUSION ("噁心", PotionEffectType.CONFUSION),
    DAMAGE_RESISTANCE ("抗性", PotionEffectType.DAMAGE_RESISTANCE),
    FAST_DIGGING ("挖掘加速", PotionEffectType.FAST_DIGGING),
    FIRE_RESISTANCE ("抗火", PotionEffectType.FIRE_RESISTANCE),
    GLOWING ("發光", PotionEffectType.GLOWING),
    HARM ("立即傷害", PotionEffectType.HARM),
    HEAL ("立即治療", PotionEffectType.HEAL),
    HEALTH_BOOST ("生命值提升", PotionEffectType.HEALTH_BOOST),
    HUNGER ("飢餓", PotionEffectType.HUNGER),
    INCREASE_DAMAGE ("力量", PotionEffectType.INCREASE_DAMAGE),
    INVISIBILITY ("隱形", PotionEffectType.INVISIBILITY),
    JUMP ("跳躍提升", PotionEffectType.JUMP),
    LEVITATION ("懸浮", PotionEffectType.LEVITATION),
    LUCK ("好運", PotionEffectType.LUCK),
    NIGHT_VISION ("夜視", PotionEffectType.NIGHT_VISION),
    POISON ("中毒", PotionEffectType.POISON),
    REGENERATION ("回復", PotionEffectType.REGENERATION),
    SATURATION ("飽食", PotionEffectType.SATURATION),
    SLOW ("緩速", PotionEffectType.SLOW),
    SLOW_DIGGING ("挖掘減速", PotionEffectType.SLOW_DIGGING),
    SPEED ("加速", PotionEffectType.SPEED),
    UNLUCK ("霉運", PotionEffectType.UNLUCK),
    WATER_BREATHING ("水下呼吸", PotionEffectType.WATER_BREATHING),
    WEAKNESS ("虛弱", PotionEffectType.WEAKNESS),
    WITHER ("凋零", PotionEffectType.WITHER),
    ;

    private static final Map<PotionEffectType, LangParser> potionEffectTypes;

    private final String label;
    private final Object source;

    LangParser(String label, PotionEffectType source) {
        this.label = label;
        this.source = source;
    }

    public static String parse(PotionEffectType type) {
        return potionEffectTypes.get(type).label;
    }

    public String getLabel() {
        return label;
    }

    static {
        potionEffectTypes = new HashMap<>();

        for (LangParser lang : values()) {
            if (lang.source instanceof PotionEffectType)
                potionEffectTypes.put((PotionEffectType) lang.source, lang);
        }
    }


}
