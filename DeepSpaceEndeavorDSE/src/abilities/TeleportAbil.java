package abilities;

import main.ResourceFactory;
import mech.GameSys;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entity.Explosion;
import entity.Enemy;

public class TeleportAbil extends Ability {
private int tTicks = 0;
private int cThresh = 0;

	public TeleportAbil(AbilTypes id, int startThresh) {
		super(id);
		cThresh = startThresh;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(tTicks > cThresh) {
			tTicks = 0;
			new Explosion("Ex-" + GameSys.random.nextInt(), 
					(int)owner.getPos().x, 
					(int)owner.getPos().y, 
					ResourceFactory.getInstance().getSheet("EXPLOSION"), //TODO, change to purple blast
					6.0f);
			((Enemy) owner).fire();
			if(GameSys.random.nextBoolean()) {
				owner.setPos(new Vector2f(owner.getPos().x + GameSys.random.nextInt(200)+20, owner.getPos().y +GameSys.random.nextInt(80)+10 ));
			}
			else {
				owner.setPos(new Vector2f(owner.getPos().x - GameSys.random.nextInt(200)+20, owner.getPos().y + GameSys.random.nextInt(80)+10 ));
			}
		}
		else {
			tTicks++;
		}
		//owner.setCenterOfRotation(new Vector2f(owner.getPos().x+ owner.getCenter().x, owner.getPos().y+ owner.getCenter().y));
		owner.rotate(5.5f);
		
	}

}
