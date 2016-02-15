package abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import entity.*;

public abstract class Ability { //an abstract class to be given to separate components of entities
	protected Entity owner; //owner of the Ability
	 AbilTypes id = null; //id, an enum
	 
	 public Ability(AbilTypes id) {
		 this.id = id; //create the object with a specific ID
	 }
	 
	 public void setOwner(Entity own) {
		 this.owner = own; //set the owner
	}
	 
	 public abstract void update(GameContainer gc, StateBasedGame sb, int delta); //update method, called every game tick (~20 millis)

	public AbilTypes getId() {
		return id; //return the ID of the specific ability
	}

	public Entity getOwner() {
		return owner;
		
	}
}
