package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		keyDown[0]=false;	// w
		keyDown[1]=false;	// s
		keyDown[2]=false;	// d
		keyDown[3]=false;	// a
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ID.Player) {
				   if(key == KeyEvent.VK_W) tempObject.setVelY(tempObject.getVelY()-10);
				    if(key == KeyEvent.VK_A) tempObject.setVelX(tempObject.getVelX()-10);
				    if(key == KeyEvent.VK_S) tempObject.setVelY(tempObject.getVelY()+10);
				    if(key == KeyEvent.VK_D) tempObject.setVelX(tempObject.getVelX()+10);
				    tempObject.setVelY(Game.clamp(tempObject.getVelY(),-10,10));
				    tempObject.setVelX(Game.clamp(tempObject.getVelX(),-10,10));
			}
		}
		if(key== KeyEvent.VK_ESCAPE)
			System.exit(1);
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ID.Player) {
			    if(key == KeyEvent.VK_W) tempObject.setVelY(tempObject.getVelY()+10);
			    if(key == KeyEvent.VK_A) tempObject.setVelX(tempObject.getVelX()+10);
			    if(key == KeyEvent.VK_S) tempObject.setVelY(tempObject.getVelY()-10);
			    if(key == KeyEvent.VK_D) tempObject.setVelX(tempObject.getVelX()-10);
			    tempObject.setVelY(Game.clamp(tempObject.getVelY(),-10,10));
			    tempObject.setVelX(Game.clamp(tempObject.getVelX(),-10,10));
			}
		}
		
	}

}
