package snakeV0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, KeyListener{
	
	private final int PANEL_WIDTH = 500;
	private final int PANEL_HEIGHT = 500;
	private final int UNIT_SIZE = 25;
	private final int GAME_FPS = 20;
	private int snakeCount = 6;
	private int velocityX = 1;
	private int velocityY = 0;
	private boolean upPressed, downPressed, leftPressed, rightPressed;
	private Thread gameThread;
	private int[] snakeXCoords;
	private int[] snakeYCoords;

	Panel() {
		
		this.setBounds(80, 60, PANEL_WIDTH, PANEL_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(this);
		
		snakeXCoords = new int[400];
		snakeYCoords = new int[400];
		
		startGame();
		
	}
	
	@Override
		public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(Color.black);
		     
		for(int i=1; i<PANEL_WIDTH / UNIT_SIZE; i++) {
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, 500);
			g.drawLine(0, i*UNIT_SIZE, 500, i*UNIT_SIZE);
		}
		g.setColor(Color.red);
		
		for(int i=snakeCount; i>0; i--) {
			g.fillRect(snakeXCoords[i]* UNIT_SIZE, snakeYCoords[i]*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
		}
		
	}
	
	public void startGame() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void update() {
		
		if(upPressed) {
			
			if(velocityY == 0) {
				velocityX = 0;
				velocityY = -1;
			}
			
		}
		if(downPressed) {
			
			if(velocityY == 0) {
				velocityX = 0;
				velocityY = 1;
			}
			
		}
		if(leftPressed) {
			
			if(velocityX ==0 ) {
				velocityX = -1;
				velocityY = 0;
			}
			
		}
		if(rightPressed) {
			
			if(velocityX == 0) {
				velocityX = 1;
				velocityY = 0;
			}
			
		}		
		
		for(int i=snakeCount; i>0; i--) {
			snakeXCoords[i] = snakeXCoords[i-1] ;
			snakeYCoords[i] = snakeYCoords[i-1];
		}

		snakeXCoords[0] += velocityX;
		snakeYCoords[0] += velocityY;
		
	}

	@Override
	public void run() {
		
		while(gameThread != null) {
			
			try {
				Thread.sleep(1000 / GAME_FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			update();
			repaint();
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == 37) {	// left
			
			upPressed = false;
			downPressed = false;
			leftPressed = true;
			rightPressed = false;
			
		}
		if(code == 38) {	// up
			
			upPressed = true;
			downPressed = false;
			leftPressed = false;
			rightPressed = false;
		}
		if(code == 39) {	// right
			
			upPressed = false;
			downPressed = false;
			leftPressed = false;
			rightPressed = true;
		}
		if(code == 40) {	// down
			
			upPressed = false;
			downPressed = true;
			leftPressed = false;
			rightPressed = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
