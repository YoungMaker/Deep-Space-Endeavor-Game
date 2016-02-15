package abilities;

import mech.GameSys;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entity.Entity;

public class JetEffect extends Ability {
private Entity attached = null;
private int ticks = 0;
private int offsetx,offsety;
private int thresh = GameSys.random.nextInt(2)+1;
private int saturation = (int) Math.floor((120*GameSys.random.nextFloat())+100);
private float lastRoatation  = 0;
	
	public JetEffect(AbilTypes id, Entity attached, int offsetx, int offsety) {
		super(id);
		this.attached = attached;
		this.offsetx = offsetx;
		this.offsety = offsety;
		//owner.setColor(new Color(255,255,255, saturation));
		lastRoatation = attached.getRoatation();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		owner.setPos(new Vector2f(attached.getPos().x+offsetx, (int)attached.getPos().y+offsety));
		if(attached.getRoatation() != lastRoatation) {
			owner.rotate(attached.getRoatation() - owner.getRoatation());
		}
		if(ticks==0) {owner.setColor(new Color(255,255,255,saturation));}
		if(ticks >= thresh) {
			saturation = (int) Math.floor((120*GameSys.random.nextFloat())+100);
			thresh = GameSys.random.nextInt(4)+1;
			ticks = 0;
		}
		else {
			ticks++;
		}
	}
}
