package abilities;

import level.LevelLoader;
import mech.GameSys;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import abilities.util.interpolation;

import entity.SidewaysEnemy;

public class SidewaysAbil extends Ability {
private long lastUpdate = 0;
float speed = 0.16f;
Vector2f startPos = null;
	public SidewaysAbil(AbilTypes id, int x, int y) {
		super(id);
		startPos = new Vector2f(x,y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(GameSys.getMills() - lastUpdate > 2000) { //if interval
			lastUpdate = GameSys.getMills(); //reset interval
			if(owner instanceof SidewaysEnemy) { //if the owner of this is a gunship
				((SidewaysEnemy)owner).fire(); //cast the owner and fire it!
			}
		}
		if(owner.getPos().y <= 200) { //if were up above halfway
			interpolation.interpolate(owner, owner.getRoatation(), false, speed , delta); //movedown
		}
		else {
			if(startPos.x > 300){ //if where on the right side
				owner.setPos(new Vector2f(owner.getPos().x - (speed+3f), (float)(owner.getPos().y)));
			}
			else if(startPos.x < 300) { //on the left
				owner.setPos(new Vector2f(owner.getPos().x + (speed+3f), (float)(owner.getPos().y)));
			}
			interpolation.interpolate(owner, owner.getRoatation(), false, new Float(LevelLoader.getInstance().getLevelProperty("backspeed")) , delta);
		}
	}
	
	

}
