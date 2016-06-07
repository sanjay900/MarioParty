package com.sanjay900.marioParty.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.bukkit.command.CommandSender;

import com.sanjay900.marioParty.MarioParty;

@AllArgsConstructor
@Getter
public abstract class BaseSubCommand {
	protected MarioParty plugin = MarioParty.getInstance();
	private String label;
	private int minArgs;
	private int maxArgs;
	BaseSubCommand(String label,int minArgs,int maxArgs) {
		this.label = label;
		this.maxArgs = maxArgs;
		this.minArgs = minArgs;
	}
	public abstract void run(CommandSender sender, String[] args);
}
