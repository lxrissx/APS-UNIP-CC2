package br.com.apsjogo2d.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ApsJogo2D extends Game{
	public SpriteBatch batch;
	public OrthographicCamera camera;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		this.setScreen(new Menu(this));
	}

	@Override
	public void render () { super.render(); }
	
	@Override
	public void dispose (){
		super.pause();
		batch.dispose();
		super.dispose();
	}
	
	@Override
	public void pause() {super.pause();}
	
	@Override
	public void resume(){super.resume();}
	
}
