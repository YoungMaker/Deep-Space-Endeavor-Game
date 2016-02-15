package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import main.Game;
import mech.Layer;



public class AnimatedEntity extends Entity {
//Color col = null;
	boolean deleteOnEnd = true;
	public AnimatedEntity(String id, SpriteSheet frames, int x, int y, Layer layer, float scale, int duration, int frameCount) {
		super(id, frames, x, y, layer, scale, duration, frameCount);
		this.spawn();
		
	}
	public AnimatedEntity(String id, SpriteSheet frames, int x, int y, Layer layer, float scale, int duration, int frameCount, boolean delete) {
		super(id, frames, x, y, layer, scale, duration, frameCount);
		this.spawn();
		this.deleteOnEnd = delete;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		motion.draw(this.getPos().x, this.getPos().y, this.getWidth(), this.getHeight());
		if(Game.isDebug()) {
			 gr.drawRect(this.getPos().x, this.getPos().y, this.getWidth(), this.getHeight());
		 }
	}
	
	public boolean isDone() {
		if(deleteOnEnd) {
			return motion.isStopped();
		}
		else {
			return false;
		}
	}
	
	public void stop() {
		motion.stop();
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		super.update(gc, sb, delta);
		motion.update(delta);
	}
}
