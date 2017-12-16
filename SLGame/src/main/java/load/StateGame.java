package load;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/* classe du main */
public class StateGame extends StateBasedGame {

	  public static void main(String[] args) throws SlickException {
		  
		  System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
	    new AppGameContainer(new StateGame(), 800, 600, false).start();
	    
	  }

	  public StateGame() {
	    super("SLGAME");
	  }
	 
	 /*chaque state est un "écran", ObjectGame, et le jeu en lui même, avec la carte etc "*/
	  @Override
	  public void initStatesList(GameContainer container) throws SlickException {
	    addState(new MainScreenGameState());
	    addState(new ObjectsGame());
	  }

	
	}
