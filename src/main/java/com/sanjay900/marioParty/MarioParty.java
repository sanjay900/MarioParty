package com.sanjay900.marioParty;

import java.util.HashMap;
import java.util.UUID;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.sanjay900.marioParty.commands.Command;
import com.sanjay900.marioParty.objects.Character;
import com.sanjay900.nmsUtil.NMSUtil;
@Getter
public class MarioParty extends JavaPlugin{
	private NMSUtil nmsutil;
	@Getter
	private static MarioParty instance;
	public HashMap<UUID,Character> characters = new HashMap<>();
	@Override
	public void onEnable(){
		instance = this;
		getCommand("mp").setExecutor(new Command());
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		nmsutil = (NMSUtil) Bukkit.getPluginManager().getPlugin("nmsUtils");
	}
	
}
