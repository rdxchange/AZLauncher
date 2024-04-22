package fr.speccy.azclientapi.bukkit.handlers;

public enum PLSPConfFlag {
    ATTACK_COOLDOWN("ATTACK_COOLDOWN"),
    PLAYER_PUSH("PLAYER_PUSH"),
    LARGE_HITBOX("LARGE_HITBOX"),
    SWORD_BLOCKING("SWORD_BLOCKING"),
    HIT_AND_BLOCK("HIT_AND_BLOCK"),
    OLD_ENCHANTEMENTS("OLD_ENCHANTEMENTS"),
    PVP_HIT_PRIORITY("PVP_HIT_PRIORITY"),
    SEE_CHUNKS("SEE_CHUNKS"),
    SIDEBAR_SCORES("SIDEBAR_SCORES"),
    SMOOTH_EXPERIENCE_BAR("SMOOTH_EXPERIENCE_BAR"),
    SORT_TAB_LIST_BY_NAMES("SORT_TAB_LIST_BY_NAMES");

    private final String name;

    PLSPConfFlag(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
