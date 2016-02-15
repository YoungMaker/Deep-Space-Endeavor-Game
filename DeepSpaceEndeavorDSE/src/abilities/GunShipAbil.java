package abilities;

import mech.GameSys;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.state.StateBasedGame;

import abilities.util.interpolation;

import entity.Enemy;
import entity.Ship;
import entity.GunShip;

public class GunShipAbil extends Ability { //ability for enemy 2, a regular gunship
long lastUpdate0 = 0;
long lastUpdate = 0;
float speed = 0.100f;
long thresh = 2068;
	public GunShipAbil(AbilTypes id, long thresh) {
		super(id);
		this.thresh = thresh;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
				if(GameSys.getMills() - lastUpdate > thresh) { //if interval
					lastUpdate = GameSys.getMills(); //reset interval
					if(owner instanceof Enemy) { //if the owner of this is an enemy
						((Enemy)owner).fire(); //cast the owner and fire it!
					}
					else{
						((Ship) owner).fireBullet();
					}
				}
		}
}
