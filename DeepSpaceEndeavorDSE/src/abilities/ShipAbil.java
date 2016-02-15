package abilities;

import mech.GameSys;
import mech.Hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
//import org.newdawn.slick.KeyListener;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Vector2f;

import entity.Ship;

public class ShipAbil extends Ability { //Ability for the player's ship
	 protected float speed = 0.1f; //speed of ship
	 boolean onKeys = false; //does it move on keys
	 long lastdelay; //interval storage
	 long timeout = 300; //timeout period
	 int fireTimes= 0; //times fired
	 boolean firing = true; //firing allowed?
	 long lastCoolDelay; //last cool down/reload delay
	 long lastReloadDelay; //last reload time
	 int ticks = 0;
	 private final int amtTicks = 16;
	 
	public ShipAbil(AbilTypes id, float speed, boolean onKeys) {
		super(id);
		if(speed != 0) {
			this.speed = speed;
		}
		this.onKeys = onKeys;
	}
	
	public void setSpeed(float newSpeed) {
		this.speed = newSpeed;
	}
	public float getSpeed() {
		return this.speed;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(onKeys) {
		Vector2f nowPos = owner.getPos();
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) { //UP
			if(nowPos.y > 0) {
			nowPos.y-= speed*delta; 
			}
		 } 
		if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) { //DOWN
			if(nowPos.y < 595-(owner.getHeight())) {
			nowPos.y+= speed*delta;
			}
	 }
		if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) { //R
			if(nowPos.x < 795-owner.getWidth()) {
			nowPos.x+= speed*delta;
			}
		 } 
		if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)){ //L
			if(nowPos.x > 0) { 
			nowPos.x-= speed*delta;
			}
		 }
		 owner.setPos(nowPos);
		if(firing) {
		 if(input.isKeyDown(Input.KEY_SPACE)) {
			if(GameSys.getMills() - lastdelay >= timeout) {
				lastdelay = GameSys.getMills();
				lastReloadDelay = GameSys.getMills();
			 if(owner instanceof Ship) {
				 fireTimes++;
				 Hud.getInstance().setShotsFired(fireTimes);
				 //System.out.println(fireTimes);
				((Ship) owner).fireBullet();
			}
			}
		 }
		 else if(fireTimes > 0 && GameSys.getMills() - lastReloadDelay >= 600 ){
			 if(((Ship) owner).isHyperCannon()) {
				 if(ticks >= amtTicks*3) {
					 fireTimes --;
					 ticks = 0;
				 }
				 else {
					 ticks ++;
				 }
			 }
			 else {
				 if(ticks >= amtTicks) {
					 fireTimes --;
					 ticks = 0;
				 }
				 else {
					 ticks ++;
				 }
			 }
			 Hud.getInstance().setShotsFired(fireTimes);
		 }
		}
		 if(fireTimes >= 50) {
			 //cool down time
			 firing = false;
			 resetFiretimes();
			 lastCoolDelay = GameSys.getMills();
			 if(((Ship) owner).isHyperCannon()) {
				 ((Ship) owner).removePowerUp(3);
			 }
		 }
		
		if(GameSys.getMills() - lastCoolDelay >= timeout*8 && !firing) {
			//System.out.println(GameSys.getMills() - lastCoolDelay);
			lastCoolDelay = GameSys.getMills();
			 Hud.getInstance().setShotsFired(0);
			 firing = true;
		 }
	}
	}

	public void resetFiretimes() {
		 fireTimes = 0;
	}

}
