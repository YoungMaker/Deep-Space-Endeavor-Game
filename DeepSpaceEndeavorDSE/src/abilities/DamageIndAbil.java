package abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import entity.Entity;

public class DamageIndAbil extends Ability{

Entity attached;
	public DamageIndAbil(AbilTypes id, Entity attached) {
		super(id);
		this.attached = attached;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(attached != null) {
			owner.setPos(attached.getPos());
		}
		else{
			
		}
	}

}
