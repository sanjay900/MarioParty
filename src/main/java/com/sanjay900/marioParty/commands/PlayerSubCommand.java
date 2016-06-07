package com.sanjay900.marioParty.commands;

import lombok.Getter;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
@Getter
public abstract class PlayerSubCommand extends BaseSubCommand{

	public Player player;
	public PlayerSubCommand(String label) {
		super(label,0,0);
	}
	public abstract void run(Player pl, String[] args);
	public void run (CommandSender sender, String[] args) {
		run((Player) sender,args);
	}
}
