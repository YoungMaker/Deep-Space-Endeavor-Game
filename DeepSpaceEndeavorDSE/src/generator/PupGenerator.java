package generator;

import entity.Entity;
import entity.powerUp;
import main.Game;
import main.ResourceFactory;
import mech.GameSys;

public class PupGenerator {
	
	public static void spawnPup(int x, int y, int type) {
		switch(type) {
		case 0:
			new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 1,0), x,y, type); //health
		break;
		case 1:
				new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 2,0), x,y, type);//speed
		break;
		case 2:
		//	System.out.println("ARMOR");
			if(!Game.getPlayer().isArmored()) {
				new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 0,2), x,y, type); //armor
			}
			else {
				new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 0,0), x,y, 6);//blank
			}
		break;
		case 3:
			//System.out.println("PupGenerator: hyper cannon");
			if(!Game.getPlayer().isHyperCannon()) { //TODO: blank pups are fucked up
				new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 0,1), x,y, type); //hyper cannon
			}
			else  {
				new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 0,0), x,y, 6);//blank
			}
		break;
		case 4:
			//System.out.println("PupGenerator: hyper cannon");
			new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 1,1), x,y, type); //heal
		break;
		case 5:
			//System.out.println("PupGenerator: invincible ");
			if(!Game.getPlayer().isInvincible()){
				new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 2,1), x,y, type); //invincible
		    }
			else {	
				new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 0,0), x,y, 6);//blank
			}
		break;
		}
	}
	
	public static void changePup(int type, Entity pup) {
		switch(type) {
		case 0:
		//	System.out.println("HEALTH");
			pup.setTexture(ResourceFactory.getInstance().getSheetImage("PUPS", 1,0));
			((powerUp)pup).setType(type);
		break;
		case 1:
		//	System.out.println("SPEED");
			pup.setTexture(ResourceFactory.getInstance().getSheetImage("PUPS", 2,0));
			((powerUp)pup).setType(type);
		break;
		case 2:
		//	System.out.println("ARMOR");
			pup.setTexture(ResourceFactory.getInstance().getSheetImage("PUPS", 0,2));
			((powerUp)pup).setType(type);
		break;
		case 3:
	//		System.out.println("HYPER CANNON");
			pup.setTexture(ResourceFactory.getInstance().getSheetImage("PUPS", 0,1));
			((powerUp)pup).setType(type);
		break;
		case 4:
		//	System.out.println("BUDDY");
			pup.setTexture(ResourceFactory.getInstance().getSheetImage("PUPS", 1,1));
			((powerUp)pup).setType(type);
		break;
		case 5:
	//		System.out.println("INVINCIBLE");
			pup.setTexture(ResourceFactory.getInstance().getSheetImage("PUPS", 2,1));
			((powerUp)pup).setType(type);
		break;
		}
	}
	
}
