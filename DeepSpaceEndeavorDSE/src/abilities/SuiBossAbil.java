package abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import entity.Boss;

public class SuiBossAbil extends Ability {
private boolean dir = true; //true = right. false = left.
private int ticks = 0;
private int thresh =0 ;

	public SuiBossAbil(AbilTypes id, int thresh) {
		super(id);
		this.thresh = thresh;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(ticks == 100) {
			((Boss) owner).fire();
		}
		if(ticks > thresh) {
			ticks = 0;
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
	}

}
