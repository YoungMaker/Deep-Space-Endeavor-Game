package abilities;

import main.Game;
import mech.GameSys;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entity.Entity;

public class TurretAbil extends Ability {
private float angle = 0;
private float lastAngle = 0;
	public TurretAbil(AbilTypes id) {
		super(id);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Vector2f ownerPos = owner.getPos();
		Entity target = Game.getPlayer();
		Vector2f targetPos = new Vector2f(target.getPos().x + target.getCenterOfRotation().x, target.getPos().y + target.getCenterOfRotation().y);
		 angle = (float) (Math.toDegrees(Math.atan2(targetPos.x - ownerPos.x, targetPos.y - ownerPos.y)));
			if(angle*-1 != lastAngle) {
				owner.rotate((angle*-1));
		 		lastAngle = angle*-1;
		 		//System.out.println(owner.getRoatation() - (angle*-1));
			}
	}

}
