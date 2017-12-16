package load;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import serverRelated.Server;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */

/*Le menu du jeu, il y aura dedans : 
 * 		- Les options
 * 		- Les serveurs
 * 		- ect
 */
public class MainScreenGameState extends BasicGameState {
	/* id du state, obligatoire pour slick d'avoir un ID par state */
	
	public static final int ID = 1;
	/*
	 * On prépare le server, car c'est dans le menu que la connection s'établira pour le moment, plus tard on pourra au pire avoir un state, pour la connection en elle même
	 */
	static Server server = new Server();
	

	
	/*
	 * Cette id sert au server de savoir qui est qui, plus tard on changera surement avec un pseudo
	 */
	public static int id = (int) (Math.random() * 1000);
	private Image background;
	private StateBasedGame game;
	
	String message = "Appuyer sur une touche";
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		this.game = game;
		this.background = new Image("BG/forest.png");
		

	}

	/**
	 * Contenons nous d'afficher l'image de fond. .
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString(message, 300, 300);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	
	/*
	 * Pour le moment, une fois sur le "menu" apuiyer sur une touche lancera la communication vers le server
	 * Il tente de se connecter durant une periode de temps, et si la connection echoue, le jeu se ferme, sinon on change de state
	 */
	@Override
	public void keyReleased(int key, char c) {
		message = "accès au serveru, veuillez patientez";
		
		try {
			server.init();
			while(!server.isConnAccep())
			{
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.enterState(ObjectsGame.ID);
	}

	/**
	 * L'identifiant permet d'identifier les différentes boucles, pour passer de l'une à l'autre.
	 */
	@Override
	public int getID() {
		return ID;
	}
	public static Server getServer() {
		return server;
	}
}