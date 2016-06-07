package com.sanjay900.marioParty.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements org.bukkit.command.CommandExecutor{
	public ArrayList<BaseSubCommand> commands = new ArrayList<>();
	public Command() {
		commands.add(new PlayCommand());
		commands.add(new StopCommand());
		commands.add(new RollCommand());
		commands.add(new HitCommand());
	}
	@Override
	public boolean onCommand(CommandSender sender,
			org.bukkit.command.Command cmd, String label, String[] args) {
		int reallength = args.length -1;
		
		if (args.length > 0) {
			for (BaseSubCommand s:commands) {
				if (s.getLabel().equalsIgnoreCase(args[0])) {
					if (!(s instanceof PlayerSubCommand) || sender instanceof Player) {
						if (s.getMinArgs()<=reallength && (s.getMaxArgs() == -1 || reallength <= s.getMaxArgs())) {
							s.run(sender, Arrays.copyOfRange(args, 1, args.length));
						} else {
							sender.sendMessage("You entered the wrong amount of arguments for this command.");
						}

					} else if (s instanceof PlayerSubCommand) {
						sender.sendMessage("You must be a player to run this command!");
					} 
				}
			}
		} else {
			sender.sendMessage("You must provide arguments to this command!");
		}
		return true;
	}

}
