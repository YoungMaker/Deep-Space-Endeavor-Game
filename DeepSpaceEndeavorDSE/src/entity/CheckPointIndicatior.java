package entity;

import abilities.AbilTypes;
import abilities.interpolate;
import main.Game;
import main.ResourceFactory;


public class CheckPointIndicatior extends AnimatedEntity{

	public CheckPointIndicatior(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheet("CPOINT"), x, y, Game.effects, 1.0f, 300, 6);
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, 0.086f, false));
	}

}
