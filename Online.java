package me.lordspyder.online;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Online extends JavaPlugin implements Listener {

	FileConfiguration config;
	File cfile;
	ArrayList<String> online = new ArrayList<String>();

	public void onEnable() {
		getLogger().info("Has been enabled");
		getServer().getPluginManager().registerEvents(this, this);
		config = getConfig();
		config.options().copyDefaults(true);
		saveDefaultConfig();
		cfile = new File(getDataFolder(), "config.yml");
	}
	public void onDisable() {
		getLogger().info("Has been disabled");
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission("staff.online")){
			online.add(e.getPlayer().getName());
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission("staff.online")) {
			online.remove(e.getPlayer().getName());
		}
	}
	public boolean onCommand(CommandSender sender, Command cmd, String Comandlabel, String[] args) {
		Player p = (Player) sender;
		if ((cmd.getName().equalsIgnoreCase("Staff"))) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', " " + this.getConfig().getString("Staff")));
			for(String s : online){
				p.sendMessage(ChatColor.GOLD + "-" + " " + ChatColor.RESET + s);
			}
		}
		return true;
	}
}
