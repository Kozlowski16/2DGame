package main;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Trail {
	


	private int[][] oldPositions;
	private int height;
	private int width;

	Trail(float x,float y,int width,int height,int trialLength) {
		oldPositions = new int[trialLength][2];
		for(int[] pos: oldPositions) {
			pos[0] = (int) x;
			pos[1] = (int) y;
		}
		this.width=width;
		this.height=height;
	}


	public void tick(float x,float y) {
		oldPositions[0][0]=(int)(x+0.5f);
		oldPositions[0][1]=(int)(y+0.5f);
		for(int i = oldPositions.length-1;i>0;i--) {
			oldPositions[i][0]=oldPositions[i-1][0];
			oldPositions[i][1]=oldPositions[i-1][1];
		}
	}

	public void render(Graphics g) {
		
		Graphics2D g2d =(Graphics2D)g;
		float alpha =0.5f;

		for(int[] pos: oldPositions) {
			alpha=alpha-0.5f/(float)oldPositions.length;
			if (alpha>0)
				g2d.setComposite(makeTransparent(alpha));
			g.fillRect(pos[0],pos[1],width,height);
		}

		g2d.setComposite(makeTransparent(1));
		
	}
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
		
	}

}

/*package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trial extends GameObject{
	
	private float alpha = 1;
	private Handler handler;
	private Color color;
	private float life;

	Trial(int x, int y,float life, ID id,Color color, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		this.color=color;
		this.life=life;
	}

	@Override
	public void tick() {
		if(alpha>life) {
			alpha-=life-0.001f;
		}
		else
			handler.removeObject(this);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d =(Graphics2D)g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x, (int)y, 16, 16);
		
		g2d.setComposite(makeTransparent(1));

		
	}
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/
