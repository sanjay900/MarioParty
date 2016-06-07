package com.sanjay900.marioParty.objects;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;

import com.sanjay900.marioParty.MarioParty;
import com.sanjay900.nmsUtil.fallingblocks.FrozenSand;
import com.sanjay900.nmsUtil.fallingblocks.FrozenSandFactory;
@Getter
public class RidingDice {
	private MarioParty plugin = MarioParty.getInstance();
	private boolean rolling = true;
	private FrozenSand sand;
	private BukkitTask move;
	private Location target;
	Character c;
	public RidingDice (int landed, Location l, final Character c) {
		this.c = c;
		this.sand = new FrozenSandFactory().withLocation(l).withId(getId(landed)).withData(getData(landed)).build();
		move = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable(){

			@Override
			public void run() {
				if (sand != null)
				sand.teleport(c.npc.getEntity().getLocation().add(0, 3, 0));
			}}, 1l, 1l);
	}
	public void destroy() {
		if (move != null)
		move.cancel();
		if (sand != null)
		plugin.getNmsutil().getFrozenSandManager().remove(sand);
		sand = null;
		c.dice.d = null;

	}
	public void update(int landed) {
		
		plugin.getNmsutil().getFrozenSandManager().remove(getSand());
		this.sand = new FrozenSandFactory().withLocation(c.npc.getEntity().getLocation().add(0, 3, 0)).withId(getId(landed)).withData(getData(landed)).build();
		if (landed <= 0) {
			destroy();
		}
	}
	private byte getData(int landed) {
		switch (landed) {
		case 1:
		case 2:
		case 3:
		case 4: 
		case 5:
			return (byte) (landed+10);

		case 6:
			return 0;
		case 7:
			return 6;
		case 8:
			return 2;
		case 9:
			return 12;
		case 10:
			return 13;
		default: return 0;
		}
	}
	private int getId(int landed) {
		switch (landed) {
		case 1:
		case 2:
		case 3:
		case 4: 
		case 5:
			return 95;
		case 6:
			return 20;
		case 7:
		case 8:
		case 9:
		case 10:
			return 160;
		default: return 0;
		}
	}
}
