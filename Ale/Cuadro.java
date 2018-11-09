package presentacion;
import javax.swing.*;
import java.awt.*;

public class Cuadro extends JPanel{
	
		private Color color;
		private boolean bola;
		private boolean hueco;
		private char numerob;
		private char numeroh;
		
		public Cuadro(Color c, boolean b, boolean h, char n, char m){
			setBackground(Color.LIGHT_GRAY);
			bola = b;
			hueco = h;
			color = c;
			numerob = n;
			numeroh = m;
		}
		
		public void setColor(Color c){
			color = c;
			repaint();
		}
		
		public Color getColor(){
			return color;
		}
		
		public boolean tieneHueco(){
			return hueco;
		}
		
		public boolean tieneBola(){
			return bola;
		}
		
		public void pintarHueco(){
			hueco = true;
			repaint();
		}
		
		public void pintarBola(){
			bola = true;
			repaint();			
		}
		
		public void borrarHueco(){
			hueco = false;
			repaint();
		}
		
		public void borrarBola(){
			bola = false;
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
			if(hueco){
				colorHueco = Color.BLACK;
				if(bola){
					colorBola = color;
				}
				else{
					colorBola = Color.BLACK;
				}
			}
			else{
				colorHueco = Color.LIGHT_GRAY;
				if(bola){
					colorBola = color;
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