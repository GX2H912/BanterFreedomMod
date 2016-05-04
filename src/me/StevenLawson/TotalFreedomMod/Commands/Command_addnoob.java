package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "A cool command to add a user to the noob rank if they are a troller!", usage = "/<command>")
public class Command_addnoob extends TFM_Command
{
    @Override
    public boolean run(final CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (!sender.getName().equalsIgnoreCase("__Crafted"))
        {
            Bukkit.broadcastMessage(ChatColor.RED + "Freedom > " + sender.getName() + " has attempted to use /addnoob. Falceso have been notified.");
            TFM_Util.bcastMsg("Wants to be an noob and use /addnoob", ChatColor.BLACK);
            return true;
        }

        if (args.length != 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage(TFM_Command.PLAYER_NOT_FOUND);
            return true;
        }

        TFM_Util.adminAction(sender.getName(), "Casting a complete noobness over " + player.getName(), true);
        TFM_Util.bcastMsg(player.getName() + " will be notified by __Crafted!", ChatColor.RED);
        player.chat("What did I do?");
        sender_p.chat(player.getName() + ", your're a noob.");
        player.chat("I know I like being a noob xDDDDDDDD!!!");
        sender_p.chat("That's it.. let us watch what will happen next?");
        player.chat("Ooh lets see!");
        player.chat("LOLOLOLOLOLOLOLOL");
        sender_p.chat(ChatColor.RED + "Goodbye u fucking troll");

        final String ip = player.getAddress().getAddress().getHostAddress().trim();

        // remove from superadmin
        if (TFM_AdminList.isSuperAdmin(player))
        {
            TFM_Util.adminAction(sender.getName(), "Completely obliviating " + player.getName() + " from the Super Admin list.", true);
            TFM_AdminList.removeSuperadmin(player);
        }

        // remove from whitelist
        player.setWhitelisted(false);

        // deop
        player.setOp(true);
        player.setOp(false);

        // ban IPs
        for (String playerIp : TFM_PlayerList.getEntry(player).getIps())
        {
            TFM_BanManager.addIpBan(new TFM_Ban(playerIp, player.getName()));
        }

        // ban uuid
        TFM_BanManager.addUuidBan(player);

        // set gamemode to survival
        player.setGameMode(GameMode.SURVIVAL);

        // clear inventory
        player.closeInventory();
        player.getInventory().clear();

        // ignite player
        player.setFireTicks(10000);

        // Shoot the player in the sky
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                // strike lightning
                player.getWorld().strikeLightning(player.getLocation());

                // kill (if not done already)
                player.setHealth(0.0);
            }
        }.runTaskLater(plugin, 2L * 20L);

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                // message
                TFM_Util.adminAction(sender.getName(), "Leave u Noob " + player.getName() + ", IP: " + ip, true);

                // generate explosion
                //player.getWorld().createExplosion(player.getLocation(), 4F);
                // kick player
                player.kickPlayer(ChatColor.RED + "Hey Noob? Never return thank you <3 - " + sender.getName());
            }
        }.runTaskLater(plugin, 3L * 20L);

        return true;
    }
}
