package entity;

import java.util.ArrayList;
import abilities.*;

import main.Game;
import main.GameStates;
import main.ResourceFactory;
import mech.GameSys;
import mech.Layer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Entity {
    String id;
	Vector2f pos;
	private float scale;
	protected Layer layer = null;
	private float angle = 0;
	
	ArrayList<Ability> ablitites = null;
	protected Image sprite = null;
	private boolean isTarget = false;
	protected Animation motion = null;
	private Color color = new Color(255,255,255,255);


	public Entity(String id, SpriteSheet frames, int x, int y, Layer layer, float scale, int duration, int amtFrames) {
		this.id = id;
		this.layer = layer;
		this.pos = new Vector2f(x,y);
		this.scale = scale;
		this.motion = new Animation(frames, duration);
		this.motion.setAutoUpdate(false);
		this.motion.stopAt(amtFrames);
		this.ablitites = new ArrayList<Ability>();
	}
	
	public Entity(String id, Image sprite, int x, int y, Layer layer, float scale) {
		this.id = id;
		this.layer = layer;
		this.pos = new Vector2f(x,y);
		this.scale = scale;
		this.sprite = sprite;
		this.ablitites = new ArrayList<Ability>();
	}
	
	public Entity(String id, Image sprite, int x, int y, Layer layer, float scale, Color filter) {
		this.id = id;
		this.layer = layer;
		this.pos = new Vector2f(x,y);
		this.scale = scale;
		this.sprite = sprite;
		this.ablitites = new ArrayList<Ability>();
		this.color = filter;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setPos(Vector2f set) {
		this.pos = set;
	}
	
	public Image getTexture() {
		return this.sprite;
	}
	
	 public void getSubImage( int x, int y, int w, int h) {
		 this.sprite = this.sprite.getSubImage(x, y, w, h);
	 }
	 
	 public int getWidth() {
		 if(sprite != null) {
			 return (int) (sprite.getWidth()* this.getScale());
		 }
		 else  {
			 return (int) (motion.getWidth()*this.getScale());
		 }
	 }
	 
	 public int getHeight() {
		 if(sprite != null) {
		 return (int) (sprite.getHeight()*this.getScale());
		 }
		 else {
			 return (int) (motion.getHeight()*this.getScale());
		 }
	 }
	 
	public Color getColor() {
			return color;
		}

	public void setColor(Color color) {
			this.color = color;
		}

	public void rotateTo(float angle) {
		this.angle = angle;
	}
	
	public void rotate(float angle) {
		this.angle = angle;
		sprite.rotate(angle);
	}
	
	public void setAsTarget(boolean swich) {
	if(swich) {
		if(!isTarget) {
			isTarget = true;
		}
	}
	else {
		isTarget = false;
	}
	}
	
	public boolean isTarget() {
		return this.isTarget;
	}

	public Vector2f getCenterOfRotation() {
		return new Vector2f(sprite.getCenterOfRotationX()*scale, sprite.getCenterOfRotationY()*scale);
	}
	
	public void setCenterOfRotation(Vector2f loc) {
		sprite.setCenterOfRotation(loc.x, loc.y);
	}
	
	public Vector2f getCenter() {
		return new Vector2f(this.getPos().x+(this.getWidth()/2), this.getPos().y+(this.getHeight()/2));
	}

	public float getRoatation() {
		return sprite.getRotation();
	}

	public void setTexture(Image texture) {
		if(sprite != null) {
			this.sprite = texture;
		}
	}
	
	public Vector2f getPos() {
		return this.pos;
	}
	
	public float getScale() {
		return this.scale;
	}
	
	public void setScale(float scales) {
		this.scale = scales;
	}
	
	public void addAbility(Ability abil) {
		abil.setOwner(this);
		ablitites.add(abil);
	}
	
	public Ability getAbility(AbilTypes id) {
		for(Ability abil : ablitites) {
			if(abil.getId().equals(id)) {
				return abil;
			}
		}
		return null;
	}

	public void spawn() {
		layer.add(this);
	}
	
	public void delete()  {
		if(this instanceof Enemy) {
			new Explosion("Ex-" + GameSys.random.nextInt(), (int)this.getPos().x, (int)this.getPos().y, ResourceFactory.getInstance().getSheet("EEXPLOSION"), 3.5f);
		}
		else if(this instanceof Iship) { //, SpriteSheet image
			new Explosion("Ex-" + GameSys.random.nextInt(), (int)this.getPos().y, (int)this.getPos().x, ResourceFactory.getInstance().getSheet("EXPLOSION"), 3.5f);
		}
		if(isTarget) {
			isTarget = false;
		}
		layer.remove(this);
		//System.out.println("Entity: " + id + " was deleted");
		try {this.finalize();} catch (Throwable e) {e.printStackTrace();}
	}
	
	public String toString() {
     if(sprite != null) {
    	 	return "Entity: " + id + " @ (" + this.getPos().x + "," + this.getPos().y + ")" + " img: " + sprite; 
		}
     else if(motion != null) {
    	 String output =
    		 "Animated Entity: " + id + " @ (" + this.getPos().x + "," + this.getPos().y + ")" + " animation:" + motion + " frames: " + '\n';
    	 	for(int i=0; i<motion.getFrameCount(); i++) {
    	 		output += "		" + "frame img: " + motion.getImage(i) + '\n';
    	 	}
    	 	return output;
     }
     else {
    	 return "Entity: " + id + " @ (" + this.getPos().x + "," + this.getPos().y + ")";
     }
	}
	
	 public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		 this.sprite.draw(this.getPos().x, this.getPos().y, this.scale, color);

		 if(Game.isDebug()) {
			 if(this instanceof Enemy) {
		     		 gr.setColor(new Color(255,0,0));
		     }
		     if(Game.getPlayer() != null && this instanceof Enemy) {
		    	 Vector2f plrPos = Game.getPlayer().getPos();
		    	 gr.drawLine(this.getPos().x+this.getCenterOfRotation().x, this.getPos().y+this.getCenterOfRotation().y, 
		    			plrPos.x + Game.getPlayer().getCenterOfRotation().x, plrPos.y+ Game.getPlayer().getCenterOfRotation().y);
			 	}
		     	gr.drawRect(this.getPos().x, this.getPos().y, this.getWidth(), this.getHeight());
		     	gr.setColor(new Color(255,255,255));
			 }
		 }

	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for(Ability abil : ablitites) {
			abil.update(gc, sb, delta);
		}
	}
}
