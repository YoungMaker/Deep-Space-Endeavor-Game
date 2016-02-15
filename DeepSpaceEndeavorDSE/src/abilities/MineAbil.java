package abilities;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import entity.Explosion;
import entity.Iship;
import entity.Ship;
import entity.Mine;

public class MineAbil extends Ability {
private int ticks = 0;
private int bTicks = 0;
private int thresh = 500;
private int radius = 10;
	public MineAbil(AbilTypes id, int thresh, int radius) {
		super(id);
		this.thresh = thresh;
		this.radius = radius;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(bTicks == 100) {
			((Mine) owner).setBlink(true);
		}
		else if(bTicks == 180) {
			((Mine) owner).setBlink(false);
			bTicks = 0;
		}
		bTicks++;
			if(ticks <= thresh) {
				ticks++;
				//System.out.println("Mine controller: " + GameSys.distance(owner.getPos(), Game.getPlayer().getPos()));
				if(GameSys.distance(owner.getPos(), Game.getPlayer().getPos()) <= radius) {
						Game.updateHealth(Iship.COLLISION_DAMAGE*2, Game.getPlayer());
						owner.delete();
						new Explosion("Ex-" + GameSys.random.nextInt(), 
								(int)owner.getCenter().x-120, 
								(int)owner.getCenter().y-120, 
								ResourceFactory.getInstance().getSheet("EEXPLOSION"),
								15.0f);
					}
				//System.out.println("Mine abil: distance from target: " + GameSys.distance(owner.getPos(), Game.ships.entities.get(i).getPos()));
			}
			else {
				new Explosion("Ex-" + GameSys.random.nextInt(), 
						(int)owner.getCenter().x-120, 
						(int)owner.getCenter().y-120, 
						ResourceFactory.getInstance().getSheet("EEXPLOSION"),
						15.0f);
				if(GameSys.distance(owner.getPos(), Game.getPlayer().getPos()) <= radius) {
					Game.updateHealth(Iship.COLLISION_DAMAGE*2, Game.getPlayer());
				}
				owner.delete();
			}
	}

}
