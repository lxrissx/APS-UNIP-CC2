package br.com.apsjogo2d.game;

public class Lixeira 
{
	private int plastico = 0, papel = 0, vidro = 0, metal = 0, total = 0;
	
	public boolean setarPontuacao(int tipo){
		if(tipo == 1) plastico++;
		if(tipo == 2) papel++;
		if(tipo == 3) vidro++;
		if(tipo == 4) metal++;
		
		total = plastico + papel + vidro + metal;
		if(total == 4)  return true;
		else return false;
	}
	
	public int getPlastico() { return plastico; }
	
	public int getPapel() { return papel; }
	
	public int getVidro() { return vidro; }
	
	public int getMetal() { return metal; }
	
}
