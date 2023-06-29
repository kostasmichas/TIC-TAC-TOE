package model;

import java.io.Serializable;

import control.GameController;



public class GameModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	PlayersCatalogue  playerCatalogue;
	GamesCatalogue gamesCatalogue;
		
	Player player;
	
	String [] gamePlayers;		
	String[][] gameBoard;
	GameController gc;
 
	/* Changes for Lab 08 */
	Boolean mover;
	int moves;
	
	public GameModel(GameController gc) {
		this.gc=gc;
		gamePlayers = new String[2];
		gameBoard= null;
		
		playerCatalogue= new PlayersCatalogue(gc);
		gamesCatalogue = new GamesCatalogue(gc);
		
		mover=false;
		moves=0;
		
		
	}
	
	public void selectPlayer(String player, int pos) {
		if (pos<0 && pos>1)return;
		gamePlayers[pos]=player;		
	}
	
	
	public boolean ready() {
		return (gamePlayers[0] != null && gamePlayers[1] !=null);
	}
	
	
	public void startGame() {
		gameBoard= new String[3][3];
		

	}
	
	
	public boolean inPlay() {
		return gameBoard !=null && moves <9;
	}
	
	public boolean NoPlay() {
		return !inPlay();
	}
	
	/* Changes for Lab 08 */
	public int getMover() {
		return mover.compareTo(false);
	}
	
	
	
	
	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public void setMover(Boolean mover) {
		this.mover = mover;
	}

	public String[] getGamePlayers() {
		return gamePlayers;
	}
	

	public String[][] getGameBoard() {
		return gameBoard;
	}
	
	/* Changes for Lab 08 */
	public void checkDimValidity(int row, int col) {
		if (row <0 || col < 0 || row > 2 || col >2) {
			throw new IndexOutOfBoundsException(row + ","+col +" is not a valid board cell");
		}
	}
	
	/* Changes for Lab 08 */
	public void checkMoveValidity(int row, int col) {
		checkDimValidity(row, col);
		if (gameBoard[row][col]!=null) {
			throw new IllegalArgumentException("Non playable cell");
		}
	}
	
	/* Changes for Lab 08 */
	public String getBoardMark(int row, int col) {
		checkDimValidity(row, col);
		return gameBoard[row][col];
	}

	public void setGameBoard(String[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public PlayersCatalogue getPlayerCatalogue() {
		return playerCatalogue;
	}

	public void setPlayerCatalogue(PlayersCatalogue playerCatalogue) {
		this.playerCatalogue = playerCatalogue;
	}
	
	


	public GamesCatalogue getGamesCatalogue() {
		return gamesCatalogue;
	}

	public String checkWin() {
		
		if(gameBoard[0][0]!=null && gameBoard[0][1]!=null && gameBoard[0][2]!=null && gameBoard[0][0].equals(gameBoard[0][1]) && gameBoard[0][1].equals(gameBoard[0][2]))
			return gameBoard[0][0];
		if(gameBoard[1][0]!=null && gameBoard[1][1]!=null && gameBoard[1][2]!=null && gameBoard[1][0].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[1][2]))
			return gameBoard[1][0];
		if(gameBoard[2][0]!=null && gameBoard[2][1]!=null && gameBoard[2][2]!=null && gameBoard[2][0].equals(gameBoard[2][1]) && gameBoard[2][1].equals(gameBoard[2][2]))
			return gameBoard[2][0];
		
		
		if(gameBoard[0][0]!=null && gameBoard[1][0]!=null && gameBoard[2][0]!=null && gameBoard[0][0].equals(gameBoard[1][0]) && gameBoard[1][0].equals(gameBoard[2][0]))
			return gameBoard[0][0];
		if(gameBoard[0][1]!=null && gameBoard[1][1]!=null && gameBoard[2][1]!=null && gameBoard[0][1].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[2][1]))
			return gameBoard[0][1];
		if(gameBoard[0][2]!=null && gameBoard[1][2]!=null && gameBoard[2][2]!=null && gameBoard[0][2].equals(gameBoard[1][2]) && gameBoard[1][2].equals(gameBoard[2][2]))
			return gameBoard[1][1];
		
		if(gameBoard[0][0]!=null && gameBoard[1][1]!=null && gameBoard[2][2]!=null && gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[2][2]))
			return gameBoard[0][0];
		if(gameBoard[0][2]!=null && gameBoard[1][1]!=null && gameBoard[2][0]!=null && gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[2][0]))
			return gameBoard[1][1];
		
		if( moves == 9) {
			return "tie";
		}
		
		return null;
	}
	
	public String getWinnersName() {
		if( checkWin().equals("X") ) {
			return gc.getView().getLeftPanel().getCurrentPlayer();
		}else {
			return gc.getView().getRightPanel().getCurrentPlayer();

		}
		
	} 
	
	public String getLoosersName() {
		if( checkWin().equals("O") ) {
			return gc.getView().getLeftPanel().getCurrentPlayer();
		}else {
			return gc.getView().getRightPanel().getCurrentPlayer();

		}
		
	}


	

	public String getMoverMarkO() {
		System.out.println("Mover:"+ mover );
		return mover? "X": "O";
	}
	
	
	
	public String getMoverMarkX() {
		System.out.println("Mover:"+ mover );
		return mover? "O": "X";
	}
	
	
	public void makeMoveX(int row, int col) {
		checkMoveValidity(row, col);
		gameBoard[row][col]=getMoverMarkX();
		mover=!mover;
		moves++;
	}
	
	
	

	public void makeMoveO(int row, int col) {
		checkMoveValidity(row, col);
		gameBoard[row][col]=getMoverMarkO();
		mover=!mover;
		moves++;
	}
	

	/* Changes for Lab 08 */
	public String getPlayerStats(String player) {
		StringBuilder sb = new StringBuilder("");
		sb.append(player).append("\n\n\n");
		sb.append("Total Score:").append("\t").append(gc.getModel().getPlayerCatalogue().getPlayerScore(player)).append("\n");
		sb.append("Wins:").append("\t").append(gc.getModel().getPlayerCatalogue().getPlayerWin(player)).append("\n");
		sb.append("Losses:").append("\t").append(gc.getModel().getPlayerCatalogue().getPlayerLoose(player)).append("\n");
		sb.append("Ties:").append("\t").append(gc.getModel().getPlayerCatalogue().getPlayerTies(player)).append("\n");
		sb.append("Total Games:").append("\t").append(gc.getModel().getPlayerCatalogue().getPlayerGames(player)).append("\n");
	
		return sb.toString();			
	}
	public boolean boolCheckMoveValidity(int row, int col) {
        if (gameBoard[row][col]==null) {
            return true;
        }
        return false;
	}
}
