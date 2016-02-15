package abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class PongBounceAbil extends Ability {

	private boolean xDir = true;
	private boolean yDir = true;
	
	private int ylim = 0;
	private int xlim = 0;
	
	private float xSpeed = 0.8f;
	private float ySpeed = 0.4f;
	
	public PongBounceAbil(AbilTypes id, int xlim, int ylim, float xSpeed, float ySpeed) {
		super(id);
		this.xlim = xlim;
		this.ylim = ylim;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		owner.setPos(new Vector2f(owner.getPos().x + moveX(),owner.getPos().y+ moveY()));
		//System.out.println("Bounce Abil: offsets: X: " + moveX() + " Y: " + moveY());
	}
	
	private float moveY() {
		if(yDir) { //y component
			if(owner.getPos().y > ylim) {
				return -ySpeed;
			}
			else {
				yDir  = false;
				return ySpeed;
			}
		}
		else if(!yDir){
			if(owner.getPos().y+owner.getHeight() < 600) {
				return ySpeed;
			}
			else {
				yDir = true;
				return -ySpeed;
			}
		}
		return 0;
	}

	private float moveX() {
		if(xDir) { //x component
			if(owner.getPos().x > xlim) {
				return -xSpeed;
			}
			else {
				xDir  = false;
				return xSpeed;
			}
		}
		else if(!xDir) {
			if(owner.getPos().x+owner.getWidth() < 800) {
			 return xSpeed;	
			}
			else {
				xDir = true;
				return -xSpeed;
			}
		}
		return 0;
	}

}