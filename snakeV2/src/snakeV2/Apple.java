package snakeV2;

import java.util.Random;

public class Apple {
	
	Random random;
	static int appleX;
	static int appleY;
	
	Apple(int coordinate) {	
		random = new Random();
		
		appleX = random.nextInt(0, coordinate);
		appleY = random.nextInt(0, coordinate);
		
	}
	
	

}
