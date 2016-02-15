package entity;

import level.LevelLoader;
import main.Game;
import main.ResourceFactory;
import mech.Layer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import abilities.AbilTypes;
import abilities.MineAbil;
import abilities.interpolate;

public class Mine extends Enemy {
	private int value = 15;
	private boolean blink = false;
	public Mine(String id, int x, int y) { //do we want it in bullets?
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 5, 1),
				x, y, Game.enemies, 2.6f, 6);
		this.addAbility(new MineAbil(AbilTypes.MINE_ABIL, 450, 120));
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, 
				new Float(LevelLoader.getInstance().getLevelProperty("backspeed")),false));
		this.spawn();
	}
	
	public int getValue() {
		return this.value;
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
	public void spawnEnemy() {	}

	@Override
	public void die() {}

	@Override
	public void fire() {}

	@Override
	public int getHealth() {
		return this.health;
	}
	@Override
	 public void render(GameContainer gc, StateBasedGame sb, Graphics g) {
		super.render(gc, sb, g);
		if(blink) {
			g.setColor(Color.red);
			g.fillRect(this.getPos().x + (9*this.getScale()), this.getPos().y+(5*this.getScale()), this.getScale(), this.getScale());
			g.setColor(Color.white);
		}
	}

	public void setBlink(boolean b) {
		// TODO Auto-generated method stub
		this.blink=b;
	}
}
