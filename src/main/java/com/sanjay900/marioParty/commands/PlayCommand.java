package com.sanjay900.marioParty.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.sanjay900.marioParty.objects.Character;
import com.sanjay900.marioParty.objects.Character.CharacterType;
public class PlayCommand extends PlayerSubCommand{
	public PlayCommand() {
		super("play");
	}

	@Override
	public void run(Player pl, String[] args) {
		if (plugin.characters.containsKey(pl.getUniqueId())) return;
		Character ch =  new Character(pl.getName(), CharacterType.MARIO, pl);
		ch.spawn(new Location(Bukkit.getWorld("mpworld1"),-16,65,-40), BlockFace.EAST);
		plugin.characters.put(pl.getUniqueId(), ch);
		
	}
}
