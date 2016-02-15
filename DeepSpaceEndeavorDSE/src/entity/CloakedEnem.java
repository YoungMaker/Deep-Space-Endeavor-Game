package entity;

import main.Game;
import main.ResourceFactory;
import mech.Layer;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.CloakedAbil;
import abilities.interpolate;

public class CloakedEnem extends Enemy {

	public CloakedEnem(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 3,1), x, y, Game.enemies, Enemy.ENEM_SCALE, 8);
		this.health = 1;
		this.addAbility(new CloakedAbil(AbilTypes.ADVANCED, 200));
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, 0.055f, false));
		this.spawn();
	}

	@Override
	public int[] getGunpoints() {return null;}

	@Override
	public void setHealth(int newHealth) {
		health = newHealth;

	}

	@Override
	public void spawnEnemy() {}

	@Override
	public void die() {}

	@Override
	public void fire() {

	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return Iship.SIDEWAYS_SCORE_VAL;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
