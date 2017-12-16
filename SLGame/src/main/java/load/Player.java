package load;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

import serverRelated.Server;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * Représente le joueur
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class Player {
	
	private float x = 300, y = 300;
	private int direction = 2;
	private boolean onStair = false;
	private boolean moving = false;
	private boolean notMoovingSend = false;
	private Animation[] animations = new Animation[8];
	private Server serv;
	private Map map;
	private static PlayerActionSer playerAS = new PlayerActionSer();
	public static PlayerActionSer getPlayerAS() {
		return playerAS;
	}
	TrueTypeFont trueTypeFont;
	Font font ;
	public Player(Map map) {
		this.map = map;
	}

	public void init() throws SlickException {
		this.serv = MainScreenGameState.server;
		playerAS.setId(MainScreenGameState.id);
		playerAS.setPosXPlayer(x);
		playerAS.setPosYPlayer(y);
		playerAS.setDirection(direction);
		playerAS.setMoving(moving);
		playerAS.setOnStair(onStair);
		//serv.sendInfo(playerAS);
		font = new Font("Verdana", Font.BOLD, 20);
		trueTypeFont = new TrueTypeFont(font, true);

		// render some text to the screen
		
		SpriteSheet spriteSheet = new SpriteSheet("perso/perso.png", 64, 64);
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 8);
		this.animations[1] = loadAnimation(spriteSheet, 0, 1, 9);
		this.animations[2] = loadAnimation(spriteSheet, 0, 1, 10);
		this.animations[3] = loadAnimation(spriteSheet, 0, 1, 11);
		this.animations[4] = loadAnimation(spriteSheet, 1, 9, 8);
		this.animations[5] = loadAnimation(spriteSheet, 1, 9, 9);
		this.animations[6] = loadAnimation(spriteSheet, 1, 9, 10);
		this.animations[7] = loadAnimation(spriteSheet, 1, 9, 11);
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
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], (int) x - 32, (int) y - 60);
		trueTypeFont.drawString(x -16, y-100, "ID : " + playerAS.getId() , Color.black);
		trueTypeFont.drawString(x - 16, y-80, "X = "+ x + "| Y = "+ y, Color.black);
	}

	public void update(int delta) {
		if (this.moving) {
			notMoovingSend = false;
			float futurX = getFuturX(delta);
			float futurY = getFuturY(delta);
			boolean collision = this.map.isCollision(futurX, futurY);
			if (collision) {
				this.moving = false;
			} 
			else {
				this.x = futurX;
				this.y = futurY;
				this.playerAS.setPosXPlayer(this.x);
				this.playerAS.setPosYPlayer(this.y);
				//this.serv.sendInfo(playerAS);
			}
		}
		else if(!this.notMoovingSend)
		{
			//this.serv.sendInfo(playerAS);
			notMoovingSend = true;
		}
		
	}

	private float getFuturX(int delta) {
		float futurX = x;
		switch (direction) {
		case 1:
			futurX = this.x - .1f * delta;
			break;
		case 3:
			futurX = this.x + .1f * delta;
			break;
		}
		return futurX;
	}

	private float getFuturY(int delta) {
		float futurY = this.y;
		switch (this.direction) {
		case 0:
			futurY = this.y - .1f * delta;
			break;
		case 2:
			futurY = this.y + .1f * delta;
			break;
		case 1:
			if (this.onStair) {
				futurY = this.y + .1f * delta;
			}
			break;
		case 3:
			if (this.onStair) {
				futurY = this.y - .1f * delta;
			}
			break;
		}
		return futurY;
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
	public void setPASXY(float x,float y ) {
		this.playerAS.setPosXPlayer(x);
		this.playerAS.setPosYPlayer(y);
	}
}