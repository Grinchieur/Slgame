package load;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import serverRelated.Server;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class ObjectsGame extends BasicGameState {
	static java.util.Map<Integer, OtherPlayer> connectedPlayer = new HashMap<Integer, OtherPlayer>();
	public static final int ID = 3; 
	private GameContainer container;
	
	private static Map map = new Map();
	private static Player player = new Player(map);
	public static Player getPlayer() {
		return player;
	}
	public static void setPlayer(Player player) {
		ObjectsGame.player = player;
	}
	public static Server getServTest() {
		return servTest;
	}
	public static void setServTest(Server servTest) {
		ObjectsGame.servTest = servTest;
	}
	public static SpriteSheet getSpritesheet() {
		return spritesheet;
	}
	public static void setSpritesheet(SpriteSheet spritesheet) {
		ObjectsGame.spritesheet = spritesheet;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		ObjectsGame.id = id;
	}
	private TriggerController triggers = new TriggerController(map, player);
	private Camera camera = new Camera(player);
	public static Server servTest = new Server();
	public boolean gotConnection = false;
	public static SpriteSheet spritesheet;
	//static public Animation[] animations = new Animation[8];
	//TEST A CHANGER AVEC PSEUDO
	
		public static int id = (int) (Math.random() * 1000);

	/* le init est important, tout ce qui est images/ background spritesheet, doit être déclarer ici, mais les init sont appelé en même temps que les "addState(new ObjectsGame());"
	 * et donc les musique et autre doivent être lancé seulement dans la fonction "enter"
	 */

	public void init(GameContainer container, StateBasedGame game ) throws SlickException {
		this.container = container;
		container.setAlwaysRender(true);
		spritesheet = new SpriteSheet("perso/perso.png", 64, 64);
		
		ObjectsGame.map.init();
		
		PlayerController controler = new PlayerController(ObjectsGame.player);
		
		container.getInput().addKeyListener(controler);
		
		
		
		
	}
	/*
	 * la fonction enter permet d'activer certain "truc" au moment ou on entre dans le state, pas avant
	 */
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		player.init();
	}
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}

/* render permet d'afficher le tout */
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		this.camera.place(container, g);
		ObjectsGame.map.renderBackground();
		ObjectsGame.player.render(g);
		/*
		 * c'est un for spécial permetant de récupéré les valeur dans un tableau de type map p prend la valeur du premier OtherPlayer, puis on le render, puis other player etc etc 
		 */
		for(OtherPlayer p : connectedPlayer.values()){
			
				
				p.render(g);
				System.out.println("rendering other player");
			
			
		}
		/*for (int i = 0; i < ObjectsGame.getConnectedPlayer().size(); i++)
		{
			ObjectsGame.getConnectedPlayer().get(i).getPAS();
			ObjectsGame.getConnectedPlayer().get(i).render(g);
		}*/
		
		ObjectsGame.map.renderForeground();
		
	}

	/* pour tout ce qui change on niveau du render*/
	public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException {
		this.triggers.update();
		ObjectsGame.player.update(delta);
		for(OtherPlayer p : connectedPlayer.values()){
			p.update();
			
			
		}
		/*for (int i = 0; i < ObjectsGame.getConnectedPlayer().size(); i++)
		{
			
			ObjectsGame.getConnectedPlayer().get(i).update();
			
		}*/
		
		this.camera.update(container);
		
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			servTest.disco();
			this.container.exit();
		}
	}

	@Override
	public void keyPressed(int key, char c) {
	}

	public static java.util.Map<Integer, OtherPlayer>  getConnectedPlayer() {
		return connectedPlayer;
	}

	public static void setConnectedPlayer(java.util.Map<Integer, OtherPlayer>  coPlayer) {
		connectedPlayer = coPlayer;
	}
	


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
}