package abilities.util;


import entity.Entity;

public class interpolation {
	
	public static void interpolate(Entity owner, float rotation, boolean dir, float speed, int delta) {
		float hip = speed*delta;
		if(!dir) {
	        owner.getPos().x -= hip * Math.sin(Math.toRadians(rotation));
	        owner.getPos().y += hip * Math.cos(Math.toRadians(rotation));
			}
			else {
				owner.getPos().x += hip * Math.sin(Math.toRadians(rotation));
		        owner.getPos().y -= hip * Math.cos(Math.toRadians(rotation));
			}
	}
	
	public static void interpolateLR(Entity owner, float rotation, boolean dir, float speed, int delta) {
		float hip = speed*delta;
		if(!dir) {
	        owner.getPos().x -= hip * Math.sin(Math.toRadians(rotation));
	        owner.getPos().y += hip * Math.cos(Math.toRadians(rotation));
			}
			else {
				owner.getPos().x += hip * Math.sin(Math.toRadians(rotation));
		        owner.getPos().y -= hip * Math.cos(Math.toRadians(rotation));
			}
	}
	
}
