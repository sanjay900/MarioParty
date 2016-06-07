package com.sanjay900.marioParty.commands;

import org.bukkit.entity.Player;

import com.sanjay900.marioParty.MarioParty;

public class StopCommand extends PlayerSubCommand{
	public StopCommand() {
		super("stop");
	}

	@Override
	public void run(Player pl, String[] args) {
		if (!plugin.characters.containsKey(pl.getUniqueId())) return;
		plugin.characters.get(pl.getUniqueId()).despawn();
	}
}
