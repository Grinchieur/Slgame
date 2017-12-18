package serverRelated;

import java.io.IOException;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import load.MainScreenGameState;
import load.ObjectsGame;
import load.OtherPlayer;
import load.Player;
import load.PlayerActionSer;
import load.ThreadTimer;


public class Server {
	Client client;
	java.util.Map<Integer, OtherPlayer> connectedPlayer =null;
	boolean connAccep = false;
	public boolean isConnAccep() {
		return connAccep;
	}
	
	SpriteSheet spritesheet[] = new SpriteSheet[2];
	Animation[] animations;
	public void init( /*, Animation[] animations*/) throws IOException {
		
		connectedPlayer = ObjectsGame.getConnectedPlayer();
		client = new Client();
		Kryo kryo = client.getKryo();
		/* toute classe envoyer par le server, ou par le client doivent être déclaré comme ci dessous au niveau du client et du server */
	    kryo.register(PacketRemovePlayer.class);
	    kryo.register(AddPlayer.class);
	    kryo.register(PlayerActionSer.class);
	    kryo.register(RequestConnection.class);
	    kryo.register(AcceptedConnection.class);
	    spritesheet = ObjectsGame.spritesheet;
		client.addListener(new GMyListener(connectedPlayer, this, ObjectsGame.getPlayer()));
	    client.start();
	    client.connect(5000, "145.239.78.97", 54555, 54556);
	    RequestConnection request = new RequestConnection();
	    
	    request.id = MainScreenGameState.id;
	    client.sendTCP(request);
	    /* on lance un thread pour tester la connection, si après x time AcceptedConnection n'est pas reçu, le jeu se ferme */
	    ThreadTimer timer = new ThreadTimer();
	    timer.start();
	    System.out.println("after thread launch");
	    
	    while((!connAccep) && (timer.isAlive())) {
	    	
	    }
	    if(!connAccep) {
	    	System.out.println(connAccep);
	    	System.out.println("Connection time out : connection not accepted");
	    	System.exit(-1);
	    }
	    /* sert a envoyer x PAS / seconde au server*/
	    Tickrate serverTickrate = new Tickrate();
	    serverTickrate.start();
	  

	    
	    
	    
	    
	    
	    
	    
	}
	/* comande servant a envoyer le PAS du client */
	public void sendInfo(PlayerActionSer InfoPlayer) {
		client.sendTCP(InfoPlayer);
		System.out.println("Sending PAS Game");
		//InfoPlayer.printPlayerServ();
		
	}
	public void disco() {
		client.stop();
		
	}
	public void connecAccpeted()
	{
		connAccep = true;
	}
	/*Runnable myrunnable = new Runnable() {
	    public void run() {
	        try {
				wait(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//Call your function
	    }
	};*/

	//Call it when you need to run the function
	
/*	private void addtoConPla(PlayerActionSer playerObjet) throws SlickException {
		if( connectedPlayer.size()  != 0) {
			for (int i = 0; i < connectedPlayer.size(); i++)
	        {
	      	  if (playerObjet.getId() == connectedPlayer.get(i).getId()) {
	      		  connectedPlayer.get(i).setPAS(playerObjet); 
	      		  return;
	      	  }
	      	  else
	      	  {
	      		OtherPlayer tempOther = new OtherPlayer();
	      		tempOther.init(this, playerObjet.getId(), playerObjet);
	      		connectedPlayer.add(tempOther);
	      	  }
	        }
		}
		else {
			OtherPlayer tempOther = new OtherPlayer();
      		tempOther.init(this, playerObjet.getId(), playerObjet);
      		connectedPlayer.add(tempOther);
		}
		
	}
	*/
	
	
	
}
