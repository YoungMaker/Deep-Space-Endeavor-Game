package entity;

import level.LevelLoader;
import main.Game;
import mech.Layer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import abilities.AbilTypes;
import abilities.PushAbil;
import abilities.interpolate;

public abstract class StationPart extends Entity {

public static float PART_SCALE= 5.68f;

	public StationPart(String id, Image sprite, int x, int y) {
		super(id, sprite, x, y, Game.background, PART_SCALE);
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, Float.parseFloat(LevelLoader.getInstance().getLevelProperty("backspeed")), false ));
		this.addAbility(new PushAbil(AbilTypes.INTERPOLATE, 0.5f ) ) ;
	}
	
	public StationPart(String id, Image sprite, int x, int y, float scale) {
		super(id, sprite, x, y, Game.background, scale);
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, Float.parseFloat(LevelLoader.getInstance().getLevelProperty("backspeed")), false ));
	}
	
	public abstract void action();

}
