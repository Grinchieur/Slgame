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
	private Animation[] animations = new Animation[12];
	SpriteSheet[] spriteSheet;
	
	private PlayerActionSer playerAS = new PlayerActionSer();
	
	
	
	public OtherPlayer() throws SlickException{
		spriteSheet = ObjectsGame.spritesheet;
		this.init();
	}
	
	
	
	private void init() throws SlickException {
		
		
			
			System.out.println(" ANIMATION");
			
			this.animations[0] = loadAnimation(spriteSheet[0], 0, 1, 8);
			this.animations[1] = loadAnimation(spriteSheet[0], 0, 1, 9);
			this.animations[2] = loadAnimation(spriteSheet[0], 0, 1, 10);
			this.animations[3] = loadAnimation(spriteSheet[0], 0, 1, 11);
			this.animations[4] = loadAnimation(spriteSheet[0], 1, 9, 8);
			this.animations[5] = loadAnimation(spriteSheet[0], 1, 9, 9);
			this.animations[6] = loadAnimation(spriteSheet[0], 1, 9, 10);
			this.animations[7] = loadAnimation(spriteSheet[0], 1, 9, 11);
			
			try {
				this.animations[8] = loadAnimation(spriteSheet[1], 0, 6, 0);
				this.animations[9] = loadAnimation(spriteSheet[1], 0, 6, 1);
				this.animations[10] = loadAnimation(spriteSheet[1], 0, 6, 2);
				this.animations[11] = loadAnimation(spriteSheet[1], 0, 6, 3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
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
		
		if (getPAS().isAttk()) {
			
			
		g.drawAnimation(animations[direction + 8], (int) x - 96, (int) y - 120);
		}

		else {
			g.drawAnimation(animations[playerAS.getDirection() + (playerAS.isMoving() ? 4 : 0)], (int) x - 32, (int) y - 60);
		}
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