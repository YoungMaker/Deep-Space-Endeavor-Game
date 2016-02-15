package entity;

import main.Game;
import main.ResourceFactory;
import mech.Layer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import abilities.AbilTypes;
import abilities.interpolate;

public class EndGameObject extends Entity {

	public EndGameObject(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getImage("END"), x, y, Game.background, 12.5f);
		// TODO Auto-generated constructor stub
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, 0.015f, false));
		this.spawn();

	}

}
