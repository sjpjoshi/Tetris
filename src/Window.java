import javax.swing.JFrame;
import java.awt.event.*;  
import javax.swing.JButton;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Window {

	// we dont want to change these 
	public int WIDTH = 500, HEIGHT = 640;
	private board Board; 
	// change this to public if ya want
	private JFrame window; 
	private JButton r;
	
	public Window(int d) {
		
		if(d == 4) {
			
			WIDTH = 640;
			HEIGHT = 789;
			
		} else if(d == 5) {
			
			WIDTH = 800;
			HEIGHT = 939;
			
		} // else if
		
		window = new JFrame("Tetris"); // sets the name of the game 
		window.setSize(WIDTH, HEIGHT); // sets the size of the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// when the X button is clicked it closes 	
		window.setLocationRelativeTo(null);
        window.setResizable(false);
		
		Board = new board(d); // making a i
		window.add(r);
		window.add(Board);
		window.addKeyListener(Board);
		window.setVisible(true);
		
	} // Window
	
	public void Tet() {
		
		window.addMouseMotionListener(Board);
		window.addMouseListener(Board);
		window.add(Board);
        Board.startGame();	
        window.revalidate();
        
	} // Tet
	
	public static void main(String[] args) {
		
		Menu menu = new Menu();
		
	} // main

	
} // Class
