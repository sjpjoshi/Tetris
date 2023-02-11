import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

public class Shape {
	
	private int x = 4, y = 0;
	private int normalTime = 600;
	private int fastTime = 50;
	private double instant = 0.001;
	private long startTime;
	private double delayTimeForShapeMovement = normalTime;
	private int deltaX = 0; // we are gunna use this for moving the letter left or right
	private boolean col = false; // collision 
	private int[][] coords;
	public int BOARD_W = 10, BOARD_H = 20;
	public int BLOCK_S = 30;
	private board Board;
	private Color color;
	boolean moveX = true;
	private int difficulty;	
	
	// constructor
	public Shape(int[][] coords, board b, Color c, int d) {
		
		difficulty = d;
		//changes shape speed and size depending on difficulty
		switch(difficulty)
		{
			case 2:
			{
				normalTime = 500;
				delayTimeForShapeMovement = 500;
				break;
			}
			case 3:
			{
				normalTime = 400;
				delayTimeForShapeMovement = 400;
				break;
			}
			case 4:
			{
				normalTime = 200;
				delayTimeForShapeMovement = 200;
				BOARD_W = 15;
				BOARD_H = 25;
				break;
			}
			case 5:
			{
				normalTime = 100;
				delayTimeForShapeMovement = 100;
				BOARD_W = 20;
				BOARD_H = 30;
				break;
			}
		}
	
		this.coords = coords;
		this.Board = b;
		this.color = c;
		
	} // Shape
	
	
	// updating the shapes
	public void update() {
		
		//timer();
		if(col) {	
			
			// fill the shape colors 
			for(int i = 0; i < coords.length; i++) { // taking the rows that are less than the coordinates
				
				for(int j = 0; j < coords[0].length; j++) {	 // taking the columns that are at the 0 index of the coords 
					
					if(coords[i][j] != 0) // if both of them arent equal to zero
						Board.getBoard()[y + i][x + j] = color; // update their color
					
				} // for	
				
			} // for
			
			checkRow();
			Board.setCurrent(); //set the current shape 
			Board.addScore();	
			return;
			
		} // if
		
		
		// horizontal movement
		if( !(x + deltaX + coords[0].length > BOARD_W) && !(x + deltaX < 0) ) {
			
			for(int i = 0; i < coords.length; i++) {
				
				for(int j = 0; j < coords[i].length; j++) {
					
					if(coords[i][j] != 0) {
				
							if(Board.getBoard()[y + i][x + deltaX + j] != null) 								
								moveX = false;
	
					} // if
					
				} // for
				
			} // for
			
			if(moveX)
				x += deltaX; 	
			
		} // if
			
		deltaX = 0;
		
		// basically if the current time of the computer - the starting time is > 600 
		if(System.currentTimeMillis() - startTime > delayTimeForShapeMovement) {
			
			// vertical movement 
			if( !(y + 1 + coords.length > BOARD_H) ) {
				
				for(int i = 0; i <coords.length; i++) {
					
					for(int j = 0; j < coords[i].length; j++) {
						
						if(coords[i][j] != 0) {
							
							if(Board.getBoard()[y + 1+ i][x + deltaX + j] != null) 								
								col = true;
								
						} // if
						
					} // for
					
				} // for
				
				if(!col)
					y++; // change the y on the shape
				
			} else
				col = true; // setting  the collision to be true
			
			startTime = System.currentTimeMillis(); // set the start time to the current time the computer is at
			
			deltaX = 0;
		} // if
		
	} // update
	
	
	// making the shapes 
	public void rendering(Graphics G) {
	
		// making the shape
		for(int i = 0; i < coords.length; i++) {
			
			for(int j = 0; j < coords[0].length; j++) {
				
				if(coords[i][j] != 0) {
					
					G.setColor(color); 
					G.fillRect(j * BLOCK_S + x * BLOCK_S, i * BLOCK_S + y * BLOCK_S, BLOCK_S, BLOCK_S); // filing the letters
					
				} // if  
				
			} // for
			
		} // for
		
	} // rendering 
	
	public void rotate() {
		
		int[][] rotated = transpose(coords);
	    reverse(rotated);
		
	    // check right side and bottom
	    
	    if( (x + rotated[0].length > Board.BOARD_W) || (y + rotated.length > Board.BOARD_H) ) // we need to check if there will be a invalid rotation
	    	return;
	    
	    //check for collision with other shapes before rotating
	    
	    for(int i = 0; i < rotated.length; i++) {
	    	
	    	for(int j = 0; j < rotated[0].length; j++) {
	    
	    		if(rotated[i][j] != 0) {
	    			
	    			if(Board.getBoard()[y + i][x + j] != null)
	    				return;
	    				
	    		} // if
	    			
	    	} // for
	    	
	    } // for
	    
	    coords = rotated;
		
	} // rotate
	
	private int[][] transpose(int[][] matrix) {
	
		int[][] temp = new int[matrix[0].length][matrix.length];
		
		for(int i = 0; i < matrix.length; i++) {
			
			for(int j = 0; j < matrix[0].length; j++) {
			
				temp[j][i] = matrix[i][j]; // basically we need to "flip" the blocks 
				
			} // for
			
		} // fpr
		
		return temp;
		
	} // transpose
	
	private int[][] reverse(int[][] matrix) { 
		
		int middle = matrix.length/2;
		
		for(int i = 0; i < middle; i++) {
			
			// wee need to swap the row to make a correct clockwise rotation
			int[] temp = matrix[i];
			matrix[i] = matrix[matrix.length - i - 1];
			matrix[matrix.length - i - 1] = temp;
			
		} // for
		
		return matrix;
		
	} // reverse 
	
	private void checkRow() {
		
		//int rows = 0;
		int bottomRow = Board.getBoard().length - 1; // bottom of the matrix
		
		for(int topRow = Board.getBoard().length - 1; topRow > 0; topRow--) { // so we are gunna start from the top to the bottom 
			
			int temp = 0;
			
			for(int j = 0; j < Board.getBoard()[0].length; j++) {
				
				if(Board.getBoard()[topRow][j] != null) 
					temp++;
				
				//Board.addScore();
				Board.getBoard()[bottomRow][j] = Board.getBoard()[topRow][j];
			
			} // for
			
			if(temp < Board.getBoard()[0].length) {
				
				bottomRow--; // move the bottom line to the top one unit 
				
			}
				
		} // for 
		
	} // checkRow
	
	// setters
	public void setX(int x) {
		
		this.x = x;
		
	} // setX
	
	public void setY(int y) {
		
		this.y = y;
		
	} // setX
	
	
	// Movement 
	public void instant()
	{
		delayTimeForShapeMovement = instant;
	}
	
	public void speed() {	
		
		delayTimeForShapeMovement = fastTime; // so the shape speed increases 		
		
	} // speed
	
	public void slow() {	
		
		delayTimeForShapeMovement = normalTime; // this just resets the speed when you stop holding the key	
		
	} // slow
	
	public void moveRight() {
		
		deltaX = 1;
		
	} // moveRight
	
	public void moveLeft() {
		
		deltaX = -1;
		
	} // moveLeft
	
	// reseting 
	public void reset() {
		
		this.y = 0;
		this.x = 4;
		col = false;
		
	} // reset
	
	public int[][] getCoords() {
		// TODO Auto-generated method stub
		return coords;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	

	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	public void timer() {
		
		try {
			
			TimeUnit.SECONDS.sleep((long) .1);
			Board.addTime();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		 
		
	} // timer
	
} // class
