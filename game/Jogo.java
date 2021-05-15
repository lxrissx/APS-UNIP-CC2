package br.com.apsjogo2d.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Jogo implements Screen{
	
	final ApsJogo2D game;
	private BitmapFont font;
	private Lixeira contarPontuacao;
	private Personagem dadosPersonagem;
	public float larguraQuad, alturaQuad, tamanhoPadrao, posicaoPontosY, posicaoPontosX;
	private ArrayList<Zumbi> dadosZumbi = new ArrayList<>();
	private ArrayList<Predios> dadosPredios = new ArrayList<>();
	private ArrayList<Lixo> dadosLixo = new ArrayList<>();
	private Texture fundo, moldura, popup, txtrPause, lixeiras, btnMenu, btnSair, btnVoltar;
	private Texture texturePersonagem, textureZumbi[], textureLixo[], texturePredio[] = new Texture[15];
	private int i, qntsZumbis = 4, qntsLixos = 8, contadorLixos = 0;
	private float heightLixeiras, widthFundo, heightFundo, widthMoldura, heightMoldura, widthLixeiras, xLixeiras, xFundo, yFundo;
	float[] xPredios = new float[15], yPredios = new float[15]; //** passar como parametro para saber onde os personagens não podem ir
	private Integer[] lixosColetados;
	private boolean coletado, ganhou = false, perdeu = false, pausou = false, desbloqueado = false;
	private Rectangle predioRect, lixoRect[], zumbiRect[], personagemRect, btnMenuRect, btnSairRect, btnVoltarRect, btnPauseRect;
	
	public Jogo(final ApsJogo2D game) {
		this.game = game;
		/* TAMANHO DAS IMAGENS DE BACKGROUD */
		heightLixeiras = Gdx.graphics.getHeight() / 8; // Altura das lixeiras com proporção ao tamanho da tela
		widthLixeiras = heightLixeiras * 4; // Largura das quatro lixeiras juntas, elas são quadradas e por isso a multiplicação
		xLixeiras = (Gdx.graphics.getWidth() / 2) - (widthLixeiras /2); // Onde começa a imagem lixeiras
		heightMoldura = Gdx.graphics.getHeight() - heightLixeiras; // Altura da moldura  menos o tamanho das lixeiras
		widthMoldura = Gdx.graphics.getWidth(); // A moldura é a parte rosa
		widthFundo = (float) (widthMoldura - (widthMoldura * 0.02)); // O fundo recebe a moldura menos 2% dela msm 
		heightFundo = (float) (heightMoldura - (heightMoldura * 0.02)); // Fundo é a parte cinza
		xFundo = (widthMoldura / 2) - (widthFundo / 2); // Centralizar largura da parte cinza
		yFundo = (heightFundo / 2) - (heightFundo / 2); // Centralizar altura da parte cinza
		
		//** DIVIDIR A TELA EM UMA MALHA 11x7 PARA DEFINIR AS COORDENADAS **\\
		larguraQuad = widthFundo / 11; 
		alturaQuad = heightFundo / 7;
		
		//Setar a pontuação nas lixeiras
		posicaoPontosY = (alturaQuad / 2) + heightFundo;
		posicaoPontosX = (alturaQuad / 2);

		moldura = new Texture("moldura.jpg");
		fundo = new Texture("fundo-cinza.jpg");
		lixeiras = new Texture("lixeiras.png");
		txtrPause = new Texture("pause.png");
		
		//SETANDO PARAMETROS ESTATICOS 
		Lixo.larguraQuad = larguraQuad; // ZUMBIS E LIXOS USAM COORDENADAS PARES
		Lixo.alturaQuad = alturaQuad;
		Zumbi.larguraQuad = larguraQuad; 
		Zumbi.alturaQuad = alturaQuad;
		Zumbi.setParametros(xFundo, widthFundo, yFundo, heightFundo);
		Predios.larguraQuad = larguraQuad; // PREDIOS USAM COORDENADAS IMPARES
		Predios.alturaQuad = alturaQuad;
		
		//CRIANDO OS OBJETOS, OS TEXTURES E OS RECTANGLES
		dadosPersonagem = new Personagem(larguraQuad, alturaQuad);
		personagemRect = new Rectangle(dadosPersonagem.getX(), dadosPersonagem.getY(), this.getTamanhoPadrao(), this.getTamanhoPadrao());
		
		for(int i = 0; i < 15; i++) { dadosPredios.add(new Predios()); }
		for(int i = 0; i < qntsZumbis; i++) { dadosZumbi.add(new Zumbi()); }
		int j = 1;
		for(int i = 0; i < qntsLixos; i++) {
			dadosLixo.add(new Lixo(j)); 
			if(j < 4) j ++; // Controlar o tipo de lixo que está sendo criado
			else j = 1;
		}
		
		i = 0;
		for(Predios contPredio: dadosPredios) {
			if(i == 7) { 
				texturePredio[i] = new Texture("predio-7-cinza.png"); // SETAR PRÉDIO CINZA QUANDO ELE ESTIVER BLOQUEADO
				predioRect = new Rectangle(contPredio.getX()-10, contPredio.getY()-10, larguraQuad, larguraQuad);
			}
			else texturePredio[i] = new Texture(contPredio.getImagem());

			xPredios[i] = contPredio.getX(); // GUARDAR X E Y PARA PASSAR PARA O MÉTODO PERSONAGEM
			yPredios[i] = contPredio.getY();
			i++;
		}
		zumbiRect = new Rectangle[qntsZumbis];
		i = 0;
		for(Zumbi contZumbi: dadosZumbi) {
			zumbiRect[i] = new Rectangle(contZumbi.getX(), contZumbi.getY(), this.getTamanhoPadrao(), this.getTamanhoPadrao());
			i++;
		}
		textureLixo = new Texture[qntsLixos];
		lixoRect = new Rectangle[qntsLixos];
		lixosColetados = new Integer[qntsLixos];
		i = 0;
		for(Lixo contLixo: dadosLixo) {
			textureLixo[i] = new Texture(contLixo.getImagem());
			lixoRect[i] = new Rectangle(contLixo.getX(), contLixo.getY(), contLixo.getTamanho(), contLixo.getTamanho());
			i++;
		}
		btnMenu = new Texture("pop-btn-menu.png");
		btnSair = new Texture("pop-btn-sair.png");
		btnVoltar = new Texture("pop-btn-voltar.png");
		//FIM DA CRIAÇÃO DOS OBJETOS, OS TEXTURES E OS RECTANGLES
		
		game.camera.setToOrtho(false);
        
        contarPontuacao = new Lixeira();
        font = new BitmapFont();
        font.setColor(0, 0, 0, 1);
        
        btnPauseRect = new Rectangle(0, heightMoldura + 10, this.getTamanhoPadrao(), this.getTamanhoPadrao());
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		for(int i = 0; i < zumbiRect.length; i++) if (personagemRect.overlaps(zumbiRect[i])) this.perdeu(i);
		for(int i = 0; i < lixoRect.length; i++) if (personagemRect.overlaps(lixoRect[i])) this.coletarLixo(i);
		
		if(desbloqueado && personagemRect.overlaps(predioRect)) this.ganhou();
		
		if(Gdx.input.isTouched()) {
	        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			game.camera.unproject(touch);
			if(btnPauseRect.contains(touch.x, touch.y)){
				this.pause();
		    }	
		}
		
		//MOVER PERSONAGEM PRINCIPAL PASSANDO PARÂMETROS: Direção, DeltaTime, Array das coordenadas dos prédios
		if(!perdeu) {
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) dadosPersonagem.mover(1, Gdx.graphics.getDeltaTime(), xPredios, yPredios, xFundo, widthFundo, yFundo, heightFundo);
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) dadosPersonagem.mover(2, Gdx.graphics.getDeltaTime(), xPredios, yPredios, xFundo, widthFundo, yFundo, heightFundo);
			if(Gdx.input.isKeyPressed(Input.Keys.UP)) dadosPersonagem.mover(3, Gdx.graphics.getDeltaTime(), xPredios, yPredios, xFundo, widthFundo, yFundo, heightFundo);
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) dadosPersonagem.mover(4, Gdx.graphics.getDeltaTime(), xPredios, yPredios, xFundo, widthFundo, yFundo, heightFundo);
			
			//FAZER OS ZUMBIS SE MOVEREM
	        int j = 1;
	  		for(Zumbi contZumbi: dadosZumbi) {
	  			contZumbi.mover(j, delta);
	  			zumbiRect[j-1].setPosition(contZumbi.getX(), contZumbi.getY());
	  			j++;
	  		}
		}else {
			dadosPersonagem.setImagemPerdeu();
		}
  		// CRIANDO TEXTURE E SPRITES PARA A PERSONAGEM E OS ZUMBIS 
  		texturePersonagem = new Texture(dadosPersonagem.getImagem());
  		textureZumbi = new Texture[qntsZumbis];
  		
  		Sprite spritePersonagem = new Sprite(texturePersonagem);
  		Sprite spriteZumbi[] = new Sprite[qntsZumbis];
  		i = 0;
		for(Zumbi contZumbi: dadosZumbi) {
			textureZumbi[i] = new Texture(contZumbi.getImagem());
			 spriteZumbi[i] = new Sprite(textureZumbi[i]);
			i++;
		}
		
		//VIRAR IMAGEM DA PERSONAGEM CASO ELA VÁ PARA A ESQUERDA
        if(dadosPersonagem.getFilp()) spritePersonagem.flip(true, false);
        else spritePersonagem.flip(false, false);
        
        spritePersonagem.setBounds(dadosPersonagem.getX(), dadosPersonagem.getY(), alturaQuad, alturaQuad);
       
        i = 0;
		for(Zumbi contZumbi: dadosZumbi) {
			spriteZumbi[i].setBounds(contZumbi.getX(), contZumbi.getY(), alturaQuad, alturaQuad);
			zumbiRect[i].setPosition(contZumbi.getX(), contZumbi.getY());
			if(contZumbi.getFlip()) spriteZumbi[i].flip(true, false);
			else spriteZumbi[i].flip(false, false);
			i++;
		}
		//FIM DA CRIAÇÃO DOS TEXTURE E SPRITES PARA A PERSONAGEM E OS ZUMBIS
		
		game.camera.update();
		game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		
		game.batch.draw(moldura, 0, 0, widthMoldura, heightMoldura);
		game.batch.draw(fundo, xFundo, yFundo, widthFundo, heightFundo);
		game.batch.draw(lixeiras, xLixeiras, heightMoldura, widthLixeiras, heightLixeiras);
		game.batch.draw(txtrPause, 0, heightMoldura + 10, this.getTamanhoPadrao(), this.getTamanhoPadrao());
		
		i = 0;
		for(Lixo contLixo: dadosLixo) {
			coletado = false;
			for(int cont = 0; cont < lixosColetados.length; cont++) {
				if(lixosColetados[cont] != null) {if(i == lixosColetados[cont]) { coletado = true; }}
			}
			if(!coletado) {
				game.batch.draw(textureLixo[i], contLixo.getX(), contLixo.getY(), contLixo.getTamanho(), contLixo.getTamanho());
				lixoRect[i].setPosition(contLixo.getX(), contLixo.getY()); //** atualizar posição do rectangle
			}
			i++;
		}
		i = 0;
		for(Predios contPredio: dadosPredios) {
			game.batch.draw(texturePredio[i], contPredio.getX(), contPredio.getY(), alturaQuad, alturaQuad);
			i++;
		}
		
		spritePersonagem.draw(game.batch);
		personagemRect.setPosition(dadosPersonagem.getX(), dadosPersonagem.getY());
		for(int i = 0; i < spriteZumbi.length; i++) spriteZumbi[i].draw(game.batch);
		
		font.draw(game.batch, contarPontuacao.getPlastico() + "/2", xLixeiras + posicaoPontosX, posicaoPontosY);
		font.draw(game.batch, contarPontuacao.getPapel() + "/2", xLixeiras + posicaoPontosX + alturaQuad, posicaoPontosY);
		font.draw(game.batch, contarPontuacao.getVidro() + "/2", xLixeiras + posicaoPontosX + (alturaQuad * 2), posicaoPontosY);
		font.draw(game.batch, contarPontuacao.getMetal() + "/2", xLixeiras + posicaoPontosX + (alturaQuad * 3), posicaoPontosY);
		
		//MOSTRAR POP UP
		if(perdeu || ganhou || pausou) {
			float largurapop = widthFundo - (larguraQuad * 5), alturapop = heightFundo - (alturaQuad * 3);
			float xpop = (widthFundo/2) - (largurapop/2), ypop = alturaQuad * 2;
			float larguraBotao = largurapop / 5, alturaBotao = (float) (larguraBotao / 2.5);
			
			game.batch.draw(popup, xpop, ypop, largurapop, alturapop);
			game.batch.draw(btnVoltar, xpop + 30, ypop + 30, larguraBotao, alturaBotao);
			game.batch.draw(btnMenu, (widthFundo/2) - (larguraBotao/2) , ypop + 30, larguraBotao, alturaBotao);
			game.batch.draw(btnSair, xpop + largurapop - larguraBotao - 30, ypop + 30, larguraBotao, alturaBotao);
			
			btnVoltarRect = new Rectangle(xpop + 30, ypop + 30, larguraBotao, alturaBotao);
			btnMenuRect = new Rectangle((widthFundo/2) - (larguraBotao/2) , ypop + 30, larguraBotao, alturaBotao); 
			btnSairRect = new Rectangle(xpop + largurapop - larguraBotao - 30, ypop + 30, larguraBotao, alturaBotao);
			
			if(Gdx.input.isTouched()) {
		        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				game.camera.unproject(touch);
				if(btnVoltarRect.contains(touch.x, touch.y)){
					//game.setScreen(new Jogo(game));
			    }	
				if(btnMenuRect.contains(touch.x, touch.y)){
					game.setScreen(new Menu(game));
					try {
						dispose();
					} catch (Exception e) {
						System.out.println(e);
					}
			    }	
				if(btnSairRect.contains(touch.x, touch.y)){
					try {
						this.dispose();
						game.dispose();
					} catch (Exception e) {
						System.out.println(e);
					}
			    }	
			}
		}
		game.batch.end();
		
	}
	
	public float getTamanhoPadrao() {return  alturaQuad - alturaQuad / 5;}
	public void tempo() { }
	public void ganhou() { 
		game.pause();
		popup = new Texture("pop-ganhou.png");
		btnVoltar = new Texture("pop-btn-reiniciar.png");
		ganhou = true;
	}
	public void perdeu(int acertou) { 
		System.out.println("Você acertou o zumbi " + acertou);
		zumbiRect[acertou].set(0, heightLixeiras, 1, 1);
		personagemRect.set(500, heightLixeiras, 1, 1);
		perdeu = true;
		popup = new Texture("pop-perdeu.png");
		btnVoltar = new Texture("pop-btn-reiniciar.png");
		game.pause();
		
	}
	public void coletarLixo(int i) {
		int tipo = 0;
		lixosColetados[contadorLixos] = i;
		lixoRect[i].set(0, heightLixeiras, 1, 1);
		
		if(i == 0 || i == 4) tipo = 1;
		if(i == 1 || i == 5) tipo = 2;
		if(i == 2 || i == 6) tipo = 3;
		if(i == 3 || i == 7) tipo = 4;
		
		if(contarPontuacao.setarPontuacao(tipo)) this.desbloquearPredio();
		
		contadorLixos++;
	}
	public void pause() {
		popup = new Texture("pop-pause.png");
		pausou = true;
		game.pause();
	}
	public void desbloquearPredio() {
		texturePredio[7] = new Texture("predio-7.png");
		desbloqueado = true;
	}
	@Override
	public void dispose() {
		font.dispose();
		moldura.dispose();
		fundo.dispose();
		popup.dispose();
		btnVoltar.dispose();
		btnMenu.dispose();
		btnSair.dispose();
		lixeiras.dispose();
		texturePersonagem.dispose();
		
		for(i = 0; i < texturePredio.length; i++) texturePredio[i].dispose();
		for(i = 0; i < textureZumbi.length; i++) textureZumbi[i].dispose(); 
		for(i = 0; i < textureLixo.length; i++) textureLixo[i].dispose();
	}

	@Override
	public void show() { }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}
	
}
