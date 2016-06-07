package com.sanjay900.marioParty.objects;

import java.util.UUID;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.event.NavigationCompleteEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.Gravity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import com.sanjay900.marioParty.FaceUtil;
import com.sanjay900.marioParty.MarioParty;

public class Character implements Listener{
	private MarioParty plugin = MarioParty.getInstance();
	public NPC npc;
	private UUID playerUUID;
	private CharacterType type;
	Dice dice;
	private BlockFace direction;
	private int landed = -1;
	int hitcounter = 0;
	BukkitTask task;
	public Character(String name, CharacterType type, Player pl) {
		this.type = type;
		npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
		npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, type.name);
		this.playerUUID = pl.getUniqueId();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	public void spawn(Location l, BlockFace facing) {
		npc.spawn(l.setDirection(FaceUtil.faceToVector(facing)));
		this.direction = facing;
	}
	public void roll() {
		dice = new Dice();
		dice.spawn(npc);
	}
	public Player getPlayer() {
		return Bukkit.getPlayer(playerUUID);
	}
	public void hitDice() {
		if (dice == null || !dice.isRolling()) {
			getPlayer().sendMessage("You can't hit a dice that doesnt exist!");
		} else {
			final Location l = npc.getEntity().getLocation().clone();
			npc.teleport(npc.getEntity().getLocation().add(0, 3, 0), TeleportCause.PLUGIN);
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
				@Override
				public void run() {
					dice.explode(Character.this);
				}}, 1l);
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
				@Override
				public void run() {
					npc.teleport(l.add(0, 0.1, 0), TeleportCause.PLUGIN);
				}}, 3l);
		}
	}

	public enum CharacterType {
		MARIO("mario"),LUIGI("sonic"),SONIC("sonic"),BOWSER("sonic"),PEACH("sonic"),YOSHI("sonic"),TOAD("sonic"),LINK("sonic"),ZELDA("sonic"),COMMANDERVIDEO("sonic"),GOMEZ("sonic"),PACMAN("sonic"),SAMUS("sonic"),GHOST("sonic"),MASTERCHIEF("sonic"),DONKEYKONG("DK");
		CharacterType(String s) {
			this(s,false);
		}
		Boolean blob;
		String name;
		CharacterType(String s, boolean b) {
			name = s;
			blob = b;
		}
	}

	public void move(int landed) {
		this.landed = landed;
		
		npc.getNavigator().setTarget(npc.getEntity().getLocation().add(FaceUtil.faceToVector(direction,2)));
	}
	@EventHandler
	public void targetFinished(NavigationCompleteEvent evt) {
		landed--;
		dice.getD().update(landed);
		if (landed == 0) {
			dice = null;
			getPlayer().sendMessage("MOVED!");
		} else {
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
				@Override
				public void run() {

					move(landed);
				}
			}, 10l);
		}
	}
	public void despawn() {
		if (dice != null) dice.despawn();
		npc.destroy();
	}
}
