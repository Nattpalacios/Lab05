package aplicacion;

import java.awt.Color;

import presentacion.Casilla;

public class Senku {

	private char[][] matriz;
	private int largo;
	private int ancho;
	
	public Senku() {
		largo = 7;
		ancho = 3;
		matriz = new char[largo][largo];
		iniciaMatriz();
	}
	
	public void iniciaMatriz() {
		for(int i = 0; i < largo; i++) {
			for(int j = 0; j < largo; j++) {
				if(i == (largo/2) && j == (largo/2)) {
					matriz[i][j] = 'v';
				}else if((j >= (largo-ancho)/2 && j < largo - (largo-ancho)/2) || (i >= (largo-ancho)/2 && i < largo - (largo-ancho)/2)) {
					matriz[i][j] = 'b';
				}else {
					matriz[i][j] = 'p';
				}
			}
		}
	}
	
	public void derecha(int x, int y) {
		if(matriz[x][y] == 'b') {
			if((y+2 >= (largo-ancho)/2 && y+2 < largo - (largo-ancho)/2) || (x >= (largo-ancho)/2 && x < largo - (largo-ancho)/2)) {
				if(matriz[x][y+1] == 'b' && matriz[x][y+2] == 'v') {
					matriz[x][y] = 'v';
					matriz[x][y+1] = 'v';
					matriz[x][y+2] = 'b';
				}
			}
		}
	}
	
	public void izquierda(int x, int y) {
		if(matriz[x][y] == 'b') {
			if((y-2 >= (largo-ancho)/2 && y-2 < largo - (largo-ancho)/2) || (x >= (largo-ancho)/2 && x < largo - (largo-ancho)/2)) {
				if(matriz[x][y-1] == 'b' && matriz[x][y-2] == 'v') {
					matriz[x][y] = 'v';
					matriz[x][y-1] = 'v';
					matriz[x][y-2] = 'b';
				}
			}
		}
	}

	public void abajo(int x, int y) {
		if(matriz[x][y] == 'b') {
			if((y >= (largo-ancho)/2 && y < largo - (largo-ancho)/2) || (x+2 >= (largo-ancho)/2 && x+2 < largo - (largo-ancho)/2)) {
				if(matriz[x+1][y] == 'b' && matriz[x+2][y] == 'v') {
					matriz[x][y] = 'v';
					matriz[x+1][y] = 'v';
					matriz[x+2][y] = 'b';
				}
			}
		}
	}

	public void arriba(int x, int y) {
		if(matriz[x][y] == 'b') {
			if((y >= (largo-ancho)/2 && y < largo - (largo-ancho)/2) || (x-2 >= (largo-ancho)/2 && x-2 < largo - (largo-ancho)/2)) {
				if(matriz[x-1][y] == 'b' && matriz[x-2][y] == 'v') {
					matriz[x][y] = 'v';
					matriz[x-1][y] = 'v';
					matriz[x-2][y] = 'b';
				}
			}
		}
	}
	
	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public char[][] getMatriz() {
		return matriz;
	}
	
}
