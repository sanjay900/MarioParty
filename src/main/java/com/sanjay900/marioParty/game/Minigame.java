package com.sanjay900.marioParty.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Minigame {
	public String Name;
	public abstract void startGame();
	public abstract void stopGame();
	
}
