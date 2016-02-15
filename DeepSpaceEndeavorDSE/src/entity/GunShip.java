package entity;

import abilities.AbilTypes;
import abilities.GunShipAbil;
import abilities.interpolate;
import level.LevelLoader;
import main.Game;
import main.ResourceFactory;
//import mech.Layer;
import mech.GameSys;

//import org.newdawn.slick.Image;

public class GunShip extends Enemy {

	public GunShip(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 2,0), 
				x, y, Game.enemies, Enemy.ENEM_SCALE, Iship.GUNSHIP_ID);
		this.addAbility(new GunShipAbil(AbilTypes.GUN_SHIP_ABIL, 2400));
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, 0.085f, false));
		//addEffect(new Jet("enem jet" + GameSys.random.nextInt(), 1, 0.6f, this, 21,-3));
		this.spawn();
		this.health = Iship.REGULAR_ENEM_HEALTH;
	}

	@Override
	public int[] getGunpoints() {
		return Iship.GUNSHIP_GUNPOINTS;
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public void setHealth(int newHealth) {
		this.health = newHealth;
	}

	@Override
	public void spawnEnemy() {}

	@Override
	public void die() { }

	@Override
	public int getValue() {
		return Iship.SHOOTER_SCORE_VAL;
	}

	@Override
	public void fire() {
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.GUNSHIP_GUNPOINTS[0], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f, false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.GUNSHIP_GUNPOINTS[1], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f ,false);
		
	}

}
