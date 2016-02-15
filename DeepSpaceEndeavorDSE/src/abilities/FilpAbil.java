package abilities;

import java.util.Random;

import level.LevelLoader;
import mech.GameSys;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import abilities.util.interpolation;
import entity.Enemy;

public class FilpAbil extends Ability {
	
private float rotateSpeed = 2.5f; //rotation speed
private float moveSpeed = 0.2f; //movement speed 
private float initialx = 0; 
private int thresh; //rotate start threshold
private boolean hasFired = false;

	public FilpAbil(AbilTypes id, float speed, float rtateSpeed, float intialx) {
		super(id);
		this.initialx = intialx;
		thresh = GameSys.random.nextInt(25)+5;
		System.out.println("Flip Code: Thresh: " + thresh);
		this.rotateSpeed = rtateSpeed;
		this.moveSpeed = speed;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		// TODO if its less than 300 go left, greater than 300 go right. Rotate constantly. If angle is +/- 5 deg (or 0?) then fire
		if(owner.getPos().y > thresh) {
			if(initialx < 300) {
				owner.setPos(new Vector2f(owner.getPos().x + ((0.30f*moveSpeed)*delta), owner.getPos().y));
			}
			else {
				owner.setPos(new Vector2f(owner.getPos().x - ((0.30f*moveSpeed)*delta), owner.getPos().y));
			}
			owner.rotate( rotateSpeed);
		//	System.out.println("Flip Code: Current rotation: " + owner.getRoatation());
			if(Math.abs(owner.getRoatation()) > 360 || Math.abs(owner.getRoatation())  < 5 ) { 
				if(!hasFired) { // have we already fired once?
					hasFired = true;
					 //System.out.println("Flip Code: Current rotation: " + owner.getRoatation() + " has Fired: " + hasFired);
					((Enemy)owner).fire();
				}
			}
			 else {
				hasFired = false; 
				
			}
			interpolation.interpolateLR(owner, 0, false, new Float(LevelLoader.getInstance().getLevelProperty("backspeed")), delta);
		}
		else {
			interpolation.interpolateLR(owner, owner.getRoatation(), false, moveSpeed, delta);
		}
	}

}
