package abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Vector2f;

//TODO: FIX and update!
public class BackgroundAbil extends Ability {
	private float speed = 0.189f/20;
	Vector2f loc = null;
	private float limit = 0;
	private int start;
	
	public BackgroundAbil(AbilTypes id, int speed, float limit, int start){
		super(id);
		if(speed != 0) {
			this.speed = speed;
		}
		this.limit = limit;
		this.start = start;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		loc = owner.getPos();
		loc.y += speed*delta;;
		if(loc.y == limit) {
			loc.y = start;
		}
		owner.setPos(loc);
	}

}
