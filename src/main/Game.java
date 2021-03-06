package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -381014417800033391L;
	
	public static final int WIDTH=640*2, HEIGHT = WIDTH/12*9;
	
	private Handler handler;
	private Random r;

	private Thread thread;
	private boolean running = false;
	private HUD hud;
	private Spawn spawner;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT,"THE GAME",this);
		hud= new HUD();
		spawner = new Spawn(handler,hud);
		
		r = new Random();

		for(int i = 0; i< 0;i++) {
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.BasicEnemy,Color.RED));

		}

		handler.addObject(new Player(100,100,ID.Player, handler));
		handler.addObject(new BasicEnemy(100,100,ID.BasicEnemy,Color.GREEN));
		handler.addObject(new BasicEnemy(100,40,ID.BasicEnemy,Color.RED));
		handler.addObject(new LinearEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.SmartEnemy,Color.GRAY,handler));

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running=true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running=false;
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks=60.0;
		double ns= 1000000000 / amountOfTicks;
		double delta=0;
		long timer= System.currentTimeMillis();
		int frames=0;
		while(running){
			long now= System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime=now;
			while(delta>=1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis()- timer > 1000) {
				timer+= 1000;
				System.out.println("FPS: " + frames);
				frames=0;
			}
		}
		stop();
		
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		spawner.tick();
		
		//handler.addObject(new Player(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.Player));
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g);
		g.dispose();
		bs.show();
			
	}
	public static int clamp(int var,int min, int max) {
		if(var>=max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
	public static int clamp(float var,int min, int max) {
		if(var>=max)
			return (int) (var = max);
		else if (var <= min)
			return (int) (var = min);
		else
			return (int) var;
	}
	
	
	public static void main(String[] args) {
		new Game();
		
	}
}
