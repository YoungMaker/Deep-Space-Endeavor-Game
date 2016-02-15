package entity;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;
import mech.Layer;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.FilpAbil;

public class FlipEnemy extends Enemy implements Iship{

	public FlipEnemy(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 4,1), x, y, Game.enemies, Enemy.ENEM_SCALE, 4);
		this.addAbility(new FilpAbil(AbilTypes.FLIP_ABIL, 0.09f, 3.5f, x));
		this.health = Iship.ELEVATED_ENEM_HEALTH;
		//this.setCenterOfRotation(this.getCenter());
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
	public void spawnEnemy() {
		//depreciated
	}

	@Override
	public void die() {
     //nothing ondeath
	}

	@Override
	public void fire() {
       // System.out.println("Flip Fired");
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getCenter().x, 
				(int) this.getCenter().y, Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f, false);
		new Mine("m-" + GameSys.random.nextInt(), (int)this.getCenter().x, 
				(int) this.getCenter().y+25);
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return Iship.SIDEWAYS_SCORE_VAL;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub;
		return this.health;
	}

}
