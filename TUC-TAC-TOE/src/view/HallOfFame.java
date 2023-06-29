package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.GameController;

import model.Player;


@SuppressWarnings({"serial", "unused"})


public class HallOfFame extends GamePanel{

	private GameController gc;
	private Player[] bestPlayers;
	private Player[] playersCopy;

	
	public HallOfFame(GameController gc) {
		super(gc);
		this.gc=gc;
		this.bestPlayers = new Player[10];
	

	
	
		
	}
	
	@Override
	public void paintComponent(Graphics g)   {  
	      super.paintComponent(g);
	      
	      this.shortPlayers();
	      
	      int x = this.getWidth()/2 - 50;
			int y = this.getHeight()/30;		
			g.drawString("Hall of Fame", x, y);
			
			int j = 2;
			int u = 0;
			for(int i = 10-1; i>=0;i--) {
				if(bestPlayers[i] != null ) {
					
					g.drawString((u+1)+". "+bestPlayers[i].getName(), x/5, y*j);
					j+=2;
					u++;
				}
			}

		
	      
	} 
	
	public void shortPlayers(){
		
		this.playersCopy = new Player[gc.getModel().getPlayerCatalogue().numOfPlayers()];
		
		for(int j = 0;j<gc.getModel().getPlayerCatalogue().numOfPlayers();j++) {
			
			playersCopy[j] = gc.getModel().getPlayerCatalogue().getPlayer(j); 
		}
		
		   
			 boolean sorted = false;         //sort player by score ( lower to highest )
			 Player temp;
			    while(!sorted) {
			        sorted = true;
			        for (int i = 0; i < gc.getModel().getPlayerCatalogue().numOfPlayers() - 1; i++) {
			            if (playersCopy[i].getScore() > playersCopy[i+1].getScore()) {
			                temp = playersCopy[i];
			                playersCopy[i] = playersCopy[i+1];
			                playersCopy[i+1] = temp;
			                sorted = false;
			            }
			        }
			    }
			 
			    
			    
		
		
		for(int u = gc.getModel().getPlayerCatalogue().numOfPlayers() - 1 ; u>=0; u--) {
			
			if((playersCopy[u]) != null && (u <10) ) {
				bestPlayers[u] = playersCopy[u];
				System.out.println(bestPlayers[u].getName()+"'s score: "+bestPlayers[u].getScore());
			}
	
			
		}
		
	}

}
