package entity;

import abilities.*;
import main.Game;
import main.ResourceFactory;
import mech.Layer;

public class target extends Entity {

	public target(String id, int x, int y, Layer layer) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 2,1), x, y, Game.effects, 2.25f);
		this.addAbility(new TargetAbil(AbilTypes.TARGET_ABIL));
		this.spawn();
	}

}
