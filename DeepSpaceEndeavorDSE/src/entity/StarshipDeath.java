package entity;

import main.Game;
import main.ResourceFactory;

public class StarshipDeath extends AnimatedEntity {

	public StarshipDeath(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheet("MOTHERSHIP_DEATH"), x, y, Game.effects, 7.0f, 150, 14);
	}

}
