package entity;

import main.Game;
import main.ResourceFactory;
import mech.Layer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import abilities.AbilTypes;
import abilities.JetEffect;

public class Jet extends Entity {
	public Jet(String id, int color, float scale, Entity attach, int offsetx, int offsety) {
		super(id, ResourceFactory.getInstance().getSheetImage("JET", color,0), 
				(int)(attach.getPos().x+offsetx), (int)(attach.getPos().y+offsety),
					Game.effects, scale);
		this.addAbility(new JetEffect(AbilTypes.JET_ABIL, attach, offsetx, offsety));
		this.spawn();
		if(color > 0 ) {
			this.setTexture(getTexture().getFlippedCopy(false, true));
		}
		this.rotate(attach.getRoatation() - this.getRoatation());
	}



}
