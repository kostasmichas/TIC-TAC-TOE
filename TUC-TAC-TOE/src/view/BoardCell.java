package view;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.LineBorder;

import control.GameController;
import model.FindBestMove;

@SuppressWarnings("serial")
public class BoardCell extends GamePanel implements MouseListener {
	
	
	public static final int CELL_PADDING = 10;

	int row, col;	

	public boolean highlighted;

	public BoardCell(GameController gc, int row, int col) {
		super(gc);
		this.setBackground(Color.WHITE);
		this.row = row;
		this.col = col;
		this.addMouseListener(this);
		this.highlighted = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse entered cell " + this);
		this.highlight();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse exited on cell " + this);
		this.unHighlight();
	}

	private void highlight() {
		if (!highlighted && getModel().inPlay()) {
			highlighted = true;
			repaint();
		}
	}

	private void unHighlight() {
		if (highlighted && getModel().inPlay()) {
			highlighted = false;
			repaint();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBorder(new LineBorder(Color.DARK_GRAY, 1));

		String mark = getModel().getBoardMark(this.row, this.col);
		Graphics2D g2d = (Graphics2D) g;
		int size = this.getSize().width - 2 * CELL_PADDING;
		g2d.setStroke(new BasicStroke(6));
		if (mark == null) {
			if (highlighted) {
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(CELL_PADDING, CELL_PADDING, size, size);
			}
			return;
		} else if (mark.equals("X")) {
			g2d.drawLine(CELL_PADDING, CELL_PADDING, CELL_PADDING + size, CELL_PADDING + size);
			g2d.drawLine(CELL_PADDING + size, CELL_PADDING, CELL_PADDING, CELL_PADDING + size);
		} else {
			g2d.drawOval(CELL_PADDING, CELL_PADDING, size, size);
		}

	}

	@Override
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		System.out.println("Mouse clicked on cell " + this);
        
        if (getModel().inPlay() && gc.getPos() == 0) {
        		
        	
        		
            	getModel().makeMoveX(row, col);
            
       
            if(gc.getModel().getMoves()!=9) {
                if(gc.getView().getRightPanel().currentPlayer.equals("Mr.Bean")) {
                
               
                	
                    int r =(int)(Math.random()*3);
                    int c =(int)(Math.random()*3);
                    while(getModel().boolCheckMoveValidity(r, c)==false) {
                        r = (int)(Math.random()*3);
                        c = (int)(Math.random()*3);
                    }
                
                    
                    getModel().makeMoveX(r, c);
                } if(gc.getView().getRightPanel().currentPlayer.equals("Hall")) {
                    
                	FindBestMove move = new FindBestMove(gc.getModel().getGameBoard(), gc);
                	
                    int r = (int)move.bestMove[0];
                    int c=  (int)move.bestMove[1];
                    while(getModel().boolCheckMoveValidity(r, c)==false) {
                        r = (int)move.bestMove[0];
                        c = (int)move.bestMove[1];
                    }
                
                    
                    getModel().makeMoveX(r, c);
                }
            }
            
            
            
            repaint();
        
            
        }
        else if(getModel().inPlay() && gc.getPos() == 1 ) {
            getModel().makeMoveO(row, col);
            if(gc.getModel().getMoves()!=9) {
                if(gc.getView().getLeftPanel().currentPlayer.equals("Mr.Bean")) {
                	
                
                	
                	
                    int r =(int)(Math.random()*3);
                    int c =(int)(Math.random()*3);
                    while(getModel().boolCheckMoveValidity(r, c)==false) {
                        r = (int)(Math.random()*3);
                        c = (int)(Math.random()*3);
                    }
                    
                    
                    getModel().makeMoveO(r, c);
                    
                    
                }
                if(gc.getView().getLeftPanel().currentPlayer.equals("Hall")) {
                    
                	FindBestMove move = new FindBestMove(gc.getModel().getGameBoard(), gc);
                	
                    int r = (int)move.bestMove[0];
                    int c=  (int)move.bestMove[1];
                    while(getModel().boolCheckMoveValidity(r, c)==false) {
                        r = (int)move.bestMove[0];
                        c = (int)move.bestMove[1];
                    }
                
                    
                    getModel().makeMoveO(r, c);
                }
               
            }

            repaint();
        }
		
		if(getModel().checkWin()!=null) {
			
			System.out.println("");
			
			this.gc.getGame().stopTimer();   //stop timer
	
			System.out.println("Time played: "+this.gc.getGame().getTime()+" seconds.");
			this.gc.getGame().getDate();       // get date of the game   
			
			
			
			
			System.out.println(getModel().getWinnersName() + " wins!");                                                                                   
			System.out.println("");
			System.out.println(getModel().getWinnersName()+":");
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(getModel().getWinnersName()).setAllGames(this.gc.getGame()); //ad game to winners game catalogue
			
			System.out.println("Games Played:"+ gc.getModel().getPlayerCatalogue().setPlayerGames(getModel().getWinnersName()));
			System.out.println("Number of wins: " + gc.getModel().getPlayerCatalogue().setPlayerWin(getModel().getWinnersName()));
			System.out.println("Score: " + gc.getModel().getPlayerCatalogue().setPlayerScore(getModel().getWinnersName()));
			
			System.out.println("");
			
			System.out.println(getModel().getLoosersName()+":");
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(getModel().getLoosersName()).setAllGames(this.gc.getGame()); //ad game to winners game catalogue
			
			System.out.println("Games Played :"+ gc.getModel().getPlayerCatalogue().setPlayerGames(getModel().getLoosersName()));
			System.out.println("Number of losses: " + gc.getModel().getPlayerCatalogue().setPlayerLoose(getModel().getLoosersName()));
			System.out.println("Score: " + gc.getModel().getPlayerCatalogue().setPlayerScore(getModel().getLoosersName()));
			
			this.gc.getView().getLeftPanel().setPlayerStats(gc.getModel().getPlayerStats(gc.getView().getLeftPanel().getCurrentPlayer()));;   //update both players stats after finishing the game
			this.gc.getView().getLeftPanel().repaint();
			this.gc.getView().getRightPanel().setPlayerStats(gc.getModel().getPlayerStats(gc.getView().getRightPanel().getCurrentPlayer()));;
			this.gc.getView().getRightPanel().repaint();
			
			System.out.println("");
			
			this.gc.getGame().setPlayers0(gc.getView().getLeftPanel().getCurrentPlayer());   // set player 0 of the game
			System.out.println("Game player 0:"+ gc.getGame().getPlayers0Name());
			this.gc.getGame().setPlayers1(gc.getView().getRightPanel().getCurrentPlayer());    //set player 1 of the game
			System.out.println("Game player 1:"+ gc.getGame().getPlayers1Name());
			
			this.gc.getGame().setWinner(getModel().getWinnersName());   // set winner and looser
			this.gc.getGame().setLooser(getModel().getLoosersName());

			this.gc.getModel().getGamesCatalogue().addGame(this.gc.getGame());  // add game to games catalogue
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getModel().getWinnersName()).setLatestGame(this.gc.getGame());  // add game to winner's latest games 
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getModel().getLoosersName()).setLatestGame(this.gc.getGame());  // add game to looser's latest games
			
			System.out.println("");
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getModel().getWinnersName()).printLatestGames(); //
			
			System.out.println("");
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getModel().getLoosersName()).printLatestGames(); //
			 
			System.out.println("");
			
			this.gc.getModel().getGamesCatalogue().printAllGames();
			
			System.out.println("");       
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getModel().getWinnersName()).setBestGames(this.gc.getGame());   //add game to  be shorted
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getModel().getLoosersName()).setBestGames(this.gc.getGame());
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getModel().getWinnersName()).printTop5Games();   //print top 5 games of each player
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getModel().getLoosersName()).printTop5Games();
			
			
			this.gc.getView().getLeftPanel().getSelectPlayerBtn().setEnabled(true);
			this.gc.getView().getRightPanel().getSelectPlayerBtn().setEnabled(true);
			this.gc.getView().getMainPanel().showCard("PLAYER_WON");
			this.gc.getView().getMainPanel().getLabelWon().setText(getModel().getWinnersName() + " wins!");
			this.gc.getView().getTopPanel().getDoneBtn().setEnabled(true);
			this.gc.getView().getLeftPanel().startGameBtn.setEnabled(true);
			this.gc.getView().getRightPanel().startGameBtn.setEnabled(true);
			this.gc.getView().getLeftPanel().getSelectPlayerBtn().setEnabled(true);
			this.gc.getView().getRightPanel().getSelectPlayerBtn().setEnabled(true);

			

		}
		if(getModel().checkWin()!=null && getModel().checkWin().equals("tie")) {          //if it is a tie
            
			System.out.println("");
			
			this.gc.getGame().stopTimer();   //stop timer
	
			System.out.println("Time played: "+this.gc.getGame().getTime()+" seconds.");
			this.gc.getGame().getDate();       // get date of the game   
			
			
			
			
			System.out.println("It's a tie");                                                                                   
			
			System.out.println("");
			
			System.out.println(gc.getView().getLeftPanel().getCurrentPlayer()+":");
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getLeftPanel().getCurrentPlayer()).setAllGames(this.gc.getGame()); //ad game to winners game catalogue
			
			System.out.println("Games Played:"+ gc.getModel().getPlayerCatalogue().setPlayerGames(gc.getView().getLeftPanel().getCurrentPlayer()));
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getLeftPanel().getCurrentPlayer()).setTies();   //set Tie
			System.out.println("Ties: "+this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getLeftPanel().getCurrentPlayer()).getTies());
			System.out.println("Score: " + gc.getModel().getPlayerCatalogue().setPlayerScore(gc.getView().getLeftPanel().getCurrentPlayer()));
			
			System.out.println("");
			
			System.out.println(gc.getView().getRightPanel().getCurrentPlayer()+":");
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getRightPanel().getCurrentPlayer()).setAllGames(this.gc.getGame()); //ad game to winners game catalogue
			
			System.out.println("Games Played :"+ gc.getModel().getPlayerCatalogue().setPlayerGames(gc.getView().getRightPanel().getCurrentPlayer()));
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getRightPanel().getCurrentPlayer()).setTies();   //set Tie
			System.out.println("Ties: " + this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getRightPanel().getCurrentPlayer()).getTies());
			System.out.println("Score: " + gc.getModel().getPlayerCatalogue().setPlayerScore(gc.getView().getRightPanel().getCurrentPlayer()));
		
			
			this.gc.getView().getLeftPanel().setPlayerStats(gc.getModel().getPlayerStats(gc.getView().getLeftPanel().getCurrentPlayer()));;   //update both players stats after finishing the game
			this.gc.getView().getLeftPanel().repaint();
			this.gc.getView().getRightPanel().setPlayerStats(gc.getModel().getPlayerStats(gc.getView().getRightPanel().getCurrentPlayer()));;
			this.gc.getView().getRightPanel().repaint();
			
			System.out.println("");
			
			this.gc.getGame().setPlayers0(gc.getView().getLeftPanel().getCurrentPlayer());   // set player 0 of the game
			System.out.println("Game player 0:"+ gc.getGame().getPlayers0Name());
			this.gc.getGame().setPlayers1(gc.getView().getRightPanel().getCurrentPlayer());    //set player 1 of the game
			System.out.println("Game player 1:"+ gc.getGame().getPlayers1Name());
			
	

			this.gc.getModel().getGamesCatalogue().addGame(this.gc.getGame());  // add game to games catalogue
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getLeftPanel().getCurrentPlayer()).setLatestGame(this.gc.getGame());  // add game players latest games 
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getRightPanel().getCurrentPlayer()).setLatestGame(this.gc.getGame());  // add game to players latest games
			
			System.out.println("");
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getLeftPanel().getCurrentPlayer()).printLatestGames(); //
			
			System.out.println("");
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getRightPanel().getCurrentPlayer()).printLatestGames(); //
			 
			System.out.println("");
			
			this.gc.getModel().getGamesCatalogue().printAllGames();
			
			System.out.println("");                        
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getLeftPanel().getCurrentPlayer()).setBestGames(this.gc.getGame());   //add games to be shorted
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getRightPanel().getCurrentPlayer()).setBestGames(this.gc.getGame());
			
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getLeftPanel().getCurrentPlayer()).printTop5Games();                //print top 5 games of each player
			this.gc.getModel().getPlayerCatalogue().getPlayer(gc.getView().getRightPanel().getCurrentPlayer()).printTop5Games();
			
			
			this.gc.getView().getLeftPanel().getSelectPlayerBtn().setEnabled(true);
            this.gc.getView().getRightPanel().getSelectPlayerBtn().setEnabled(true);
            this.gc.getView().getMainPanel().showCard("PLAYER_WON");
            this.gc.getView().getMainPanel().getLabelWon().setText("Its a TIE!");
            this.gc.getView().getTopPanel().getDoneBtn().setEnabled(true);
            this.gc.getView().getLeftPanel().startGameBtn.setEnabled(true);
            this.gc.getView().getRightPanel().startGameBtn.setEnabled(true);
            this.gc.getView().getLeftPanel().getSelectPlayerBtn().setEnabled(true);
            this.gc.getView().getRightPanel().getSelectPlayerBtn().setEnabled(true);
            
        }
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
