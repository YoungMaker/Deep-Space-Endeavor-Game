package entity;

import org.newdawn.slick.SpriteSheet;
import main.Game;

public class Explosion extends AnimatedEntity {

	public Explosion(String id, int x, int y, SpriteSheet image, float scale) {
		super(id, image, x, y, Game.effects, scale, 75, 8);
	}

}