package entity;

import main.Game;
import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.PupAbil;

public class powerUp extends Entity{
 int type;
	public powerUp(String id, Image sprite, int x, int y, int type) {
		super(id, sprite, x, y, Game.pups, 2.2f);
		this.addAbility(new PupAbil(AbilTypes.PUP_ABIL, x));
		this.spawn();
		this.type = type; 
	}

	public int getType() {
		return type;
	}
	public void setType(int newType) {
		this.type = newType;
	}
}
