package entity;

import org.newdawn.slick.geom.Vector2f;

import abilities.AbilTypes;
import abilities.AdvancedAbil;
import main.Game;
import main.ResourceFactory;
import mech.GameSys;

public class AdvancedEnem extends Enemy {
Vector2f startpos;
	public AdvancedEnem(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 1,0), x, y, Game.enemies, Enemy.ENEM_SCALE+0.5f,5);
		//TODO: abil? (combine suicide with mines!)
		this.addAbility(new AdvancedAbil(AbilTypes.ADVANCED, Game.ships.entities.get(0), 0.166f )); //0.176f
		this.spawn(); 
		startpos = new Vector2f(x,y);
		this.health = Iship.REGULAR_ENEM_HEALTH;
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
		// unused

	}

	@Override
	public void die() {

	}

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
		return this.health;
	}

}
