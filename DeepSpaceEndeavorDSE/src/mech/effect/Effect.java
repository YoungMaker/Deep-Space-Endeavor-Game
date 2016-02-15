package mech.effect;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Effect {
	private Vector2f loc = new Vector2f();
	private EffectLayer layer;
	private boolean isFinished = false;
	
	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Effect(int x, int y, EffectLayer layer) {
		loc = loc.set(x, y);
		this.layer = layer;
	}
	
	public abstract void render(GameContainer gc, StateBasedGame sb, Graphics g);
	public abstract void update(GameContainer gc, StateBasedGame sb, int delta);
	
	public void spawn() {
		this.layer.add(this);
	}
	
	public void setPos(Vector2f pos) {
		loc = pos;
	}
	public void setPos(float x, float y) {
		loc = new Vector2f(x,y);
	}
	public Vector2f getPos() {
		return loc;
	}
	
}
