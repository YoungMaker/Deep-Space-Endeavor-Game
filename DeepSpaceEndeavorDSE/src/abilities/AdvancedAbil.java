package abilities;

import mech.GameSys;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import abilities.util.interpolation;

import entity.AdvancedEnem;
import entity.Entity;

public class AdvancedAbil extends Ability {

Entity target;
float speed = 0.2f;
private float angle;
private long lastUpdate;
private boolean angled = false;

	public AdvancedAbil(AbilTypes id, Entity target, float speed) {
		super(id);
		this.target = target;
		this.speed = speed;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
	if(!angled ) {
		if (target != null) {
				Vector2f ownerPos = owner.getPos();
				Vector2f targetPos = new Vector2f(target.getPos().x + target.getCenterOfRotation().x, target.getPos().y + target.getCenterOfRotation().y);
				angle = (float) (Math.atan2(targetPos.x - ownerPos.x, targetPos.y - ownerPos.y) * 180 / Math.PI);
				owner.rotate(angle*-1);
				angled = true;
		}
	}
		if(GameSys.getMills() - lastUpdate > 950) { //if interval
			lastUpdate = GameSys.getMills(); //reset interval
			if(owner instanceof AdvancedEnem) { //if the owner of this is a gunship
				((AdvancedEnem)owner).fire(); //cast the owner and fire it!
			}
		}
		interpolation.interpolate(owner, owner.getRoatation(), false, speed , delta);	
	}
}