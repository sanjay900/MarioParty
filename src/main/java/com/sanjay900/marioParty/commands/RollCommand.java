package com.sanjay900.marioParty.commands;

import org.bukkit.entity.Player;
public class RollCommand extends PlayerSubCommand{
	public RollCommand() {
		super("roll");
	}

	@Override
	public void run(Player pl, String[] args) {
		if (!plugin.characters.containsKey(pl.getUniqueId())) return;
		plugin.characters.get(pl.getUniqueId()).roll();
	}
}
