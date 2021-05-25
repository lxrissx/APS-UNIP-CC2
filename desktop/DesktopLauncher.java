package br.com.apsjogo2d.game.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.com.apsjogo2d.game.ApsJogo2D;

public class DesktopLauncher 
{
	public static void main (String[] arg){
		DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.setFromDisplayMode(displayMode); /* Tela cheia */ 
		
		new LwjglApplication(new ApsJogo2D(), config);
	}
}
