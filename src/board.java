import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.Font;
import javax.swing.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class board extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L; // ignore this 
	public  int BOARD_W = 10, BOARD_H = 20;
	private Color[][] board;
	public final int BLOCK_S = 30;
	private int fps = 60;
	private int delay = 1000/fps;
	private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"),  Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};
	private Shape[] shapes = new Shape[7];
	private Shape current;
	private Random random;
	private int difficulty;
	public int score = 0;
	private boolean gameOver = false;
	private Timer looper;
	public int timeCount = 0;
	
	// constructor 
	public board(int d) {
	
		difficulty = d;
		
	 	if(difficulty == 4)  {
		 
	 		BOARD_W = 15;
	 		BOARD_H = 25;
		 
	  } else if(difficulty == 5)  {
	 		
		  	BOARD_W = 20;
		  	BOARD_H = 30;
		 
	  } // else if
	 
	 	board = new Color[BOARD_H][BOARD_W];
	 	random = new Random();
	    JButton button = new JButton("Refresh");
	    
	 	 
	 	looper = new Timer(delay, new GameLooper());

       // create shapes
	 	shapes[0] = new Shape(new int[][]{  {1, 1, 1, 1}  }, this, colors[0], d); // I
	 	shapes[1] = new Shape(new int[][]{ {1, 1, 1}, {0, 1, 0}, }, this, colors[1], d); // T
	 	shapes[2] = new Shape(new int[][]{ {1, 1, 1},{1, 0, 0},  }, this, colors[2], d); // L 
	 	shapes[3] = new Shape(new int[][]{  {1, 1, 1}, {0, 0, 1},  }, this, colors[3], d); // J
     	shapes[4] = new Shape(new int[][]{  {0, 1, 1},  {1, 1, 0}, }, this, colors[4], d); // S
     	shapes[5] = new Shape(new int[][]{  {1, 1, 0}, {0, 1, 1},  }, this, colors[5], d); // Z
     	shapes[6] = new Shape(new int[][]{  {1, 1},  {1, 1}, }, this, colors[6], d); // O
     	current = shapes[0];
     		
	} // board
	
	public boolean getGameOver()
	{
		return gameOver;
	}
	private Timer lapse = new Timer(300, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			lapse.stop();
			
		} // actionPeformed
		
	}); // Timer
	
	// updater 
	private void update() {
		
		//if(refresh)
		//	startGame();
		
		if(gameOver)
		{
			
			return;
		}
			
		current.update();
		current.timer();
		
	} // update
		
	// painter
	public void paintComponent(Graphics G) { 
		// let i = row
		// let j = column 
		
		super.paintComponent(G); // this is for making the blocks
		G.setColor(Color.black);
		G.fillRect(0, 0, getWidth(), getHeight() + 200);
			
		// so the letters can stack on each other
		for(int i = 0; i < board.length; i++) {
				
			for(int j = 0; j < board[i].length; j++) {
					
				if(board[i][j] != null) {
						
					G.setColor(board[i][j]);
					G.fillRect(j * BLOCK_S, i * BLOCK_S, BLOCK_S, BLOCK_S);
						
				} // if
					
			} // for
				
		} // for
	
		current.rendering(G);
			
		// for displaying gameover text
		if (gameOver) {
			 
	            String gameOverString = "GAME OVER";
	            G.setColor(Color.WHITE);
	            G.setFont(new Font("Georgia", Font.BOLD, 30));
	            G.drawString(gameOverString, getWidth()/2, getHeight()/2 );
	            score = 0;
	            
	    } // if
		
		G.setColor(Color.white);
		// sets the font for the score
		G.setFont(new Font("Georgia", Font.BOLD, 20));
		
		// drawing the score
        G.drawString("SCORE", getWidth() - 150 , BOARD_H  + 200);
        G.drawString(score + "", getWidth() - 60 , BOARD_H + 200);
		
        G.drawString("Time", getWidth() - 150, BOARD_H + 300);
        G.drawString(timeCount + "", getWidth() - 90, BOARD_H + 300);
        
		// making the board
		G.setColor(Color.white);
			
		for(int i = 0; i < BOARD_H; i++) // makes the rows
			G.drawLine(0, BLOCK_S * i, BLOCK_S * BOARD_W, BLOCK_S * i); // basically draws a line every 30 spaces or whatever the unit of mesasurement is in this ternimal 
				
		for(int j = 0; j < BOARD_W + 1; j++) // makes the cols 
			G.drawLine(j * BLOCK_S, 0, j * BLOCK_S, BLOCK_S * BOARD_H); // same thing tho for columns 
				
	} // paint 

	//setters
	public void setCurrent() {
		
		current = shapes[random.nextInt(shapes.length)];
		current.reset();
		
		for (int row = 0; row < current.getCoords().length; row++) {
			
            for (int col = 0; col < current.getCoords()[0].length; col++) {
            	
                if (current.getCoords()[row][col] != 0) {
                	
                    if (board[current.getY() + row][current.getX() + col] != null) 
                        gameOver = true;
                    
                } // if
                
            } // for
            
        } // for
 			
	} // setCurrent 

	// input check 
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			current.speed();
		
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			current.moveRight();
			
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			current.moveLeft();
		
		else if(e.getKeyCode() == KeyEvent.VK_UP)
			current.rotate();
		
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
			current.instant();
		
		else if(e.getKeyChar() == 'a')
			startGame();
		
	} // keyPressed

	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			current.slow();
		
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
		    current.slow();
		
	} // keyReleased

	public void addScore() {
		
		score++;
		
	} // addScore
	
	public void addTime() {
		
		timeCount++;
		
	} // addTime

	//getters
	public Color[][] getBoard() {
		
		return board;
		
	} // getBoard

	public void startGame() {
		  
	     stopGame();
	     setCurrent();
	     gameOver = false;
	     looper.start();

	  } // startGame

	public void stopGame() {
		
		score = 0;
		timeCount = 0;

	      for (int row = 0; row < board.length; row++) {
	    	  
	            for (int col = 0; col < board[row].length; col++) 
	                board[row][col] = null;
	            
	        } // for 
	      
	        looper.stop();
	        
	    } // StopGame


    class GameLooper implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	
            update();
            repaint();
            
        } // actionPerformed

    } // ActionListener
	
    @Override
    public void mouseDragged(MouseEvent e) {
    	
              
    } // mouseDragged

    @Override
    public void mouseMoved(MouseEvent e) {
    	
       
        
    } // mouseMoved
    
    @Override
    public void mousePressed(MouseEvent e) {
    	
        //if (e.getButton() == MouseEvent.BUTTON1) 
           
        
    } // mousePressed

    @Override
    public void mouseReleased(MouseEvent e) {
    	
       // if (e.getButton() == MouseEvent.BUTTON1) 
            
        
    } // mouseReleased
	
    
    
    
	// we dont need this tho if you remove it there will be a error 
	public void keyTyped(KeyEvent e) {
	
		
	} // KeyTyped

	 @Override
	public void mouseClicked(MouseEvent e) {

	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
} // class 
