package br.com.apsjogo2d.game;

public class Personagem {
	private float x, y;
	private String imagem;
	private int frame = 1, direcao, framePerdeu = 1;
	private float alturaQuad;
	
	public Personagem(float larguraQuad, float alturaQuad) {
		this.alturaQuad = alturaQuad;
		x = larguraQuad * 5;
		y = alturaQuad * 2;
		imagem = "cutegirl/Idle/Idle (" + Integer.toString(frame) + ").png";
	}
	
	public String getImagem() { return imagem; }
	
	public void setImagemPerdeu() { imagem = "cutegirl/Dead/Dead ("+framePerdeu+").png"; if(framePerdeu < 30)framePerdeu++;}
	
	public float getX() { return x; }
	
	public float getY() { return y; }
	
	public boolean getFilp() {
		if(direcao == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	public void mover(int direcao, float deltaTime, float xPredios[], float yPredios[], float xFundo, float widthFundo, float yFundo, float heightFundo) {
		this.direcao = direcao;
		boolean colidiuX = false;
		boolean colidiuY = false;
		int intX = (int) x;
		int intY = (int) y;
		int areaPredio, areaGirlX, areaGirlY, xPredioInt, yPredioInt;
		areaGirlY = (int)(intY + alturaQuad - (alturaQuad/5));
		areaGirlX = (int)(intX + alturaQuad - (alturaQuad/5));
		
		if (frame >= 9) frame = 1;
		else frame = frame+ 1;
	
		if(direcao == 1) { //ESQUERDA
			intX -= 5; 
			if(xFundo < intX) {
				colidiuX = false;
				colidiuY = false;
				for (int i = 0; i < xPredios.length; i ++) {
					areaPredio = (int)(xPredios[i] + alturaQuad);
					xPredioInt = (int)xPredios[i];
					for(float j = xPredioInt; j < areaPredio; j++) {
						if(intX == j) { colidiuX = true; }
					}
				}
				for (int i = 0; i < yPredios.length; i ++) {
					areaPredio = (int)(yPredios[i] + alturaQuad);
					yPredioInt = (int)yPredios[i];
					for(float j = yPredioInt; j < areaPredio; j++) {
						for (int k = intY; k < areaGirlY; k++) {
							if(k == j) { colidiuY = true; }
						}
					}
				}
				if(colidiuX == true && colidiuY == true) {
					
				}else {
					x -= 200 * deltaTime;
				}
			}
		}
		if(direcao == 2) { // DIREITA
			intX += (alturaQuad - (alturaQuad/5)) + 2;
			
			if(intX < widthFundo) {
				colidiuX = false;
				colidiuY = false;
				for (int i = 0; i < xPredios.length; i ++) {
					areaPredio = (int)(xPredios[i] + alturaQuad);
					xPredioInt = (int)xPredios[i];
					for(float j = xPredioInt; j < areaPredio; j++) {
						if(intX == j) { colidiuX = true; }
					}
				}
				for (int i = 0; i < yPredios.length; i ++) {
					areaPredio = (int)(yPredios[i] + alturaQuad);
					yPredioInt = (int)yPredios[i];
					for(float j = yPredioInt; j < areaPredio; j++) {
						for (int k = intY; k < areaGirlY; k++) {
							if(k == j) { colidiuY = true; }
						}
					}
				}
				if(colidiuX == true && colidiuY == true) {
					
				}else {
					x += 200 * deltaTime;
				}
			}
		} 
			
		if(direcao == 3) { // PARA CIMA 
			intY += (alturaQuad - (alturaQuad/5)) + 2;
			if(intY < heightFundo) {
				colidiuX = false;
				colidiuY = false;
				for (int i = 0; i < yPredios.length; i ++) {
					areaPredio = (int)(yPredios[i] + alturaQuad);
					yPredioInt = (int)yPredios[i];
					for(float j = yPredioInt; j < areaPredio; j++) {
						if(intY == j) { colidiuY = true; }
					}
				}
				for (int i = 0; i < xPredios.length; i ++) {
					areaPredio = (int)(xPredios[i] + alturaQuad);
					xPredioInt = (int)xPredios[i];
					for(float j = xPredioInt; j < areaPredio; j++) {
						for(float k = intX; k < areaGirlX; k++) {
							if(k == j) { colidiuX = true; }
						}
					}
				}
				if(colidiuX == true && colidiuY == true) {
					
				}else{
					y += 200 * deltaTime;
				}
			}
		}
			
		if(direcao == 4) { // PARA BAIXO
			if(yFundo < intY) {
				intY -= 5;
				colidiuX = false;
				colidiuY = false;
				for (int i = 0; i < yPredios.length; i ++) {
					areaPredio = (int)(yPredios[i] + alturaQuad);
					yPredioInt = (int)yPredios[i];
					for(float j = yPredioInt; j < areaPredio; j++) {
						if(intY == j) { colidiuY = true; }
					}
				}
				for (int i = 0; i < xPredios.length; i ++) {
					areaPredio = (int)(xPredios[i] + alturaQuad);
					xPredioInt = (int)xPredios[i];
					for(float j = xPredioInt; j < areaPredio; j++) {
						for(int k = intX; k < areaGirlX; k++) {
							if(k == j) { colidiuX = true; }
						}
					}
				}
				if(colidiuX == true && colidiuY == true) {
					
				}else{
					y -= 200 * deltaTime;
				}
			}
		}
		imagem = "cutegirl/Walk/Walk (" + Integer.toString(frame) + ").png";
			
	}
	
}
