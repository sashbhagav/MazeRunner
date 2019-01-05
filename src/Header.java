/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduinogame;

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Header extends JFrame {
	//array layout used for puzzle
    private int[][] puzzle;

    private boolean solutionFlg = false;
    private static Header instance = null;
    
    //variables used for initializing starting point in maze
    //variables also for initializing max number of moves allowed in maze
    int minimum = 0;
    int maximum = 1;
    int startX = 1;
    int startY = 1;
    int tmpStartX = 1;
    int tmpStartY = 1;//2;
    int origStartX = 0;
    int origStartY = 0;
    int _MAX_INDEX_X = 19;
    int _MAX_INDEX_Y = 19;
    int _NUM_OF_MOVES = 0;
    static int _MAX_MOVES = 0;

    //a method to call the maze and generate a random one
    private int[][] randomizePuzzle(int minimum, int maximum, int termx, int termy) {
        Random rand = new Random();
        return puzzle;
    }
    //calls the array
    private final List<Integer> path = new ArrayList<Integer>();
    private int pathIndex;

     /**method used to set up the frame for the game
     * 
     */
    public Header(int[][] _inpPuzzle, int _max_x, int _max_y, int _strt_x, int _strt_y, boolean _solutionFlg) {//int _max_moves) {
    	
    	puzzle = _inpPuzzle.clone();//cloning puzzle to use for a solution
    	solutionFlg = _solutionFlg;
    	int[][] resultPuzzle = _inpPuzzle.clone();
    	System.out.println(puzzle);
    	System.out.println(resultPuzzle);
    	_MAX_INDEX_X = _max_x;
    	_MAX_INDEX_Y = _max_y;
    	//_MAX_MOVES = _max_moves;
    	startX = _strt_x;
    	startY = _strt_y;
    	tmpStartX = _strt_x;
    	tmpStartY = _strt_y;  
//next is my original positions
        origStartX = _strt_x;
        origStartY = _strt_y;
    	
        setTitle("AMAZO");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int termx = 1;
        int termy = 1;
        
        if (recSolve(_strt_x, _strt_y, resultPuzzle)) {
           	StringBuffer s1 = new StringBuffer();
        	for (int row=0; row < resultPuzzle.length; row++) {
                for (int column=0; column < resultPuzzle[row].length; column++) 
                	if (resultPuzzle[row][column] == 7) 
                			s1.append(resultPuzzle[row][column]).append(",");
             }
        } else {
             }
        if (!path.isEmpty())
        	pathIndex = path.size() - 2;
        else
        	pathIndex = 30;
        _MAX_MOVES = pathIndex+36 ; 
    }

     /* method used to create player in maze
     * creates graphic of red ball and will travel through maze
     */
    @Override
    public void paint(Graphics g) {
    	//painting the player 
    	super.paint(g);
        g.translate(50, 50);
        for (int row = 0; row < puzzle.length; row++) {
            for (int clm = 0; clm < puzzle[0].length; clm++) {
                Color color1;
                switch (puzzle[row][clm]) {
                    case 1:
                    	//the white part is the maze itself
                        color1 = Color.WHITE;
                        break;
                    case 3:
                    	//this represents the 1's in the array
                        color1 = Color.WHITE;
                        break;
                    case 7:
                    	//if the player wants the solution it will highlight it in green
                    	if (solutionFlg)
                    		color1 = Color.GREEN;
                    	else
                    		//otherwise it will stay white for the player to solve the maze
                    		color1 = Color.WHITE;
                        break;
                    case 9:
                    	//the blue is the finish line
                        color1 = Color.BLUE;
                        break;
                    default:
                    	//the black is the 0's of the array in which the player cannot move
                        color1 = Color.BLACK;
                }//the next four lines will paint the maze
                g.setColor(color1);
                g.fillRect(30 * clm, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * clm, 30 * row, 30, 30);
            }
        }//this is for painting the red dot which player can move
        if (puzzle[tmpStartX][tmpStartY] == 0) {
           } else if (puzzle[tmpStartX][tmpStartY] == 9) {
           } else {
            startX = tmpStartX; //starts at given x value
            startY = tmpStartY; //starts at given y value
            g.setColor(Color.RED);//will be painted red everytime it moves
            g.fillOval(startY * 30, startX * 30, 30, 30);
            }
    }
    
    public boolean recSolve(int row, int col, int[][] resultPuzzle){//the solution method
    	boolean solvedFlg = false;
    	if(correctPath(row, col)){
    		if((puzzle[row][col] == 9)  || (row==puzzle.length-1&& col==puzzle[0].length-1)){//will go through each row and column to see if path is possible solution
    			row = puzzle.length-1;
    			col = puzzle[0].length-1;
    			resultPuzzle[row][col]=3;
    			solvedFlg = true;
    		}
    		resultPuzzle[row][col]=3;
   		
    		if((puzzle[row][col] == 9)  || (row==puzzle.length-1&& col==puzzle[0].length-1)){
    			solvedFlg = true;
    		}else{
    			solvedFlg = recSolve(row+1, col, resultPuzzle);//move right
    			if(!solvedFlg){
    				solvedFlg = recSolve(row, col+1, resultPuzzle);//this will try and move up
       			}
    			if (!solvedFlg){
    				solvedFlg = recSolve (row-1, col, resultPuzzle);//move left 
    			}
                if (!solvedFlg){
                	solvedFlg = recSolve (row, col-1, resultPuzzle);//move down
                }
                if(solvedFlg){
                	resultPuzzle[row][col]=7;//will reach solution
                }
    		}
    		   		
    		 	}
    	return solvedFlg;
    }
    public boolean correctPath(int row, int col){
    	boolean correctFlg = false;
    	if(row >=0 && row < puzzle.length
    			&& col>=0 && col< puzzle[0].length){
	    	if(puzzle[row][col]==1 || puzzle[row][col]==9){
	    		correctFlg = true;
	    	} else {
	      		correctFlg = false;
	    	}
    	} else {
    		correctFlg = false;
    	}
    	return correctFlg;
     }

   
   /* keyEvent used to moved player up, down, left and right
     * each move will not surpass max moves
     * will start at given coordinate and will move along
     */
    
    @Override
    protected void processKeyEvent(KeyEvent e) {
        
        if (e.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }
        if (_NUM_OF_MOVES < _MAX_MOVES) {
            tmpStartX = startX;
            tmpStartY = startY;
          
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //this is for moving player to the right
               
                tmpStartY += 1;

                if (tmpStartY > _MAX_INDEX_Y) {
                    tmpStartY -= 1;
                }
              
            
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {  //this is for moving player up
                
                tmpStartX -= 1;
                if (tmpStartX < 0) {
                    tmpStartX = +1;
                }
                
           
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {   //this is for moving player to the left
                tmpStartY -= 1;
               if (tmpStartY < 0) {
                    tmpStartY += 1;
               }
                
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {//this is for moving player down
                
                tmpStartX += 1;
                if (tmpStartX > _MAX_INDEX_X) {
                    tmpStartX = -1;
                }
                
            }
            //if statement blocks player from moving into the 0 parts of the array, which is black in the maze
            if (puzzle[tmpStartX][tmpStartY]!=0){
            	repaint();
            	if (puzzle[tmpStartX][tmpStartY] == 9) {
            		JOptionPane.showConfirmDialog(null, "Congratulations! You have finished", "Game Over", JOptionPane.PLAIN_MESSAGE);
            				}
                ++_NUM_OF_MOVES;
            }else{
            }
            
        } else {
        	/*
        	 * 2 messages will pop up once player exceeds max moves
        	 * one message will tell player they have surpassed max moves
        	 * other message will ask player if they want to start over
        	 */
            System.out.println("U EXECEEDED...." + _NUM_OF_MOVES + ":;  ALLOWD===" + _MAX_MOVES);
           JOptionPane.showMessageDialog(null, "You Have Exceeded Number of Moves", "WARNING!!!!", JOptionPane.OK_OPTION);
           int y = JOptionPane.showConfirmDialog(null, "Want to start over?", "", JOptionPane.YES_NO_OPTION);
          System.out.println(y);
          if(y==0){
        	  /*
        	   * if player says yes the y value printed is 0 so this will allow player to restart game
        	   * same method is applied here and player will repaint at beginning 
        	   */
        	  int termx = 1;
              int termy = 1;
              Depth.randomPath(randomizePuzzle(0, 1, termx, termy), startX, startY, _MAX_INDEX_X, _MAX_INDEX_Y, path);
              if (!path.isEmpty())
              	pathIndex = path.size() - 2;
              else
              	pathIndex = 50;
              _MAX_MOVES = pathIndex+14;

              _NUM_OF_MOVES = 0;
             tmpStartX = origStartX;
             tmpStartY = origStartY;
             startX = origStartX;
             startY = origStartY;
              
              repaint();
          	  
            }
        }
    }
    	//this method is used for launching the game and this will be called in the main menu, this sets up each game level
    public static void launchGame(int gameLevel, final boolean solutionFlg){
    	if (instance != null) {
    		instance.setVisible(false);
    		instance.dispose();
    	}
    	if (gameLevel == 1){//this is for level one
        	SwingUtilities.invokeLater(new Runnable() {
            	/*
            	 * will run project
            	 */
                @Override
                public void run() {
                	//Header header 
                	instance = new Header(ArrayPuzzles.puzzle1, ArrayPuzzles.puzzle1_MAX_INDEX_X, ArrayPuzzles.puzzle1_MAX_INDEX_Y, ArrayPuzzles.puzzle1_STRT_INDEX_X, ArrayPuzzles.puzzle1_STRT_INDEX_Y, solutionFlg);
                	
                    instance.setVisible(true);
                }
            });
        
    	}else if(gameLevel==2){//this is for level two
    		SwingUtilities.invokeLater(new Runnable() {
            	/*
            	 * will run project
            	 */
                @Override
                public void run() {
                	//Header header ;
                	instance = new Header(ArrayPuzzles.puzzle2, ArrayPuzzles.puzzle2_MAX_INDEX_X, ArrayPuzzles.puzzle2_MAX_INDEX_Y, ArrayPuzzles.puzzle2_STRT_INDEX_X, ArrayPuzzles.puzzle2_STRT_INDEX_Y, solutionFlg);
                    instance.setVisible(true);
                }
            });
    	}else if(gameLevel==3){ //this is for level 3
    		SwingUtilities.invokeLater(new Runnable() {
            	/*
            	 * will run project
            	 */
                @Override
                public void run() {
                	instance = new Header(ArrayPuzzles.puzzle3, ArrayPuzzles.puzzle3_MAX_INDEX_X, ArrayPuzzles.puzzle3_MAX_INDEX_Y, ArrayPuzzles.puzzle3_STRT_INDEX_X, ArrayPuzzles.puzzle3_STRT_INDEX_Y, solutionFlg);
                	instance.setVisible(true);
                }
    	});
    	}else if(gameLevel==4){//this is for level 4
    		SwingUtilities.invokeLater(new Runnable() {
            	/*
            	 * will run project
            	 */
                @Override
                public void run() {
                	instance = new Header(ArrayPuzzles.puzzle4, ArrayPuzzles.puzzle4_MAX_INDEX_X, ArrayPuzzles.puzzle4_MAX_INDEX_Y, ArrayPuzzles.puzzle4_STRT_INDEX_X, ArrayPuzzles.puzzle4_STRT_INDEX_Y, solutionFlg);
                    instance.setVisible(true);
                }
    	});
    	}
    }

      public static void main(String[] args) {
        
                           
    }
    
}