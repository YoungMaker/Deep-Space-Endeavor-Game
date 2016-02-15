package mech.effect;

import mech.GameSys;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class star extends Effect {
 private Color col = null;
 private int size = 0;
 private float speed;

	
	public star(int x, int y, EffectLayer layer, float maxSpeed) {
		super(x, y, layer);
		this.spawn();
		this.speed = (GameSys.random.nextFloat()* maxSpeed) + 0.002f;
		this.size = GameSys.random.nextInt(2)+2;
		int colVal = (int) ( 130 * ( (float)(speed/ maxSpeed) + 0.002f) ) + 100 ;
		this.col = new Color(colVal, colVal, colVal);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) {
		g.setColor(col);
		g.fillRect(this.getPos().x, this.getPos().y, size, size);
		g.setColor(Color.white);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		this.setPos(this.getPos().x, this.getPos().y + (speed*delta));
	}

}
