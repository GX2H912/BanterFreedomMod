package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about FreedomOPMod: Remastered", usage = "/<command>")
public class Command_fopm extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {

        playerMsg("Welcome to the FreedomOpMod; our server's main plugin!", ChatColor.GOLD);
        playerMsg("Created by Camzie99", ChatColor.GOLD);
        StringBuilder developers = new StringBuilder();
        developers.append("Later worked on by: CrafterSmith12");
        for (String dev : TFM_Util.FOP_DEVELOPERS)
        {
            developers.append(", " + dev);
        }
        developers.append(".");
        playerMsg(developers.toString(), ChatColor.AQUA);
        playerMsg("This plugin no longer exists, but you can find FOPM:Remastered forums at http://fop.us.to/!", ChatColor.GREEN);

        return true;
    }
}
