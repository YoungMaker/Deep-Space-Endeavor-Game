package entity;


import level.LevelLoader;
import main.Game;
import main.ResourceFactory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import abilities.AbilTypes;
import abilities.interpolate;

public class background extends Entity {

	public background(String id, int imgY) {
		 super(id, ResourceFactory.getInstance().getSheetImage("BACKGROUND", 0, imgY), 0, -10, Game.background, 7.84f);
			this.addAbility(new interpolate(AbilTypes.INTERPOLATE, new Float(LevelLoader.getInstance().getLevelProperty("backspeed")), false));
		this.spawn();
	}
	

}
