package load;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class PlayerController implements KeyListener {

	private Player player;

	public PlayerController(Player player) {
		this.player = player;
	}

	public void setInput(Input input) {

	}

	
	public boolean isAcceptingInput() {
		return true;
	}

	
	public void inputEnded() {

	}

	
	public void inputStarted() {

	}

	
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			this.player.setDirection(0);
			this.player.setMoving(true);
			break;
		case Input.KEY_LEFT:
			this.player.setDirection(1);
			this.player.setMoving(true);
			break;
		case Input.KEY_DOWN:
			this.player.setDirection(2);
			this.player.setMoving(true);
			break;
		case Input.KEY_RIGHT:
			this.player.setDirection(3);
			this.player.setMoving(true);
			break;
		
		case Input.KEY_SPACE :
			this.player.getPlayerAS().setAttk(true);
			break;
		}
			
	}

	
	public void keyReleased(int key, char c) {
		this.player.setMoving(false);
		
	}

}