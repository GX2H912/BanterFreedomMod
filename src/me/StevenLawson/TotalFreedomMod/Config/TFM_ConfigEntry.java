package me.StevenLawson.TotalFreedomMod.Config;

import java.util.List;

public enum TFM_ConfigEntry
{
    // Note this is the "config.yml" listener file
    // Don't fuck this up again!
    FORCE_IP_ENABLED(Boolean.class, "forceip.enabled"),
    FORCE_IP_PORT(Integer.class, "forceip.port"),
    //
    ALLOW_EXPLOSIONS(Boolean.class, "toggles.explosions"),
    ALLOW_FIRE_PLACE(Boolean.class, "toggles.fire_place"),
    ALLOW_FIRE_SPREAD(Boolean.class, "toggles.fire_spread"),
    ALLOW_FLUID_SPREAD(Boolean.class, "toggles.fluid_spread"),
    ALLOW_LAVA_DAMAGE(Boolean.class, "toggles.lava_damage"),
    ALLOW_LAVA_PLACE(Boolean.class, "toggles.lava_place"),
    ALLOW_TNT_MINECARTS(Boolean.class, "toggles.tnt_minecarts"),
    ALLOW_WATER_PLACE(Boolean.class, "toggles.water_place"),
    DISABLE_NIGHT(Boolean.class, "toggles.night"),
    DISABLE_WEATHER(Boolean.class, "toggles.weather"),
    SERVER_COLORFUL_MOTD(Boolean.class, "toggles.colorful_motd"),
    LANDMINES_ENABLED(Boolean.class, "toggles.landmines_enabled"),
    TOSSMOB_ENABLED(Boolean.class, "toggles.tossmob_enabled"),
    MP44_ENABLED(Boolean.class, "toggles.mp44_enabled"),
    ENABLE_PREPROCESS_LOG(Boolean.class, "toggles.preprocess_log"),
    AUTO_ENTITY_WIPE(Boolean.class, "toggles.auto_wipe"),
    TRAINING_SESSION(Boolean.class, "toggles.training_mode"),
    //
    MOB_LIMITER_ENABLED(Boolean.class, "mobs.enabled"),
    MOB_LIMITER_MAX(Integer.class, "mobs.max"),
    MOB_LIMITER_DISABLE_DRAGON(Boolean.class, "mobs.disable.dragon"),
    MOB_LIMITER_DISABLE_GHAST(Boolean.class, "mobs.disable.ghast"),
    MOB_LIMITER_DISABLE_GIANT(Boolean.class, "mobs.disable.giant"),
    MOB_LIMITER_DISABLE_SLIME(Boolean.class, "mobs.disable.slime"),
    ENABLE_PET_PROTECT(Boolean.class, "mobs.pets_enabled"),
    //
    SERVER_OWNERS(List.class, "admins.banterfreedom_owner"),
    MULTI(List.class, "admins.specialist"),
    NOADMIN_IPS(List.class, "admins.noadmin_ips"),
    ADMIN_ONLY_MODE(Boolean.class, "admins.admin_only_mode"),
    CONSOLE_IS_SENIOR(Boolean.class, "admins.console_is_senior"),
    HOST_SENDER_NAMES(List.class, "admins.host_sender_names"),
    //
    SERVER_NAME(String.class, "server_info.server_name"),
    SERVER_ADDRESS(String.class, "server_info.server_subdomain"),
    SERVER_MOTD(String.class, "server_info.server_motd"),
    FORCE_IP_KICKMSG(String.class, "server_info.kickmsg"),
    //
    SERVER_BAN_URL(String.class, "urls.url_bans"),
    SERVER_PERMBAN_URL(String.class, "urls.url_permbans"),
    SERVICE_CHECKER_URL(String.class, "urls.url_minecraft_service"),
    //
    TWITTERBOT_ENABLED(Boolean.class, "disabled.enabled"),
    TWITTERBOT_SECRET(String.class, "disabled.secret"),
    OVERLORD_IPS(List.class, "disabled.overlord_ips"),
    TWITTERBOT_URL(String.class, "disabled.url"),
    HTTPD_ENABLED(Boolean.class, "disabled.enabled"),
    HTTPD_PORT(Integer.class, "disabled.port"),
    HTTPD_PUBLIC_FOLDER(String.class, "disabled.public_folder"),
    LOGS_SECRET(String.class, "disabled.secret"),
    LOGS_URL(String.class, "disabled.url"),
    //
    PROTECTAREA_ENABLED(Boolean.class, "protection.enabled"),
    PROTECTAREA_SPAWNPOINTS(Boolean.class, "protection.auto_protect_spawnpoints"),
    PROTECTAREA_RADIUS(Double.class, "protection.auto_protect_radius"),
    FLATLANDS_GENERATE(Boolean.class, "protection.generate"),
    FLATLANDS_GENERATE_PARAMS(String.class, "protection.generate_params"),
    NUKE_MONITOR_ENABLED(Boolean.class, "protection.enabled"),
    NUKE_MONITOR_COUNT_BREAK(Integer.class, "protection.count_break"),
    NUKE_MONITOR_COUNT_PLACE(Integer.class, "protection.count_place"),
    NUKE_MONITOR_RANGE(Double.class, "protection.range"),
    EXPLOSIVE_RADIUS(Double.class, "protection.explosive_radius"),
    FREECAM_TRIGGER_COUNT(Integer.class, "protection.freecam_trigger_count"),
    //
    AUTOKICK_THRESHOLD(Double.class, "autokicker.threshold"),
    AUTOKICK_TIME(Integer.class, "autokicker.time"),
    AUTOKICK_ENABLED(Boolean.class, "autokicker.enabled"),
    //
    ANNOUNCER_ENABLED(Boolean.class, "announcer.enabled"),
    ANNOUNCER_INTERVAL(Integer.class, "announcer.interval"),
    ANNOUNCER_PREFIX(String.class, "announcer.prefix"),
    ANNOUNCER_ANNOUNCEMENTS(List.class, "announcer.announcements"),
    // Misc
    BLOCKED_COMMANDS(List.class, "freedom_b_commands"),
    UNBANNABLE_USERNAMES(List.class, "famous_minecraft_players");
    //
    private final Class<?> type;
    private final String configName;

    private TFM_ConfigEntry(Class<?> type, String configName)
    {
        this.type = type;
        this.configName = configName;
    }

    public Class<?> getType()
    {
        return type;
    }

    public String getConfigName()
    {
        return configName;
    }

    public String getString()
    {
        return TFM_MainConfig.getString(this);
    }

    public String setString(String value)
    {
        TFM_MainConfig.setString(this, value);
        return value;
    }

    public Double getDouble()
    {
        return TFM_MainConfig.getDouble(this);
    }

    public Double setDouble(Double value)
    {
        TFM_MainConfig.setDouble(this, value);
        return value;
    }

    public Boolean getBoolean()
    {
        return TFM_MainConfig.getBoolean(this);
    }

    public Boolean setBoolean(Boolean value)
    {
        TFM_MainConfig.setBoolean(this, value);
        return value;
    }

    public Integer getInteger()
    {
        return TFM_MainConfig.getInteger(this);
    }

    public Integer setInteger(Integer value)
    {
        TFM_MainConfig.setInteger(this, value);
        return value;
    }

    public List<?> getList()
    {
        return TFM_MainConfig.getList(this);
    }

    public static TFM_ConfigEntry findConfigEntry(String name)
    {
        name = name.toLowerCase().replace("_", "");
        for (TFM_ConfigEntry entry : values())
        {
            if (entry.toString().toLowerCase().replace("_", "").equals(name))
            {
                return entry;
            }
        }
        return null;
    }
}
