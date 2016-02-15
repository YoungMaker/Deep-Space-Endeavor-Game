package abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import entity.StationPart;

public class SpawnerAbil extends Ability {
private int time = 50;
private int ticks = 0;
	public SpawnerAbil(AbilTypes id, int time) {
		super(id);
		this.time = time;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(ticks >= time && owner.getPos().y < 350) {
			((StationPart) owner).action();
			System.out.println("Action Called");
			ticks = 0;
		}
		else {
			ticks++;
		}

	}

}
