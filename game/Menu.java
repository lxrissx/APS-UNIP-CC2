package br.com.apsjogo2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Menu implements Screen{

	final ApsJogo2D game;
	private Texture menu, btnAjuda, btnCreditos, btnJogar;
	private Rectangle ajuda, creditos, jogar;
	
	public Menu(final ApsJogo2D game) {
		this.game = game;
		game.camera.setToOrtho(false);
		
		menu = new Texture("fundo-menu.png");
		btnJogar = new Texture("botao-jogar.png");
		btnAjuda = new Texture("botao-ajuda.png");
		btnCreditos = new Texture("botao-creditos.png");		
		
		jogar = new Rectangle((Gdx.graphics.getWidth()/2) - 150, (Gdx.graphics.getHeight() / 2) + (Gdx.graphics.getHeight() / 5), 300, 85);
		ajuda = new Rectangle((Gdx.graphics.getWidth()/2) - 150, Gdx.graphics.getHeight() / 2, 300, 85);
		creditos = new Rectangle((Gdx.graphics.getWidth()/2) - 150, Gdx.graphics.getHeight() / 3, 300, 85);
		
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.camera.update();
		game.batch.setProjectionMatrix(game.camera.combined);
		
		game.batch.begin();
		
		game.batch.draw(menu, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(btnJogar, jogar.getX(), jogar.getY());
		game.batch.draw(btnAjuda, ajuda.getX(), ajuda.getY());
		game.batch.draw(btnCreditos, creditos.getX(), creditos.getY());
		game.batch.end();

		if(Gdx.input.isTouched()) 
		{
			Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			game.camera.unproject(touch);
			if(creditos.contains(touch.x, touch.y)){
				game.setScreen(new Creditos(game));
				dispose();
		    }
			if(ajuda.contains(touch.x, touch.y)){
				game.setScreen(new Ajuda(game));
				dispose();
		    }
			if(jogar.contains(touch.x, touch.y)){
				game.setScreen(new Jogo(game));
				dispose();
		    }
		}
		
	}
	
	@Override
	public void resize(int width, int height) { }
	
	@Override
	public void pause(){ }
	
	@Override
	public void resume(){ }
	
	@Override
	public void hide(){ }
	
	@Override
	public void dispose(){ }
	
	@Override
	public void show(){ }

}