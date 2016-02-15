package entity;

import abilities.AbilTypes;
import abilities.SuicideAbil;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;

public class suicideEnemy extends Enemy{

	public suicideEnemy(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 3, 0), x, y, Game.enemies, Enemy.ENEM_SCALE, Iship.SUICIDE_ID);
		if(Game.ships.entities.size() > 0) {
			this.addAbility(new SuicideAbil(AbilTypes.SUCIDIE_ABIL, 0.126f, Game.ships.entities.get(0)));
		}
		this.spawnEnemy();
		this.health = Iship.REGULAR_ENEM_HEALTH;
	}

	@Override
	public void spawnEnemy() {
		this.layer.add(this);
	}

	@Override
	public void die() {
		//depreciated?
	}

	@Override
	public int[] getGunpoints() {
		return null; //NOT USED FOR THIS ENEMY
	}

	@Override
	public int getValue() {
		return Iship.SUICIDE_SCORE_VAL;
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
	public void fire() {} //NOT USED FOR THIS ENEMY


}