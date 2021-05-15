package br.com.apsjogo2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Ajuda implements Screen 
{
	final ApsJogo2D game;
	private Texture fundo, setaVoltar;
	private Rectangle rectVoltar;
	
	public Ajuda(final ApsJogo2D game2) {
		this.game = game2;
		
		rectVoltar = new Rectangle(20, 20, 50, 50);
		
		game.camera = new OrthographicCamera();
		game.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fundo = new Texture(Gdx.files.internal("telaajuda.png"));
		setaVoltar = new Texture("seta-voltar.png");
	}

	@Override
	public void render(float delta) {
		game.camera.update();
		game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		game.batch.draw(fundo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(setaVoltar, 20, 20, (int)(Gdx.graphics.getHeight() * 0.1), (int)(Gdx.graphics.getHeight() * 0.1));
		game.batch.end();
		
		if(Gdx.input.isTouched()) {
			Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			game.camera.unproject(touch);
			if(rectVoltar.contains(touch.x, touch.y)) {
				game.setScreen(new Menu(game));
				dispose();
			}
		}
	}

	@Override
	public void show() {
		
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
				
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
