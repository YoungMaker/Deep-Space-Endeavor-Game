package abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entity.Boss;

public class LungeAbil extends Ability { //TODO: not working. Abandon
	private boolean dir = true; //true = right. false = left.
	private int ticks = 0;
	private boolean yDir;
	private boolean lunge;
	
	public LungeAbil(AbilTypes id, int thresh) {
		super(id);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(ticks == 100) {
			((Boss) owner).fire();
		}
		if(ticks > 170) {
			ticks = 0;
			lunge = true;
			((Boss) owner).spawnNewEnem();
		}
		else {
			ticks++;
		}
			if(dir) {
				if(owner.getPos().x > 0) {
					owner.setPos(new Vector2f(owner.getPos().x - 0.8f, owner.getPos().y));
				}
				else {
					dir = false;
				}
			}
			else {
				if(owner.getPos().x+owner.getWidth() < 800) {
					owner.setPos(new Vector2f(owner.getPos().x + 0.8f, owner.getPos().y));
				}
				else {
					dir = true;
				}
			}
			
			if(lunge) {
				if(yDir) {
					if(owner.getPos().x+owner.getHeight() < 400) {
						owner.setPos(new Vector2f(owner.getPos().y + 2.0f, owner.getPos().y));
					}
					else {
						yDir = false;
					}
				}
				else {
					if(owner.getPos().x+owner.getHeight() > 0) {
						owner.setPos(new Vector2f(owner.getPos().y -2.0f, owner.getPos().y));
					}
					else {
						lunge = false;
					}
				}
			}
			
	}

}
