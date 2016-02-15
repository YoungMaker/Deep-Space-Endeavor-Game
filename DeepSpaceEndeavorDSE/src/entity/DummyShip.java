package entity;

import main.Game;
import main.ResourceFactory;
import mech.Layer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import abilities.AbilTypes;
import abilities.interpolate;


public class DummyShip extends Entity {

	public DummyShip(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 0,0), x, y, Game.effects, 3.0f);
		this.addAbility((new interpolate(AbilTypes.INTERPOLATE, 0.08f, true, 0)));
		this.spawn();
	}


}
