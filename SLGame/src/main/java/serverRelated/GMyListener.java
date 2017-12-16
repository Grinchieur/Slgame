package serverRelated;

import org.lwjgl.Sys;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import load.MainScreenGameState;
import load.OtherPlayer;
import load.Player;
import load.PlayerActionSer;

public class GMyListener extends  Listener{
	java.util.Map<Integer, OtherPlayer> connectedPlayer;
	Server server;
	SpriteSheet spriteSheet;
	Player player;
	int clientID;
	GMyListener(java.util.Map<Integer, OtherPlayer> connectedPla, Server serv, SpriteSheet givenSprite, Player player){
		connectedPlayer = connectedPla;
		server = serv;
		spriteSheet= givenSprite;
		
		this.player = player;
	}
	/* gere tout ce que le  server envois*/
	public void received(Connection c, Object o){
		/* PlayerActionSer ou PAS et tout les action que le server/joueurs s'envois, déplacement position et plus tard peut être attaque*/
		if(o instanceof PlayerActionSer){
			
			PlayerActionSer packet = (PlayerActionSer) o;
			
			if( packet.getId() != MainScreenGameState.id) {
				
			
				System.out.println("packet recu : ");
				packet.printPlayerServ();
				System.out.println("packet id : " + packet.getId());
				
				try {
					connectedPlayer.get(packet.getId()).setPAS(packet);
				} catch (Exception e) {
					System.out.println("error in adding PAS");
				}
				System.out.println("received and sent an update to all packet");
			}
			else {
				System.out.println("received own PAS");
			}
		}
		/* quand un nouveau joueurs arrive sur le server*/
		if(o instanceof AddPlayer){
			
			System.out.println("BEFORE ADDING");
			PlayerActionSer tempPAS = new PlayerActionSer((AddPlayer) o);
			System.out.println("AFTER ADDING");
			if( tempPAS.getId() != MainScreenGameState.id) {
				OtherPlayer newPlayer = new OtherPlayer(spriteSheet);
				if(tempPAS.getId() == clientID) {
					System.out.println("ID du joueur");
				}
				else if(connectedPlayer.containsKey(tempPAS.getId()))
				{
					
					
						connectedPlayer.get(tempPAS.getId()).setPAS(tempPAS);
						
					
				}
				else {
				
					System.out.println("IN TRY");
					newPlayer.setPAS(tempPAS);
					newPlayer.setId(tempPAS.getId());
					System.out.println("id reçu = " + tempPAS.getId());
					
					connectedPlayer.put(tempPAS.getId(), newPlayer);
					/*
					for(int p : connectedPlayer.keySet()) {
						
						if(!connectedPlayer.get(p).isConnected()) {
							newPlayer = connectedPlayer.get(p);
							newPlayer.setPAS(tempPAS);
							newPlayer.setId(tempPAS.getId());
							connectedPlayer.remove(p);
							connectedPlayer.put(p, newPlayer);
							connectedPlayer.get(p).setConnected(true);
							break;
						}
						
						
						
						
						
						
						
						
					}*/
				
					
		
			
				}
			}
			
			
		}
		/* permet au client de s'avoirt si ça connection a été accepté ou non.*/
		if(o instanceof AcceptedConnection){
			AcceptedConnection accepted = (AcceptedConnection) o;
			System.out.println("accepted");
			this.player.setPASXY(accepted.x,accepted.y);
			server.connecAccpeted();
			
		}
		/* quand un joueur quite le serrver, a retravaillé au niveau du server, car ne fonctionne pas */
		if(o instanceof PacketRemovePlayer){
			connectedPlayer.get(((PacketRemovePlayer) o).getId()).setConnected(false);;
			
		}

	}

}
