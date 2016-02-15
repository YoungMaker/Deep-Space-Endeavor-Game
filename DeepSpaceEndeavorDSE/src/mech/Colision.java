package mech;

import org.newdawn.slick.geom.Vector2f;

import entity.Entity;

public class Colision {
	public static boolean colideBoxes(Entity a, Entity b) {
		Vector2f posA = a.getPos();
		Vector2f posB = b.getPos();
		if((posA.x+a.getWidth())<posB.x) 
			return false;
		if((posA.y+a.getHeight())<posB.y)
			return false;
		if(posA.x > (posB.x+b.getWidth()))
			return false;
		if(posA.y > (posB.y+b.getHeight()))
			return false;
	return true;
	}
	
	public static int getDirection(Entity a, Entity b) {
		Vector2f posA = a.getPos();
		Vector2f posB = b.getPos();
		if((posA.x+a.getWidth())>posB.x) 
			return 1;
		if((posA.y+a.getHeight())>posB.y)
			return 2;
		if(posA.x < (posB.x+b.getWidth()))
			return 3;
		if(posA.y > (posB.y+b.getHeight()))
			return 4;
	return 0;
	}
}
