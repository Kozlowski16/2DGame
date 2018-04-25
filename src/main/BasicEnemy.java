package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject{
	Random r = new Random();

	private Color color;
	private Trail trail;
	
	BasicEnemy(int x, int y, ID id, Color color) {
		super(x, y, id);

		this.color=color;
		velX = 10;
		velY = 10;
		
		do {
		velX=r.nextInt(21)-10;
		velY=r.nextInt(21)-10;
		}while(velX==0 && velY==0);
		
		trail= new Trail(x,y,24,24,10);
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		
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
