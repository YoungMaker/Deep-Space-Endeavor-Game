package abilities;

import main.ResourceFactory;
import mech.GameSys;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import entity.Explosion;

import abilities.util.interpolation;

public class BulletAbil extends Ability { //ability for bullets
private float speed;
private boolean dir;
private int time =0 ;
private int ticks = 0;
	public BulletAbil(AbilTypes id, float speed, boolean dir) {
		super(id);
		this.speed = speed; //set speed 
		this.dir = dir; //set direction
	}
	public BulletAbil(AbilTypes id, float speed, boolean dir, int time) {
		super(id);
		this.speed = speed; //set speed 
		this.dir = dir; //set direction
		this.time= time;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(time > 0) {
			if(ticks >= time) {
				ticks =0;
				owner.delete();
				explode();
			}
			else {
				ticks ++;
			}
		}
		interpolation.interpolate(owner, owner.getRoatation(), dir, speed, delta); //move it!
	}
	
	public void explode() {
		if(dir) {
			new Explosion("Ex-" + GameSys.random.nextInt(), 
					(int)owner.getPos().x, 
					(int)owner.getPos().y, 
					ResourceFactory.getInstance().getSheet("EXPLOSION"), 
					owner.getScale());
		}
		else {
			new Explosion("Ex-" + GameSys.random.nextInt(), 
					(int)owner.getPos().x, 
					(int)owner.getPos().y, 
					ResourceFactory.getInstance().getSheet("EEXPLOSION"), 
					owner.getScale());
		}
	}
}
