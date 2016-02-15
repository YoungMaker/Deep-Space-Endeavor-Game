package entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import abilities.AbilTypes;
import abilities.interpolate;
import main.Game;
import main.ResourceFactory;


public class LevelEndedIndicator extends AnimatedEntity {

	public LevelEndedIndicator(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheet("LEVEND"), x, y, Game.effects, 3.0f, 500, 7);
		this.addAbility(new interpolate(AbilTypes.INTERPOLATE, 0.075f, false));
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		super.render(gc, sb, gr);
		gr.setColor(new Color(160,160,160,255));
		gr.drawString("Final Score: " + Game.getScore(), this.getPos().x+25, this.getPos().y-10);
		gr.setColor(new Color(255,255,255,255));
	}
}
