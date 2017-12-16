package serverRelated;



import java.util.Map;

import com.esotericsoftware.kryonet.Server;

import load.MainScreenGameState;
import load.Player;
import load.PlayerActionSer;

public class Tickrate extends Thread {
	
	
	public Tickrate() {
			
	}
	
	public void run( ) {
		try {
			while(true) {
				Thread.sleep(10);
				
				MainScreenGameState.getServer().sendInfo(Player.getPlayerAS());	
					
				}
			}	
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}



