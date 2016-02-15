package abilities;

import java.util.ArrayList;
import java.util.Random;

import main.Game;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import entity.Boss;
import entity.Bullet;
import entity.Entity;
import entity.Mine;

public class TargetAbil extends Ability {
Entity target;

	public TargetAbil(AbilTypes id) {
		super(id);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		ArrayList<Entity> targets  = Game.enemies.entities;
		if(targets.size() > 0) {
			if(target == null || target.isTarget() == false) {
			 if(targets.get(targets.size()-1) instanceof Bullet || targets.get(targets.size()-1) instanceof Mine) {	owner.setPos(new Vector2f(-40,-40)); }
			 else {
			 	this.target = targets.get(targets.size()-1);
				target.setAsTarget(true);
				//owner.rotate(target.getRoatation());
			 }
			}
		}
		else {
			owner.setPos(new Vector2f(-40,-40));
		}
		if(target != null) {
			if(target instanceof Boss)
			owner.setPos(target.getCenter());
			else 
				owner.setPos(target.getPos());
		}
		else {
			owner.setPos(new Vector2f(-40,-40));
		}
	}
	}
