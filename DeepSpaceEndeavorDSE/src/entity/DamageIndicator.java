package entity;

import abilities.AbilTypes;
import abilities.DamageIndAbil;
import main.Game;
import main.ResourceFactory;

public class DamageIndicator extends AnimatedEntity{

	public DamageIndicator(String id, int x, int y, float scale, Entity hit) {
		super(id, ResourceFactory.getInstance().getSheet("DAMAGEIND"), x, y, Game.effects, scale, 5, 99);
		this.addAbility(new DamageIndAbil(AbilTypes.DAMIND, hit));
	}


}
