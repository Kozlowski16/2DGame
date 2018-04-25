package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class SmartEnemy extends GameObject{
	Random r = new Random();
	
	//private Handler handler;
	private GameObject player;
	private Trail trail;
	
	private Color color;
	
	SmartEnemy(int x, int y, ID id, Color color,Handler handler) {
		super(x, y, id);
		//this.handler=handler;
		this.color=color;

		for(int i = 0;i<handler.object.size();i++) {
			if(handler.object.get(i).getId()==ID.Player){ 
				player=handler.object.get(i);
				break;
				}
		}
		trail= new Trail(x,y,24,24,10);
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		
		float diffX =Math.round(x - player.getX()-4);
		
		float diffY = Math.round(y - player.getY()-4);
		
		float distance= (float) Math.sqrt(Math.pow(x-player.getX(),2) + Math.pow((y-player.getY()),2));
		
		velX =  (-1.0f/distance)*diffX*4;
		velY = (-1.0f/distance)*diffY*4;
		if(y<=0 || y>=Game.HEIGHT - 48)
			velY*=-1;
		if(x<=0 || x>=Game.WIDTH - 24)
			velX*=-1;
		
		trail.tick(x, y);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x,(int)y,24,24);
		
		trail.render(g);
	}


	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,24,24);
	}

}
