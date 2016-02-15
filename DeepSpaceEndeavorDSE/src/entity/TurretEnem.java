package entity;

import level.LevelLoader;
import main.Game;
import main.ResourceFactory;
import mech.GameSys;
import mech.Layer;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.TurretAbil;
import abilities.interpolate;

public class TurretEnem extends Enemy {

	public TurretEnem(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("STATION_PARTS", 1,0), x, y, Game.enemies, Enemy.ENEM_SCALE, 12);
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, Float.parseFloat(LevelLoader.getInstance().getLevelProperty("backspeed")), false ));
		this.addAbility(new TurretAbil(AbilTypes.TARGET_ABIL));
		this.health = 4; 
		this.spawn();
	}

	@Override
	public int[] getGunpoints() {return null;}

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
		Bullet b1 = new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x, (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 0.9f, false,35);
		b1.rotate(this.getRoatation());
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
