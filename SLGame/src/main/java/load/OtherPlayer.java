package load;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import serverRelated.Server;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * Représente le joueur
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class OtherPlayer {
	
	private boolean connected =false;
	private int id;
	private float x = 300, y = 300;
	private int direction = 2;
	private boolean onStair = false;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];
	SpriteSheet spriteSheet;
	
	private PlayerActionSer playerAS = new PlayerActionSer();
	
	
	OtherPlayer(){
		
	}
	public OtherPlayer(SpriteSheet givenSprite){
		spriteSheet = givenSprite;
		this.init();
	}
	
	
	
	private void init() {
		
		
			
			System.out.println(" ANIMATION");
			this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
			this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
			this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
			this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
			this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
			this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
			this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
			this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
	
		
	}
	

	

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}

	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval((int) x - 16, (int) y - 8, 32, 16);
		g.drawAnimation(animations[playerAS.getDirection() + (playerAS.isMoving() ? 4 : 0)], (int) x - 32, (int) y - 60);
	}

	public void update() {
		//if (this.playerAS.isMoving()) {
			
				this.x = playerAS.getPosXPlayer();
				this.y = playerAS.getPosYPlayer();
				
			}
		//}
	

	public void setPAS(PlayerActionSer newPAS) {
		this.playerAS = newPAS;
	}

	public PlayerActionSer getPAS( ) {
		return this.playerAS;
	}

	public float getX() {
		return x;
		
	}

	public void setX(float x) {
		this.x = x;
		this.playerAS.setPosXPlayer(x);
		
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		this.playerAS.setPosYPlayer(y);
		
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
		this.playerAS.setDirection(direction);
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
		this.playerAS.setMoving(moving);
		
	}

	public boolean isOnStair() {
		return onStair;
	}

	public void setOnStair(boolean onStair) {
		this.onStair = onStair;
		this.playerAS.setOnStair(onStair);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}