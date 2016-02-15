package entity;

import main.ResourceFactory;
import mech.Layer;

import abilities.AbilTypes;
import abilities.GunFireAbil;

public class GunFire extends AnimatedEntity {

	public GunFire(String id, int x, int y, Layer layer, Entity owner, int[] points, boolean which) {
		super(id, ResourceFactory.getInstance().getSheet("GUN_FIRE"), x, y, layer, 2.0f, 110, 2);
		this.addAbility(new GunFireAbil(AbilTypes.GUNFIRE_ABIL, owner, points, which));
	}

}
