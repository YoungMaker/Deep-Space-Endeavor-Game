package main;

import mech.GameSys;
import mech.effect.EffectLayer;
import mech.effect.star;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
//TODO: Add some sort of death animation
public class DeathState extends BasicGameState {
protected int id =0;
Input in = null;

private Image header = null;
EffectLayer layer = new EffectLayer(0);

private int saturation = 255; //pulsation vars
private boolean satDown = true;

	public DeathState(int id) {
	 this.id = id;
}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
	}

	public void enter(GameContainer container,StateBasedGame game)
    throws SlickException {
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)
			throws SlickException {
		GameStates.resetFont(g);
		layer.render(gc, sb, g);
		if(header != null) {
			header.draw(220,40);
		}
		else {
			header = ResourceFactory.getInstance().getImage("DEATH_HEADER");
		}
		
		g.setColor(new Color(255,255,255,saturation));
		g.drawString("Press Space to Continue",313,520);
		g.setColor(Color.white);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)
			throws SlickException {
		// TODO update the death animation
		layer.update(gc, sb, delta);
		in = gc.getInput();
		if(in.isKeyDown(Input.KEY_SPACE)){
			GameStates.gamer.changeState(GameStates.GAME_PLAY_STATE, Color.black);
		}
		if(satDown) {
			if(saturation <= 80){
				satDown = false;
				fillRow();
			}
			saturation -= 2;
		}
		else if(!satDown){
		  if(saturation >= 255){
				satDown = true;
				fillRow();
			}
		  saturation += 2;
		}

	}
	
	private void fillRow() {
		for(int i=0; i< 20; i++) {
			new star(GameSys.random.nextInt(800), GameSys.random.nextInt(200), layer, 0.030f);
		}
	}
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
		throws SlickException {
		
	}
	
	@Override
	public int getID() {
		return id;
	}

}
