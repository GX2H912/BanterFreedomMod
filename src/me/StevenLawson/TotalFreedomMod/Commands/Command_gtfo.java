package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Bridge.TFM_WorldEditBridge;
import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_RollbackManager;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_UuidManager;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "(deop and ip ban by username).", usage = "/<command> <partialname>", aliases = "ban")
public class Command_gtfo extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            playerMsg(TFM_Command.PLAYER_NOT_FOUND, ChatColor.RED);
            return true;
        }

        String reason = null;
        if (args.length >= 2)
        {
            reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        }

        TFM_Util.bcastMsg(player.getName() + " has been an naughty, naughty boy!", ChatColor.RED);

        // CoreProtect was removed due to server lagg
        // Undo WorldEdits:
         try
         {
         TFM_WorldEditBridge.undo(player, 15);
         }
         catch (NoClassDefFoundError ex)
         {
         }

         // rollback
         TFM_RollbackManager.rollback(player.getName());
         
        // deops the  player
        player.setOp(false);

        // command /gms
        player.setGameMode(GameMode.SURVIVAL);

        // command /ci
        player.getInventory().clear();

        // smite the user
        final Location targetPos = player.getLocation();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                targetPos.getWorld().strikeLightning(strike_pos);
            }
        }

        // banning ip address
        String ip = TFM_Util.getFuzzyIp(player.getAddress().getAddress().getHostAddress());

        final StringBuilder bcast = new StringBuilder()
                .append(ChatColor.RED)
                .append("Banning: ")
                .append(player.getName())
                .append(", IP: ")
                .append(ip);

        if (reason != null)
        {
            bcast.append("\nReason: ").append(ChatColor.YELLOW).append(reason).append(" (").append(sender.getName()).append(")");
        }

        TFM_Util.bcastMsg(bcast.toString());
        
        // banning username & ip data
        TFM_BanManager.addIpBan(new TFM_Ban(ip, player.getName(), sender.getName(), null, reason));
        TFM_BanManager.addUuidBan(new TFM_Ban(TFM_UuidManager.getUniqueId(player), player.getName(), sender.getName(), null, reason));

        // kick player message
        player.kickPlayer(ChatColor.RED + "You have been banned (GTFO)" + (reason != null ? ("\nReason: " + ChatColor.YELLOW + reason + "(" + sender.getName() + ")") : ""));

        return true;
    }
}
