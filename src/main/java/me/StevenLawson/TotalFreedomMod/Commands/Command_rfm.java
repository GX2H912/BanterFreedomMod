package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about RubyFreedomMod Remastered", usage = "/<command> [reload]")
public class Command_rfm extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        TFM_Util.playerMsg(sender_p, " §4§lRubyFreedomMod:", ChatColor.GOLD);
        TFM_Util.playerMsg(sender_p, "RubyFreedomMod: Remastered was made and compiled and tested by __Crafted", ChatColor.GREEN);
        TFM_Util.playerMsg(sender_p, "Last developed by: __Crafted (4-28-16)", ChatColor.GREEN);
        TFM_Util.playerMsg(sender_p, "§5Made in the image of the §9TotalFreedomMod §5but with more §6features §5and §eflexibility.", ChatColor.GOLD);
        TFM_Util.playerMsg(sender_p, "§9This is RubyFreedomMod Remastered" + ChatColor.GOLD);

        return true;
    }
}
