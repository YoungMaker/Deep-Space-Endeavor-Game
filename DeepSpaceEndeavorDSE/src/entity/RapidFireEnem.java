package entity;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;
import mech.Layer;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.GunShipAbil;
import abilities.PongBounceAbil;
import abilities.interpolate;

public class RapidFireEnem extends Enemy {

	public RapidFireEnem(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 1,2), x, y, Game.enemies, Enemy.ENEM_SCALE, 9);
		this.addAbility(new GunShipAbil(AbilTypes.GUN_SHIP_ABIL, 100));
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, 0.025f, false));
		this.addAbility(new PongBounceAbil(AbilTypes.SIDEWAYS_ABIL, 10, 600, 1.5f, 0f));
		this.spawn();
		this.health = 3;
	}

	@Override
	public int[] getGunpoints() {return null;}

	@Override
	public void setHealth(int newHealth) {
	 this.health = newHealth;
	}

	@Override
	public void spawnEnemy() {
	}

	@Override
	public void die() {
	}

	@Override
	public void fire() {
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.GUNSHIP_GUNPOINTS[0], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f, false, 25);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.GUNSHIP_GUNPOINTS[1], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f ,false, 25);
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 80;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
