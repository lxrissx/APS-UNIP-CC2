package br.com.apsjogo2d.game;

public class Predios{
	private String tituloImagem;
	private float x, y;
	public static float larguraQuad, alturaQuad;
	private static int i = 1, nmrImg = 1, a = 1;
	
	public Predios() {
		tituloImagem = "predio-" + Integer.toString(nmrImg) + ".png";
		
		//** setar imagens dos prédios e colocar a imagem de reciclagem no prédio nmr 7
		if (i == 7)nmrImg = 7;
		else if (nmrImg <= 5) nmrImg ++;
		else nmrImg = 1;
		
		if (i <= 5){ x = larguraQuad * a; y = alturaQuad;
		}else if (i <= 10){ x = larguraQuad * a; y = alturaQuad * 3; 
		}else{ x = larguraQuad * a; y = alturaQuad * 5; }
		
		//** a contratola a coordenada do eixo x para ter apenas numeros impares 
		if (a >= 9)  a = 1; 
		else  a = a + 2;
		i++;
	}

	public float getX() { return x; }
	public float getY() { return y; }
	public String getImagem() { return tituloImagem; }
	
}
