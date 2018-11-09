package presentacion;

import aplicacion.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import javax.swing.event.*;


/**
 * @version (02/11/2018)
 * @author (Natalia Palacios)
 */

public class SenkuGUI extends JFrame{
	
	private JMenuBar op;
	private JMenu menu;
	private JMenuItem nuevo;
	private JMenuItem abrir;
	private JMenuItem salvar;
	private JMenuItem salvarC;
	private JMenuItem salir;
	private JMenuItem color;
	private int ancho;
	private int largo;
	private JPanel panel;
	private JPanel board;
	private URL tituloo =  this.getClass().getResource("TituloSenku.jpg");
	private Image titulo = new ImageIcon(tituloo).getImage();
	private GridLayout cuadricula;
	private Casilla[][] matriz;
	private Color colorcito;
	private Senku sk;
	
	private SenkuGUI(){
		sk = new Senku();
		ancho = 3;
		largo = 7;
		matriz = new Casilla[largo][largo];
		prepareElementos();
		prepareElementosMenu();
		prepareElementosTablero();
		prepareAcciones();
		
	}
	
	private void prepareElementos(){
        this.setTitle("Senku");
		Dimension d = tamanoPantalla();
		this.setSize(d.width/2,d.height/2);
		centre();
				
	}
	
	private void prepareElementosMenu() {
		op = new JMenuBar();
		setJMenuBar(op);
		menu =  new JMenu("Options");
		op.add(menu);
		nuevo = new JMenuItem("Nuevo");
		menu.add(nuevo);
		abrir = new JMenuItem("Abrir");
		menu.add(abrir);
		salvar = new JMenuItem("Salvar");
		menu.add(salvar);
		salvarC = new JMenuItem("Salvar como");
		menu.add(salvarC);
		color = new JMenuItem("Color");
		menu.add(color);
		salir = new JMenuItem("Salir");
		menu.add(salir);
		
	}
	
	private void prepareElementosTablero() {
		prepareTitulo();
		prepareGrid();
	}
	
	private void prepareTitulo() {
		setLayout(new BorderLayout());
		panel = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(titulo, 0, 0, getWidth(), getHeight(), this);
			}
		};
		this.add(panel,BorderLayout.NORTH);
	}
	
	private void prepareGrid() {
		board = new JPanel();
		cuadricula = new GridLayout(largo,largo,3,3);
		add(board);
		board.setLayout(cuadricula);
		dibujarGrid(sk.getMatriz());
		this.add(board,BorderLayout.CENTER);
	}
	
	private void dibujarGrid(char[][] mat) {
		for(int i = 0; i < largo; i++) {
			for(int j = 0; j < largo; j++) {
				Casilla c = new Casilla();
				if(mat[i][j] == 'v') {
					c.vacia();
					
				}else if(mat[i][j] == 'b') {
					c.bola();
				}
				board.add(c);
				matriz[i][j] = c;
				c.setBackground(Color.LIGHT_GRAY);
				c.setVisible(true);
			}
		}
	}
	
	private void refresque() {
		this.revalidate();
	}

	private void centre() {
		Dimension d = tamanoPantalla();
		int esquinaX = (d.width - getSize().width)/2;
		int esquinaY = (d.height - getSize().height)/2;
		this.setLocation(esquinaX,esquinaY);
	}

	private Dimension tamanoPantalla(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public void prepareAcciones(){
		WindowAdapter cerrarVentana = new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				salir();
			}
		};
		
		ActionListener cerrarVentanaOpcion = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salirOpcion();				
			}
		};
		
		ActionListener salvarArchivo = new ActionListener() {
			public void actionPerformed(ActionEvent i) {
				salvarArchivo();
				
			}
		};
		
		ActionListener abrirArchivo = new ActionListener() {
			public void actionPerformed(ActionEvent i) {
				abrirArchivo();
				
			}
		};
		
		ActionListener cambiarColor = new ActionListener() {
			public void actionPerformed(ActionEvent i) {
				cambieColor();
				
			}
		};
		
		abrir.addActionListener(abrirArchivo);
		salir.addActionListener(cerrarVentanaOpcion);
		addWindowListener(cerrarVentana);
		salvar.addActionListener(salvarArchivo);
		color.addActionListener(cambiarColor);
	}
	
	private void cambieColor() {
		Color coloor = JColorChooser.showDialog(null, "Change color", colorcito);
		if(coloor != null && coloor != Color.GRAY) {
			colorcito = coloor;
			pinte();
		}
	}
	
	private void pinte() {
		for(int i = 0; i < largo; i++) {
			for(int j = 0; j < largo; j++) {
				matriz[i][j].changeColor(colorcito);
			}
		}
	}
	
	private void salir(){
		int respuesta = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
		if(respuesta == JOptionPane.YES_OPTION) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		
	}
	
	private void salirOpcion(){
		int respuesta = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
		if(respuesta == JOptionPane.YES_OPTION) {
			this.dispose();
			System.exit(0);
		}else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		
	}
	
	private void salvarArchivo() {
		JFileChooser file = new JFileChooser();
		file.setSelectedFile(new File("save.txt"));
		file.showSaveDialog(salvar);
		try{
			String link  =  file.getSelectedFile().getName();
			String files = link.replaceAll("[^a-zA-Z0-9.]"," ");
			String[] split = files.split(" ");
    		JOptionPane.showMessageDialog(null,"Funcion Salvar "+split[split.length-1],"En proceso",JOptionPane.ERROR_MESSAGE); 		
		}catch(Exception e){}
	}
	
	private void abrirArchivo() {
		JFileChooser file = new JFileChooser();
		file.setDialogTitle("Archivo a elegir");
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if(file.showOpenDialog(abrir) == JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "Elegiste abrir este archivo: " + file.getSelectedFile().getName());
		}
	}
	
	public static void main(String[] args){
		SenkuGUI senkuu = new SenkuGUI();
		senkuu.setVisible(true);
	}
	
}



