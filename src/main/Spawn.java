package main;

import java.awt.Color;
import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep = 0;
	
	public Spawn(Handler handler,HUD hud) {
		this.handler = handler;
		this.hud= hud;
		
	}
	
	public void tick() {
		scoreKeep++;
		if(scoreKeep>=100){
			scoreKeep=0;
			hud.setLevel(hud.getLevel()+1);
			if(hud.getLevel()%4==0)
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.SmartEnemy,Color.YELLOW,handler));
			else if(hud.getLevel()%2==0)
				handler.addObject(new LinearEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.SmartEnemy,Color.GRAY,handler));
			else
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,Color.RED));
		}
		
		
	}

}
