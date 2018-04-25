package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class LinearEnemy extends GameObject{
	Random r = new Random();
	
	//private Handler handler;
	private GameObject player;
	private Trail trail;
	
	private Color color;
	
	LinearEnemy(int x, int y, ID id, Color color,Handler handler) {
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
		
		if (player.getX()-x<32&&player.getX()-x>-32)
			if(player.getY()>y)
				velY=9;
			else
				velY=-9;
		else
			velY=0;
		if (player.getY()-y<32&&player.getY()-y>-32)
			if(player.getX()>x)
				velX=9;
			else
				velX=-9;
		else
			velX=0;
		
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
