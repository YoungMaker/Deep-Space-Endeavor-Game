package abilities;

import main.Game;
import main.GameStates;
import mech.Colision;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entity.Ship;

public class PushAbil extends Ability { //TODO: cant figure out collisoning right now- abandoning. Just kills you
//private float speed = 0;
//private boolean push = false;
	public PushAbil(AbilTypes id, float speed) {
		super(id);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Ship player = Game.getPlayer();
		if(Colision.colideBoxes(owner, player )) {
			Game.updateHealth(100, player);
		}
		else  {
			
		}

	}

}
