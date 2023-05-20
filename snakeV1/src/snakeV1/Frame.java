package snakeV1;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	Frame() {
		this.setTitle("Snake");
		this.setSize(new Dimension(700, 700));
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.black);
		
		this.add(new Panel());
		
		this.setVisible(true);
	}

}
