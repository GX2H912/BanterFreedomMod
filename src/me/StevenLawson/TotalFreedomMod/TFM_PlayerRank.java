package me.StevenLawson.TotalFreedomMod;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.COOWNER;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.CRAFTEDRANK;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.DEVELOPERS;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.EXECUTIVES;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.RF_DEVELOPERS;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.SYS;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum TFM_PlayerRank
{
    TF_DEV("a " + ChatColor.DARK_PURPLE + "TotalFreedomMod Developer", ChatColor.translateAlternateColorCodes('&', "&8[&5&lTFM-Dev&8]&9")),
    RF_DEVELOPER("a " + ChatColor.DARK_PURPLE + "RubyFreedomMod Developer", ChatColor.translateAlternateColorCodes('&', "&8[&5&lRFM-Dev&8]&9")),
    IMPOSTOR("an " + ChatColor.YELLOW + ChatColor.UNDERLINE + "Impostor", ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "[IMP]"),
    FAKEIMPOSTOR("a " + ChatColor.YELLOW + ChatColor.UNDERLINE + "Fake Impostor", ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "[IMP]"),
    NON_OP("a " + ChatColor.GREEN + "Non-OP", ChatColor.GREEN.toString()),
    OP("a " + ChatColor.RED + "OP", ChatColor.translateAlternateColorCodes('&', "&8[&c&lOP&8]&9")),
    SUPER("a " + ChatColor.BLUE + "Super Administrator", ChatColor.translateAlternateColorCodes('&', "&8[&b&lSA&8]&9")),
    TELNET("a " + ChatColor.BLUE + "Telnet Administrator", ChatColor.translateAlternateColorCodes('&', "&8[&a&lSTA&8]&9")),
    MULTI("a " + ChatColor.BLUE + "Specialist", ChatColor.translateAlternateColorCodes('&', "&8[&2&lSpecialist&8]&9")),
    SENIOR("a " + ChatColor.BLUE + "Senior Administrator", ChatColor.translateAlternateColorCodes('&', "&8[&d&lSrA&8]&9")),
    OWNER("the " + ChatColor.DARK_RED + "§4Owner §band the §9Founder §9of §cBanterFreedom§9. ", ChatColor.DARK_RED + "§8[§4§lOwner §9+ §9§lFounder§8]§9"),
    SYS_ADMIN("a " + ChatColor.RED + "System Administrator", ChatColor.translateAlternateColorCodes('&', "&8[&4&lSYS&8]&9")),
    EXEC("a " + ChatColor.YELLOW + "Executive", ChatColor.translateAlternateColorCodes('&', "&8[&4&lExecutive&8]&9")),
    CO_OWNER("a " + ChatColor.DARK_PURPLE + "§4Co Owner§9!", ChatColor.translateAlternateColorCodes('&', "&8[&4&lCo-Owner&8]&9")),
    CRAFTED("the " + ChatColor.DARK_PURPLE + "§4Co Owner§9, §5Lead-Developer§b and §4Admin-Manager§9!", ChatColor.translateAlternateColorCodes('&', "&8[&4&lCo-Owner&8]&9")),
    CONSOLE("the " + ChatColor.DARK_PURPLE + "Console", ChatColor.DARK_PURPLE + "[Console]");
    private final String loginMessage;
    private final String prefix;

    private TFM_PlayerRank(String loginMessage, String prefix)
    {
        this.loginMessage = loginMessage;
        this.prefix = prefix;
    }

    public static String getLoginMessage(CommandSender sender)
    {
        // Handle console
        if (!(sender instanceof Player))
        {
            return fromSender(sender).getLoginMessage();
        }

        // Handle admins
        final TFM_Admin entry = TFM_AdminList.getEntry((Player) sender);
        if (entry == null)
        {
            // Player is not an admin
            return fromSender(sender).getLoginMessage();
        }

        // Custom login message
        final String loginMessage = entry.getCustomLoginMessage();

        if (loginMessage == null || loginMessage.isEmpty())
        {
            return fromSender(sender).getLoginMessage();
        }

        return ChatColor.translateAlternateColorCodes('&', loginMessage);
    }

    public static TFM_PlayerRank fromSender(CommandSender sender)
    {
        if (!(sender instanceof Player))
        {
            return CONSOLE;
        }
        
        if (TFM_AdminList.isAdminImpostor((Player) sender))
        {
            return IMPOSTOR;
        }

        else if (DEVELOPERS.contains(sender.getName()))
        {
            return TF_DEV;
        }

        else if (RF_DEVELOPERS.contains(sender.getName()))
        {
            return RF_DEVELOPER;
        }

        else if (SYS.contains(sender.getName()))
        {
            return SYS_ADMIN;
        }

        else if (EXECUTIVES.contains(sender.getName()))
        {
            return EXEC;
        }

        else if (COOWNER.contains(sender.getName()))
        {
            return CO_OWNER;
        }
        
        else if (CRAFTEDRANK.contains(sender.getName()))
        {
            return CRAFTED;
        }

        final TFM_Admin entry = TFM_AdminList.getEntryByIp(TFM_Util.getIp((Player) sender));

        final TFM_PlayerRank rank;

        if (entry != null && entry.isActivated())
        {
            if (TFM_ConfigEntry.SERVER_OWNERS.getList().contains(sender.getName()))
            {
                return OWNER;
            }
            
            if (TFM_ConfigEntry.MULTI.getList().contains(sender.getName()))
            {
                return MULTI;
            }

            if (entry.isSeniorAdmin())
            {
                rank = SENIOR;
            }
            else if (entry.isTelnetAdmin())
            {
                rank = TELNET;
            }
            else
            {
                rank = SUPER;
            }
        }
        else
        {
            if (sender.isOp())
            {
                rank = OP;
            }
            else
            {
                rank = NON_OP;
            }

        }
        return rank;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public String getLoginMessage()
    {
        return loginMessage;
    }
}
