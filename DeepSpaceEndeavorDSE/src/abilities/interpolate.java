package abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import abilities.util.interpolation;

public class interpolate extends Ability {

private float speed;
private boolean dir;
float angle = 0;

	public interpolate(AbilTypes id, float speed, boolean dir, float angle) {
		super(id);
		this.speed = speed;
		this.angle = angle;
		this.dir = dir;
	}
	
	public interpolate(AbilTypes id, float speed, boolean dir) {
		super(id);
		this.speed = speed;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {

			interpolation.interpolate(owner, angle, dir, speed, delta);
	}

}
