package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SENIOR, source = SourceType.ONLY_CONSOLE, blockHostConsole = true)
@CommandParameters(description = "Start a training mode session.", usage = "/<command> [on | off]", aliases = "tm")
public class Command_trainingmode extends TFM_Command
{

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        if (args[0].equalsIgnoreCase("off -s"))
        {
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(false);
            TFM_ConfigEntry.ADMIN_ONLY_MODE.setBoolean(false);
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(false);
            return true;
        }

        if (args[0].equalsIgnoreCase("off"))
        {
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(false);
            TFM_ConfigEntry.ADMIN_ONLY_MODE.setBoolean(false);
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(false);
            TFM_Util.adminAction(sender.getName(), "Stopping the training mode session...", true);
            return true;
        }
        else if (args[0].equalsIgnoreCase("on"))
        {
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(true);
            TFM_ConfigEntry.ADMIN_ONLY_MODE.setBoolean(true);
            TFM_ConfigEntry.TRAINING_SESSION.setBoolean(true);
            TFM_Util.adminAction(sender.getName(), "Starting the training mode session...", true);
            for (Player player : server.getOnlinePlayers())
            {
                if (!TFM_AdminList.isSuperAdmin(player))
                {
                    player.kickPlayer(ChatColor.YELLOW + "Freedom >" + ChatColor.RED + "RubyFreedom is now in a training session.");
                }
            }
            return true;
        }

        return false;
    }
}
