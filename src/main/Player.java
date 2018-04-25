package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	Random r = new Random();
	Handler handler;

	Player(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler= handler;
		/*
		do {
		velX=r.nextInt(15)-7;
		velY=r.nextInt(15)-7;
		}while(velX==0 && velY==0);
		*/
	}
	private void collision() {
		for(int i = 0; i< handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getId()==ID.BasicEnemy||tempObject.getId()==ID.SmartEnemy||tempObject.getId()==ID.LinearEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=1;
				}
			}	
		}
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		x = Game.clamp(x, 0, Game.WIDTH-32);
		y = Game.clamp(y, 0, Game.HEIGHT-62);
		collision();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x,(int) y, 32, 32);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

}
