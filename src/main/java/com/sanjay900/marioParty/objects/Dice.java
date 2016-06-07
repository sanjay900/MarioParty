package com.sanjay900.marioParty.objects;

import java.util.Random;

import lombok.Getter;
import net.citizensnpcs.api.npc.NPC;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import com.sanjay900.marioParty.MarioParty;
@Getter
public class Dice {
	private Random random = new Random();
	private MarioParty plugin = MarioParty.getInstance();
	private boolean rolling = true;
	RidingDice d = null;
	Block b;
	public void spawn(Location l) {
		b = l.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH);
		b.setType(Material.STAINED_GLASS);
		roll();
	}
	@SuppressWarnings("deprecation")
	private void roll() {

		if (!rolling) return;
		b.setData((byte)random.nextInt(9));
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){

			@Override
			public void run() {
				roll();
			}
		},1l);
	}
	public void spawn(NPC npc) {
		spawn(npc.getEntity().getLocation().add(0, 3, 0));
	}
	public void explode(final Character character) {
		rolling = false;
		final int landed = b.getData();
		b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType().getId());
		int id = b.getTypeId();
		byte data = b.getData();
		Location l = b.getRelative(BlockFace.EAST).getRelative(BlockFace.SOUTH).getLocation();
		b.setType(Material.AIR);
		d = new RidingDice(data+1,l, character);
		d.update(landed);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
			@Override
			public void run() {

				character.move(landed);
			}}, 10l);
	}
	public void despawn() {
		if (d != null) d.destroy();
	}
}
