package abilities;

import mech.GameSys;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entity.Entity;

import abilities.util.interpolation;

public class SuicideAbil extends Ability {

	float speed = 0.2f;
	private float angle;
	private Entity target;
	long lastUpdate = 0;

	public SuicideAbil(AbilTypes id, float speed, Entity target) {
		super(id);
		if(speed != 0) {
			this.speed = speed;
		}
		this.target = target;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
	if(GameSys.getMills() - lastUpdate >= 10000 || lastUpdate == 0) {
		lastUpdate = GameSys.getMills();
		//seek
		Vector2f ownerPos = owner.getPos();
		Vector2f targetPos = new Vector2f(target.getPos().x + target.getCenterOfRotation().x, target.getPos().y + target.getCenterOfRotation().y);
//		System.out.println(targetPos.x + " " + targetPos.y);
		 angle = (float) (Math.atan2(targetPos.x - ownerPos.x, targetPos.y - ownerPos.y) * 180 / Math.PI);
			owner.rotate(angle*-1);
			//System.out.println(owner.getRoatation());
	}
		interpolation.interpolate(owner, owner.getRoatation(), false, speed, delta);
	}

}
