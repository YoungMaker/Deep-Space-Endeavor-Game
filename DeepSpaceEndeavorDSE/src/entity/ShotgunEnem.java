package entity;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;
import mech.Layer;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.GunShipAbil;
import abilities.interpolate;

public class ShotgunEnem extends Enemy {

	public ShotgunEnem(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 5,0), x, y, Game.enemies, Enemy.ENEM_SCALE, 7);
		this.addAbility(new GunShipAbil(AbilTypes.GUN_SHIP_ABIL, 1500));
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, 0.048f, false));
		this.health = 2;
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
		Bullet b1 = new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+9, (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 0.9f, false,35);
			b1.rotate(-45);
		Bullet b2 = new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+33, (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 0.9f ,false,35);
			b2.rotate(45);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+21, (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.0f, false);
	}

	@Override
	public int getValue() {
		return Iship.SHOOTER_SCORE_VAL + 20;
	}

	@Override
	public int getHealth() {
		return this.health;
	}

}
