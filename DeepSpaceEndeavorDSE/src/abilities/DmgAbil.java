package abilities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class DmgAbil extends Ability {
private boolean isDamaging = false;
private boolean increasing = true;
private int intensity = 255;

	public DmgAbil(AbilTypes id) {
		super(id);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(isDamaging) { //if the owner took some damage
			if(increasing ) { //are we increasing the intensity?
				if(intensity >= 50) { //if its less than max
					intensity -=8; 
					owner.setColor(new Color(255,255,255,intensity)); 
				}
				else {
				  increasing = false; //now we are decreasing
				}
			}
			else if(!increasing) { //if decreasing
				if(intensity <= 255) {
					intensity +=8;
					owner.setColor(new Color(255,255,255,intensity));
				}
				else {
				  isDamaging = false; //we're done once its done decreasing.
				  intensity = 255;
				  increasing = true;
				  //System.out.println("Damage animation abil: finished fading");
				}
			}
		}
	}

	public void setDamaging() {
		this.isDamaging = true;
	}

	public boolean getDamaging() {
		return isDamaging;
	}

}
