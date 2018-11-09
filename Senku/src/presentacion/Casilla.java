package presentacion;

import javax.swing.*;
import java.awt.*;

public class Casilla extends JPanel{
	
	private Color color;
	private boolean vacia;
	private boolean bola;
	
	public Casilla() {
		color = Color.BLUE;
		pared();
		setVisible(false);
	}
	
	public void pared() {
		vacia = false;
		bola = false;
	}
	
	public void bola() {
		vacia = true;
		bola = true;
	}
	
	public void vacia() {
		vacia = true;
		bola = false;
	}
	
	public void changeColor(Color color) {
		this.color = color;
		repaint();
	}
		
	public void paint(Graphics g){
		Color colorBola;
		Color colorHueco;			
		int centroxh = this.getWidth()/6;
		int centroyh = this.getHeight()/12;
		int centroxb = this.getWidth()/4;
		int centroyb = this.getHeight()/6;
		int diametroBola = this.getHeight()*70/100;
		int diametroHueco = this.getHeight()*90/100;				
		super.paint(g);
		Graphics2D hole = (Graphics2D) g;
		if(vacia){
			colorHueco = Color.GRAY;
			if(bola){
				colorBola = color;
			}
			else{
				colorBola = Color.GRAY;
			}
		}
		else{
			colorHueco = Color.LIGHT_GRAY;
			if(bola){
				colorBola = Color.LIGHT_GRAY;
			}
			else{
				colorBola = Color.LIGHT_GRAY;
			}
		}
		hole.setPaint(colorHueco);
		hole.fillOval(centroxh,centroyh,diametroHueco,diametroHueco);
		hole.setPaint(colorBola);
		hole.fillOval(centroxb,centroyb,diametroBola,diametroBola);			
	}
	
}
