package br.com.apsjogo2d.game;

public class Lixo {
	private int tipo;
	private float x, y, tamanho;
	private String tituloImagem;
	private static int i = 1;
	public static float larguraQuad, alturaQuad;
	
	//PODEM SER FEITOS ATÉ 8 LIXOS
	public Lixo(int tipo) {
		if (tipo == 1) tituloImagem = "lixo-plastico.png";
		else if (tipo == 2) tituloImagem ="lixo-papel.png";
		else if (tipo == 3) tituloImagem = "lixo-vidro.png";
		else if (tipo == 4) tituloImagem = "lixo-metal.png";
		
		//POSIÇÕES DE CADA UM DOS 8 LIXOS 
		if(i == 1) { x = (float) (larguraQuad * 0.3); y = (float) (alturaQuad * 0.3);
		}else if(i == 2) { x = (float) (larguraQuad * 0.3); y = alturaQuad * 6;
		}else if(i == 3) { x = larguraQuad * 10; y = alturaQuad * 6;
		}else if(i == 4) { x = larguraQuad * 10; y = (float) (alturaQuad * 0.3);
		}else if(i == 5) { x = larguraQuad * 2; y = alturaQuad * 4;
		}else if(i == 6) { x = larguraQuad * 8; y = alturaQuad * 4;
		}else if(i == 7) { x = larguraQuad * 2; y = alturaQuad * 2;
		}else if(i == 8) { x = larguraQuad * 8; y = alturaQuad * 2;}
		i ++;
	}
	
	public float getX() { return x; }
	
	public float getY() { return y; }
	
	public int getTipo() { return tipo; }
	
	public String getImagem() { return tituloImagem; }
	
	public float getTamanho() {
		tamanho = alturaQuad - alturaQuad / 3; // TAMANHO PADRÃO MENOR DO QUE A MALHA CRIADA
		return tamanho; 
	}
	
}
