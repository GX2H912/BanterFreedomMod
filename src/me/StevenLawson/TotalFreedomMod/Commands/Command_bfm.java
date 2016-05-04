package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about BanterFreedomMod Remastered", usage = "/<command>")
public class Command_bfm extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        TFM_Util.playerMsg(sender_p, " §4§lBanterFreedomMod:", ChatColor.GOLD);
        TFM_Util.playerMsg(sender_p, "BanterFreedomMod: Remastered was made and compiled and tested by __Crafted", ChatColor.GREEN);
        TFM_Util.playerMsg(sender_p, "§5Last developed by: __Crafted (5-3-16)", ChatColor.GREEN);
        TFM_Util.playerMsg(sender_p, "§5Made in the image of the §9TotalFreedomMod §5but with more §6features §5and §eflexibility.", ChatColor.GOLD);
        TFM_Util.playerMsg(sender_p, "§9This is BanterFreedomMod Remastered!" + ChatColor.GOLD);

        return true;
    }
}
