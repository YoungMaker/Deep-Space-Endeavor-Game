package abilities;

import level.LevelLoader;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import abilities.util.interpolation;

public class PupAbil extends Ability { //power up ability
private float initalX = 0.0f; // set to the inital x value 
private final float thresh = 8;
private boolean dir = false; //right f, left t
	public PupAbil(AbilTypes id, float x) {
		super(id);
		this.initalX = x;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
	//	Vector2f ownpos = owner.getPos(); //TODO: make the pups move back and forth in a singsong manner?
		interpolation.interpolate(owner, owner.getRoatation(), false, new Float(LevelLoader.getInstance().getLevelProperty("backspeed")), delta); //interpolate at background speed
	 if(Math.abs(initalX - owner.getPos().x) >= thresh) {
			dir = !dir;
			//System.out.println("initial x: " + initalX + " current x: " + owner.getPos().x);
		}
			if(dir) {
			  owner.setPos(new Vector2f(owner.getPos().x - 0.7f, owner.getPos().y));
			}
			else {
				owner.setPos(new Vector2f(owner.getPos().x + 0.7f, owner.getPos().y));
		}
	}

}
