package abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entity.Entity;

public class GunFireAbil extends Ability {

private Entity attachedTo;
private int[] gunpoints;
boolean what;
	
	public GunFireAbil(AbilTypes id, Entity attachedTo, int[] gunpoints, boolean which) {
		super(id);
		this.attachedTo = attachedTo; //what object is it attached to
		this.gunpoints = gunpoints; //attach the bullets to gunpoints of its owner
		this.what = which; //which gunpoint are we firing?* *requires clarification
	}
//have no idea what this does... shit
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(!what)
		 owner.setPos(new Vector2f(attachedTo.getPos().x+gunpoints[0],attachedTo.getPos().y));
		 else {
			 owner.setPos(new Vector2f(attachedTo.getPos().x+gunpoints[1],attachedTo.getPos().y));
		 }
	}

}
