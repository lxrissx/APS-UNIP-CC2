package br.com.apsjogo2d.game;

public class Zumbi {

	private int frame = 1;
	private float x, y; 
	private String imagem, maleFemale = "female";
	private static int i = 1;
	private boolean indo;
	public static float larguraQuad, alturaQuad, xFundo, widthFundo, yFundo, heightFundo;
	
	public Zumbi() {
		//**Podem ser feitos até 4 zumbis
		if (i == 1) { y = (float) 0.1 * alturaQuad; x = larguraQuad * 5; maleFemale = "female"; indo = true; // em baixo
		}else if(i == 2){ y = alturaQuad * 3; x = larguraQuad * 10; maleFemale = "male"; indo = true; // direita
		}else if(i == 3) { y = alturaQuad * 6; x = larguraQuad * 5; maleFemale = "female"; indo = false;  // em cima
		}else if(i == 4) { y = alturaQuad * 3; x = (float) 0.1 * alturaQuad; maleFemale = "male"; indo = false;} // esquerda
		
		imagem = "zombie/" + maleFemale + "/Walk/Walk (" + Integer.toString(frame) + ").png";
		i ++;
		
	}
	
	public static void setParametros(float xFundo, float widthFundo, float yFundo, float heightFundo) {
		Zumbi.xFundo = xFundo; 
		Zumbi.widthFundo = widthFundo;
		Zumbi.yFundo = yFundo; 
		Zumbi.heightFundo = heightFundo;
	}
	
	public void mover(int id, float delta) {
		if (id == 1) {
			if(indo) {
				if(x > xFundo){ x -= 50 * delta; }
				else { indo = false; x = xFundo + 10; }
			}else { 
				if(x < widthFundo - alturaQuad){ x += 50 * delta;}
				else { indo = true; x = widthFundo - 10; }
			}
		} else if(id == 2){ 
			if(indo) {
				if(y > yFundo) { y -= 50 * delta; }
				else { indo = false; y = yFundo + 10; }
			}else {
				if(y < heightFundo - alturaQuad) { y += 50 * delta; }
				else { indo = true; y = heightFundo - alturaQuad - 10; }
			} 
			
		} else if (id == 3) {
			if(indo) {
				if(x > xFundo){ x -= 50 * delta; }
				else { indo = false; x = xFundo + 10; }
			}else { 
				if(x < widthFundo - alturaQuad){ x += 50 * delta;}
				else { indo = true; x = widthFundo - alturaQuad - 10; }
			}
		}
		else if(id == 4){ 
			if(indo) {
				if(y > yFundo) { y -= 50 * delta; }
				else { indo = false; y = yFundo + 10; }
			}else {
				if(y < heightFundo - alturaQuad) { y += 50 * delta; }
				else { indo = true; y = heightFundo - alturaQuad - 10; }
			} 
			
		}
		if (frame >= 10) frame = 1;
		else frame += 1;
		imagem = "zombie/" + maleFemale + "/Walk/Walk (" + Integer.toString(frame) + ").png";
	}

	public String getImagem() { return imagem; }
	
	public float getX() { return x; }
	
	public float getY() { return y; }
	
	public boolean getFlip() { return indo; }
}
