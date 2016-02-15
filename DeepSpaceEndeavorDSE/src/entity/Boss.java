package entity;

import main.Game;
import mech.Layer;

import org.newdawn.slick.Image;

public abstract class Boss extends Enemy {

	private int baseHealth = 0;
	
	public static final float BOSS_SCALE = 7.0f;
	public Boss(String id, Image img, int x, int y, int health,
			int type) {
		super(id, img, x, y, Game.enemies, BOSS_SCALE, -1);
		this.health = health;
		this.baseHealth = health;
	}

	@Override
	public abstract int[] getGunpoints(); //NOT USED, not yet..

	@Override
	public void setHealth(int newHealth) {
		this.health = newHealth;
	}

	public abstract void spawnNewEnem();
	
	public void spawnEnemy() {
		this.layer.add(this);
	}
	
	@Override
	public void takeDamage() {/*do nothing*/}

	@Override
	public void die() {
		if(this.equals(Game.getBoss())) {
			Game.clearBoss();
		}
	}

	@Override
	public abstract void fire();

	@Override
	public int getValue() {
	//returns score value, 1000 times the total health
		return 100*baseHealth; //TODO: fix that this is added when it takes damage, not when it dies
	}

	@Override
	public int getHealth() {
		return this.health;
	}
	
	public int getBaseHealth() {
		return baseHealth;
	}

}
