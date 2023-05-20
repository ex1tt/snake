package snakeV3;

import java.util.Random;

public class Apple {
	
	Random random;
	static int appleX;
	static int appleY;
	
	Apple(int coordinateX, int coordinateY) {	
		random = new Random();
		
		appleX = random.nextInt(0, coordinateX);
		appleY = random.nextInt(0, coordinateY);
		
	}
	
	

}
