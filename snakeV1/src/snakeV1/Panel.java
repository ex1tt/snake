package snakeV1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	private final int PANEL_WIDTH = 500;
	private final int PANEL_HEIGHT = 500;
	private final int UNIT_SIZE = 25;
	private final int GAME_FPS = 13;
	private int snakeLength;
	private int velocityX;
	private int velocityY;
	private int appleX;
	private int appleY;
	private int score;
	private boolean upPressed, downPressed, leftPressed, rightPressed;
	private Thread gameThread;
	private int[] snakeXCoords;
	private int[] snakeYCoords;
	private Random random;

	Panel() {
		
		this.setBounds(80, 60, PANEL_WIDTH, PANEL_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(this);
		
		startGame();
		
	}
	
	private void generateApple() {
		
		random = new Random();
		
		appleX = random.nextInt(0, PANEL_WIDTH/UNIT_SIZE);
		appleY = random.nextInt(0, PANEL_HEIGHT/UNIT_SIZE);
		
	}
	
	private void eatApple() {
	
		score +=1;
		snakeLength +=1;
		generateApple();
		
		System.out.println("Score: " + score);
		
	}
	@SuppressWarnings("deprecation")
	private void gameOver() {
		
		System.out.println("Game OVER");
		gameThread.stop();
		
	}
	
	@Override
		public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(Color.black);
		     
		for(int i=1; i<PANEL_WIDTH / UNIT_SIZE; i++) {
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, 500);
			g.drawLine(0, i*UNIT_SIZE, 500, i*UNIT_SIZE);
		}
		
		g.setColor(Color.green);
		g.fillOval(appleX*UNIT_SIZE, appleY*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
		
		g.setColor(Color.red);
		
		for(int i=0; i<snakeLength; i++) {
			g.fillRect(snakeXCoords[i]*UNIT_SIZE, snakeYCoords[i]*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void startGame() {
		
		if(gameThread != null) {
			gameThread.stop();
		}
		
		gameThread = new Thread(this);
		gameThread.start();
		
		snakeXCoords = new int[400];
		snakeYCoords = new int[400];
		
		snakeLength = 1;
		velocityX = 1;
		velocityY = 0;
		score = 0;
		
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		
		generateApple();
		
	}
	
	public void update() {
		
		if(snakeXCoords[0] < 0 || snakeYCoords[0] < 0) {
			gameOver();
		}
		if(snakeXCoords[0]*UNIT_SIZE > PANEL_WIDTH-UNIT_SIZE || snakeYCoords[0]*UNIT_SIZE > PANEL_HEIGHT-UNIT_SIZE) {
			gameOver();
		}
		if(snakeXCoords[0] == appleX && snakeYCoords[0] == appleY) {
			eatApple();
		}
		
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
				
		
		for(int i=snakeLength; i>0; i--) {
			snakeXCoords[i] = snakeXCoords[i-1] ;
			snakeYCoords[i] = snakeYCoords[i-1];
		}

		snakeXCoords[0] += velocityX;
		snakeYCoords[0] += velocityY;
		
		for(int i=1; i<snakeLength; i++) {
			if(snakeXCoords[0] == snakeXCoords[i] && snakeYCoords[0] == snakeYCoords[i]) {
				gameOver();
			}
		}
		
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
		
		if(code == 82) {
			startGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
