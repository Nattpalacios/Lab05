package presentacion;

import aplicacion.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;

public class MarbelGameGUI extends JFrame{
	
	private Container contentPane;
	private JPanel opciones;
	private JPanel controles;
	private JPanel tablero;	
	private JMenuBar options;
	private JMenu menu;
	private JMenuItem opcionNuevo;
	private JMenuItem opcionAbrir;
	private JMenuItem opcionSalvar;
	private JMenuItem opcionSalvarComo;
	private JMenuItem opcionSalir;
	private JMenuItem cambiarColor;
	private JButton flechaArriba;
	private JButton flechaAbajo;
	private JButton flechaDerecha;
	private JButton flechaIzquierda;
	private JButton jugar;
	private JButton reiniciar;
	private Color color;
	private int tamano;
	private int numeroBolas;
	private GridLayout cuadricula;
	private MarbelGame mg;
	private String [][] matriz;
	
	public MarbelGameGUI() {
		mg = new MarbelGame(3,3);
		tamano = 3;
		numeroBolas = 3;
		color = Color.BLUE;
		prepareElementos();		
		prepareAcciones();
	}
	
	public static void main(String[] args){
		MarbelGameGUI gui = new MarbelGameGUI();		
		gui.setVisible(true);		
	}
	
	public void prepareElementos(){
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		setTitle("MarbelGame");
		definirTamano();
		centrar();		
		opciones  = new JPanel();
		controles = new JPanel();
		tablero = new JPanel();		
		contentPane.add(opciones, BorderLayout.NORTH);
		contentPane.add(tablero, BorderLayout.CENTER);			
		contentPane.add(controles, BorderLayout.EAST);
		opciones.setLayout(new GridLayout(1,3));
		controles.setLayout(new BorderLayout());
		prepareElementosMenu();
		prepareElementosTablero();			
	}
	
	public void prepareElementosMenu(){
		options = new JMenuBar();
		menu = new JMenu("OPTIONS");
		opcionNuevo =  new JMenuItem("New"); 
		opcionAbrir =  new JMenuItem("Open"); 
		opcionSalvar =  new JMenuItem("Save"); 
		opcionSalvarComo =  new JMenuItem("Save as"); 
		opcionSalir =  new JMenuItem("Exit"); 
		cambiarColor =  new JMenuItem("Change Color"); 
		menu.add(opcionNuevo);
		menu.add(opcionAbrir);
		menu.add(opcionSalvar);
		menu.add(opcionSalvarComo);
		menu.add(opcionSalir);
		menu.add(cambiarColor);
		options.add(menu);
		opciones.add(options);
	}
	
	public void prepareElementosTablero(){		
		ponerBotones();		
		ponerTablero();
	}
	
	public void prepareAcciones() {
		WindowAdapter escuchaCerrarVentana = new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				salga();
			}
		};
		ActionListener escuchaCerrarMenu = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				salgaMenu();
			}
		};
		ActionListener escuchaSalvar = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				guardar();
			}
		};
		ActionListener escuchaAbrir = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				abrir();
			}
		};
		ActionListener escuchaCambiarColor = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				cambioColor();
			}
		};
		ActionListener escuchaMoverDerecha = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				moverDerecha();
			}
		};
		ActionListener escuchaMoverIzquierda = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				moverIzquierda();
			}
		};
		ActionListener escuchaMoverArriba = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				moverArriba();
			}
		};
		ActionListener escuchaMoverAbajo = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				moverAbajo();
			}
		};
		this.addWindowListener(escuchaCerrarVentana);
		opcionSalir.addActionListener(escuchaCerrarMenu);
		opcionSalvar.addActionListener(escuchaSalvar);
		opcionAbrir.addActionListener(escuchaAbrir);
		cambiarColor.addActionListener(escuchaCambiarColor);
		flechaArriba.addActionListener(escuchaMoverArriba);
		flechaAbajo.addActionListener(escuchaMoverAbajo);
		flechaDerecha.addActionListener(escuchaMoverDerecha);
		flechaIzquierda.addActionListener(escuchaMoverIzquierda);
	}
	
	private void centrar(){
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (pantalla.width - getSize().width) / 2;
		int y = (pantalla.height - getSize().height) / 2;
		setLocation(x,y);
	}
	
	private void definirTamano(){
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int x = pantalla.width / 2;
		int y = pantalla.height / 2;
		setSize(x,y);
	}
	
	private void ponerBotones(){
		flechaArriba = new JButton("ARRIBA");
		flechaAbajo  = new JButton("ABAJO");
		flechaDerecha = new JButton("DERECHA");
		flechaIzquierda = new JButton("IZQUIERDA");
		jugar  = new JButton("JUGAR");
		reiniciar = new JButton("REINICIAR");
		controles.add(flechaArriba, BorderLayout.NORTH);
		controles.add(flechaIzquierda, BorderLayout.WEST);				
		controles.add(flechaDerecha, BorderLayout.EAST);
		controles.add(flechaAbajo, BorderLayout.SOUTH);
		opciones.add(reiniciar);
		opciones.add(jugar);
	}
	
	private void ponerTablero(){		
		cuadricula = new GridLayout(tamano,tamano,5,5);
		tablero = new JPanel(cuadricula);
		add(tablero);
		tablero.setBackground(Color.GRAY);
		dibujarTablero();
	}
	
	private void salga(){
		int respuesta = JOptionPane.showConfirmDialog(this,"Are you sure?","Exit",JOptionPane.YES_NO_OPTION);
		if(respuesta == JOptionPane.YES_OPTION){
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		else{
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}
	}
	
	private void salgaMenu(){
		int respuesta = JOptionPane.showConfirmDialog(opcionSalir,"Are you sure?","Exit",JOptionPane.YES_NO_OPTION);
		if(respuesta == JOptionPane.YES_OPTION){
			System.exit(0);
		}
		else{
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}
	}
	
	private void guardar(){
		JFileChooser archivo = new JFileChooser();
		archivo.showSaveDialog(archivo);
		try{
			String saved = archivo.getSelectedFile()+".file";
			saved = saved.replaceAll("[^a-zA-Z0-9.]"," ");
			String[] save = saved.split(" ");
			saved = save[save.length-1];
			JOptionPane.showMessageDialog(null,"En construccion, el archivo a guardar es: "+saved);
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"No fue posible guardar");
		}
	}
	
	private void abrir(){
		JFileChooser archivo= new JFileChooser();
		archivo.showOpenDialog(archivo);
		String abrir= archivo.getSelectedFile().getAbsolutePath();
		abrir=abrir.replaceAll("[^a-zA-Z0-9.]"," ");
		String[] open=abrir.split(" ");
		abrir=open[open.length-1];
		JOptionPane.showMessageDialog(null,"En construccion, el archivo a abrir es: "+abrir);	
	}
	
	private void pintarBolas(){
		Cuadro c;
		for(int i =0; i < tamano*tamano; i++){
			c = (Cuadro)tablero.getComponent(i);
			c.setColor(color);
		}
	}
	
	private void cambioColor(){
		Color colorp = JColorChooser.showDialog(null,"Change Marble Color",color);		
		if(colorp != null && colorp != Color.BLACK){
			color = colorp;
			pintarBolas();
		}		
	}
	
	private void dibujarTablero(){
		matriz = mg.getMatriz();
		for(int i = 0; i < tamano; i++){
			for(int j = 0; j < tamano; j++){
				if(matriz[i][j] != null){
					if(matriz[i][j].charAt(0)=='B'){
						tablero.add(new Cuadro(color,true,false,matriz[i][j].charAt(2),'0'));
					}
					if(matriz[i][j].charAt(0)=='H'){
						tablero.add(new Cuadro(color,false,true,'0',matriz[i][j].charAt(2)));
					}
					if(matriz[i][j].charAt(0)=='M'){
						tablero.add(new Cuadro(Color.RED,true,true,matriz[i][j].charAt(2),'0'));
					}
					if(matriz[i][j].charAt(0)=='V'){
						tablero.add(new Cuadro(Color.GREEN,true,true,matriz[i][j].charAt(2),'0'));
					}
				}
				else{
					tablero.add(new Cuadro(color,false,false,'0','0'));
				}
			}
		}
	}
	
	private void moverAbajo(){
		mg.moverAbajo();
		dibujarTablero();
	}
	
	private void moverArriba(){
		mg.moverArriba();
		dibujarTablero();
	}
	
	private void moverDerecha(){
		//mg.moverDerecha();
		dibujarTablero();
	}
	
	private void moverIzquierda(){
		mg.moverIzquierda();
		dibujarTablero();
	}
}
