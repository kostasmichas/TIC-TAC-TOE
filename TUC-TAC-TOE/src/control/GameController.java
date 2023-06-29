package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import model.FindBestMove;
import model.Game;
import model.GameModel;
import view.MainAreaPanel;
import view.MainWindow;

public class GameController extends WindowAdapter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	MainWindow view;
	GameModel model;
	
	Game game;
	
	private int pos;          
	
	public GameController() {		
		
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		quit();
	}
	
	
	public void start() {
		this.view= new MainWindow(this);
		this.model = new GameModel(this);
		this.view.addWindowListener(this);
		this.view.setVisible(true);
	
	//	this.getView().getMainPanel().showCard(MainAreaPanel.HOF);
		
		
	
		
	}
	
	public void quit() {	
		
		this.model.getPlayerCatalogue().storePlayers();   //Store Players
		
		System.out.println("bye bye...");		
		System.exit(0);
	}
	
	
	
	public void selectPlayer(String p, int pos) {
		this.model.selectPlayer(p, pos);	
		System.out.println("Player " + pos + " set to " + p);
		this.view.getLeftPanel().getStartGameBtn().setEnabled(model.ready());
		this.view.getRightPanel().getStartGameBtn().setEnabled(model.ready());		
	}
	
	public void startGame(int pos) {
		
		this.pos = pos;
	    this.model.setGameBoard(new String[3][3]);
		this.view.getLeftPanel().getStartGameBtn().setEnabled(false);
		this.view.getRightPanel().getStartGameBtn().setEnabled(false);
		
		this.view.getTopPanel().getAddPlayer().setEnabled(false);   //
		this.view.getTopPanel().getDelPlayer().setEnabled(false);   //
		
		this.view.getMainPanel().showCard(MainAreaPanel.BOARD);
		this.view.getMainPanel().showCard("PLAYER_MOVE");
		this.view.getLeftPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
		this.view.getRightPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
		
		this.game = new Game(this.model.getPlayerCatalogue());
		this.game.startTimer();  //
		
		this.model.setMoves(0);
		this.model.setMover(false);
		

		if(this.view.getLeftPanel().getCurrentPlayer().equals("Mr.Bean") && pos==0) {
			
			
			
			int r =(int)(Math.random()*3);
            int c =(int)(Math.random()*3);
            this.model.makeMoveX(r, c);
            model.setMover(false);
            this.pos =1;
            //System.out.println("random cell (" + r +","+ c +")");
        }
        
        if(this.view.getRightPanel().getCurrentPlayer().equals("Mr.Bean") && pos==1) {
           
        	
        
        	
        	int r =(int)(Math.random()*3);
            int c =(int)(Math.random()*3);
            this.model.makeMoveO(r, c);
            model.setMover(false);
            this.pos = 0;
            //System.out.println("random cell (" + r +","+ c +")");
        }
        if(this.view.getLeftPanel().getCurrentPlayer().equals("Hall")&& pos == 0) {
            
        	FindBestMove move = new FindBestMove(getModel().getGameBoard(), this);
        	
            int r = (int)move.bestMove[0];
            int c=  (int)move.bestMove[1];
            while(getModel().boolCheckMoveValidity(r, c)==false) {
                r = (int)move.bestMove[0];
                c = (int)move.bestMove[1];
            }
            
            getModel().makeMoveX(r, c);
            
            model.setMover(false);
            this.pos =1;
        } if(this.view.getRightPanel().getCurrentPlayer().equals("Hall") && pos == 1) {
            
        	FindBestMove move = new FindBestMove(getModel().getGameBoard(), this);
        	
            int r = (int)move.bestMove[0];
            int c=  (int)move.bestMove[1];
            while(getModel().boolCheckMoveValidity(r, c)==false) {
                r = (int)move.bestMove[0];
                c = (int)move.bestMove[1];
            }
            
            getModel().makeMoveO(r, c);
            
            model.setMover(false);
            this.pos =0;
        }
        
        
    }
		
	
	
	
	
	public GameModel getModel() {
		return model;
	}
	
	public MainWindow getView() {
		return view;
	}

	public int getPos() {
		return pos;
	}

	public Game getGame() {
		return game;
	}
			
	
}
