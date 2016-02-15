package entity;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;
import abilities.AbilTypes;
import abilities.SidewaysAbil;

public class SidewaysEnemy extends Enemy {

	public SidewaysEnemy(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 4,0), x, y, Game.enemies, Enemy.ENEM_SCALE, 3);
		this.addAbility(new SidewaysAbil(AbilTypes.SIDEWAYS_ABIL, x,y));
		this.spawn();
		this.health = Iship.ELEVATED_ENEM_HEALTH;
	}

	@Override
	public int[] getGunpoints() {
		return Iship.SIDEWAYS_GUNPOINTS;
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
		// depreciated

	}

	@Override
	public void fire() {
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.GUNSHIP_GUNPOINTS[0], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f, false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.GUNSHIP_GUNPOINTS[1], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f ,false);
	}

	@Override
	public int getValue() {
		return Iship.SHOOTER_SCORE_VAL;
	}

	@Override
	public int getHealth() {
		return this.health;
	}

}
