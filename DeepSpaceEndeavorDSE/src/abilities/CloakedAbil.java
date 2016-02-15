package abilities;

import main.Game;
import mech.GameSys;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class CloakedAbil extends Ability {
private int saturation = 255;
private int radius = 240;

	public CloakedAbil(AbilTypes id, int radius) {
		super(id);
		this.radius = radius;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(owner.getPos().y > 0) {
			owner.setColor(new Color(255,255,255,getOffset()));
		}
		else {
			owner.setColor(new Color(255,255,255,80));
		}

	}
	
	private int getOffset() {
		return (int)(180* 
				(float)(radius /GameSys.distance(owner.getPos(), Game.getPlayer().getPos()) ) );
	}

}
