package entity;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;
import mech.Layer;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.TeleportAbil;

public class TeleportEnem extends Enemy implements Iship{

	public TeleportEnem(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 0,2), x, y, Game.enemies, Enemy.ENEM_SCALE, 7);
		this.addAbility(new TeleportAbil(AbilTypes.TELE_ABIL, 200));
		this.health = Iship.ELEVATED_ENEM_HEALTH+3;
		this.spawn();
	}

	@Override
	public int[] getGunpoints() {
		return null;
	}

	@Override
	public void setHealth(int newHealth) {
		this.health = newHealth;
	}

	@Override
	public void spawnEnemy() {}

	@Override
	public void die() {}

	@Override
	public void fire() {
		new Mine("m-" + GameSys.random.nextInt(), (int)this.getCenter().x, 
				(int) this.getCenter().y);
	}

	@Override
	public int getValue() {
		return Iship.SHOOTER_SCORE_VAL +2;
	}

	@Override
	public int getHealth() {
		return health;
	}

}
