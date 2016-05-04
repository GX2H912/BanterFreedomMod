package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.FOPM_TFM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(
        description = "Developer Communication",
        usage = "/<command> [message...]",
        aliases = "dev")
public class Command_devchat extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (senderIsConsole)
            {
                playerMsg(ChatColor.YELLOW + "Freedom >" + ChatColor.RED + "Only in-game users can use /devchat");
                return true;
            }

            TFM_PlayerData userinfo = TFM_PlayerData.getPlayerData(sender_p);

            if (userinfo.inAdminChat())
            {
                userinfo.setAdminChat(!userinfo.inAdminChat());
            }

            if (userinfo.inSeniorAdminChat())
            {
                userinfo.setSeniorAdminChat(!userinfo.inSeniorAdminChat());
            }

            userinfo.setDevChat(!userinfo.inDevChat());
            playerMsg(ChatColor.YELLOW + "Freedom >" + ChatColor.RED + "Toggled Developer Chat " + (userinfo.inDevChat() ? "on" : "off") + ".");
        }
        else
        {
            FOPM_TFM_Util.DevChatMessage(sender, StringUtils.join(args, " "), senderIsConsole);
        }

        return true;
    }
}
