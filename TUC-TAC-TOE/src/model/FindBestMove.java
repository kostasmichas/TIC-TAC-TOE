package model;


import java.io.Serializable;

import control.GameController;

public class FindBestMove implements Serializable{
	
	private static final long serialVersionUID = 10L;


	GameController gc;
	
	public double[] bestMove = new double[2];
	
	public FindBestMove( String[][] gameBoard, GameController gc ) {
		this.gc = gc;
		//X to make his turn
		
		double bestScore = Double.NEGATIVE_INFINITY;
		
		
		for( int i=0; i<3; i++) {
			for( int j=0; j<3; j++) {
				
				// if the cell is available
				if ( gameBoard[i][j] == null ) {
					gameBoard[i][j] = "X";
					double score = miniMax(gameBoard, 0, false);
					gameBoard[i][j] = null;
					if( score > bestScore) {
						bestScore = score;
						bestMove[0] = i;
						bestMove[1] = j;
					}
					
				}
				
			}
		}
		
		System.out.println("Best Move for X: ("+bestMove[0]+","+bestMove[1]+"). ");
		
	}
	
	public int scoreToNumbers() {
		if(gc.getModel().checkWin().equals("X")) {
			return 1;
		}else if(gc.getModel().checkWin().equals("O")) {
			return -1;
		}else if (gc.getModel().checkWin().equals("tie")) {
			return 0;
		}
		return 5;
	}
	
	public double max( double i, double j ) {
		if( i > j) {
			return i;
		}
		return j;
		
	}
	public double min( double i, double j ) {
		if( i > j) {
			return j;
		}
		return i;
		
	}
	
	
	public double miniMax( String[][] gameBoard, int depth, boolean isMaximazing ) {    //if isMaximizing is true then the first move is the maximizing player
		
		String result = gc.getModel().checkWin();
		if( result != null ) {
			int score = scoreToNumbers();
			return score;
		}
		if( isMaximazing ){
			double bestScore = Double.NEGATIVE_INFINITY;

			for( int i=0; i<3; i++) {
				for( int j=0; j<3; j++) {
					// if the cell is available
					if ( gameBoard[i][j] == null ) {
						gameBoard[i][j] = "X";
						double score = miniMax(gameBoard, depth + 1, false);
						gameBoard[i][j] = null;
						bestScore = max( score, bestScore);
						
						
					}
				}
			}
			return bestScore;
		}else {
			
			double bestScore = Double.POSITIVE_INFINITY;
			for( int i=0; i<3; i++) {
				for( int j=0; j<3; j++) {
					// if the cell is available
					if ( gameBoard[i][j] == null ) {
						gameBoard[i][j] = "O";
						double score = miniMax(gameBoard, depth+1, true);
						gameBoard[i][j] = null;
						bestScore = min( score, bestScore);
					}
				}
			}
			return bestScore;
		}
			
		
	}
	
	

}
