import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaJuego extends JFrame {

	private JPanel contentPane;
	private PanelTablero panelTablero;
	private LabelBoton lblSalir;
	private JPanel panelMenu;
	private Barco portavionesJ1;
	private Barco fragataJ1;
	private Barco acorazadoJ1;
	private Barco canoneraJ1;
	private Barco buqueInsigniaJ1;
	private Barco cruceroJ1;
	private Barco galeonJ1;
	private Barco portavionesJ2;
	private Barco fragataJ2;
	private Barco acorazadoJ2;
	private Barco canoneraJ2;
	private Barco buqueInsigniaJ2;
	private Barco cruceroJ2;
	private Barco galeonJ2;
	private JPanel panelJugador1;
	private JLabel lblJugador;
	private LabelBarco lblPortaaviones;
	private LabelBarco lblFragata;
	private LabelBarco lblAcorazado;
	private LabelBarco lblCañonera;
	private LabelBarco lblBuqueInsignia;
	private LabelBarco lblGaleon;
	private LabelBarco lblCrucero;
	private JPanel panelJugador2;
	private JLabel lblJugador2;
	private LabelBarco lblBuqueInsigniaJ2;
	private LabelBarco lblAcorazadoJ2;
	private LabelBarco lblPortavionesJ2;
	private LabelBarco lblFragataJ2;
	private LabelBarco lblGaleonJ2;
	private LabelBarco lblCanoneraJ2;
	private LabelBarco lblCruceroJ2;
	private ComponentMover cm;
	private LabelBarco labelBarcoSelecionado;
	private JLabel lblTurnoDelJugador;
	private JLabel lblPresionaEspacioPara;
	private JLabel lblPuntuacionJ1;
	private JLabel lblPuntuacionJ2;

	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VentanaJuego frame = new VentanaJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaJuego() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 1035, 753);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		java.net.URL urlImagen=getClass().getResource("gradienteAzul.png");
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(urlImagen).getImage().getScaledInstance(1035, 753, Image.SCALE_SMOOTH));
		
		lblPuntuacionJ2 = new JLabel("Puntuación");
		lblPuntuacionJ2.setBounds(926, 45, 130, 14);
		contentPane.add(lblPuntuacionJ2);
		
		lblPuntuacionJ1 = new JLabel("Puntuación");
		lblPuntuacionJ1.setBounds(10, 45, 140, 14);
		contentPane.add(lblPuntuacionJ1);
		
		lblPresionaEspacioPara = new JLabel("Presiona espacio para cambiar la orientaci\u00F3n");
		lblPresionaEspacioPara.setBounds(406, 697, 275, 14);
		contentPane.add(lblPresionaEspacioPara);
		
		lblTurnoDelJugador = new JLabel("TURNO DEL JUGADOR:");
		lblTurnoDelJugador.setFont(new Font("Stencil", Font.BOLD, 23));
		lblTurnoDelJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurnoDelJugador.setBounds(374, 28, 297, 39);
		contentPane.add(lblTurnoDelJugador);
		
		lblSalir = new LabelBoton(LabelBoton.LABEL_SALIR, "SALIR", new Rectangle(210, 247, 249, 34));
		lblSalir.setLocation(776, 709);
		contentPane.add(lblSalir);
		
		panelTablero = new PanelTablero();
		panelTablero.setBounds(130, 66, 788, 632);
		contentPane.add(panelTablero);
		
		
		panelMenu = new JPanel();
		panelMenu.setOpaque(false);
		panelMenu.setBounds(0, 0, 1035, 28);
		contentPane.add(panelMenu);
		
		
		cm = new ComponentMover(this, panelMenu);
		
		//Bloque para iniciar los paneles laterales
		panelJugador1 = new JPanel();
		//panelJugador1.setBorder(new LineBorder(Color.CYAN));
		panelJugador1.setBounds(10, 66, 97, 632);
		contentPane.add(panelJugador1);
		panelJugador1.setLayout(new GridLayout(8, 0, 0, 0));
		

		lblJugador = new JLabel("JUGADOR 1");
		lblJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugador.setOpaque(true);
		lblJugador.setBackground(Color.black);
		lblJugador.setForeground(Color.white);
		panelJugador1.add(lblJugador);
		
		lblBuqueInsignia = new LabelBarco();
		lblBuqueInsignia.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador1.add(lblBuqueInsignia);
		
		lblAcorazado = new LabelBarco();
		lblAcorazado.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador1.add(lblAcorazado);
		
		lblPortaaviones = new LabelBarco();
		lblPortaaviones.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador1.add(lblPortaaviones);
		
		lblFragata = new LabelBarco();
		lblFragata.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador1.add(lblFragata);
		
		lblGaleon = new LabelBarco();
		lblGaleon.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador1.add(lblGaleon);
		
		lblCañonera = new LabelBarco();
		lblCañonera.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador1.add(lblCañonera);
		
		lblCrucero = new LabelBarco();
		lblCrucero.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador1.add(lblCrucero);
		
		panelJugador2 = new JPanel();
		panelJugador2.setBounds(928, 66, 97, 632);
		contentPane.add(panelJugador2);
		panelJugador2.setLayout(new GridLayout(8, 0, 0, 0));
		
		lblJugador2 = new JLabel("JUGADOR 2");
		lblJugador2.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugador2.setOpaque(true);
		lblJugador2.setBackground(Color.black);
		lblJugador2.setForeground(Color.white);
		panelJugador2.add(lblJugador2);
		
		lblBuqueInsigniaJ2 = new LabelBarco();
		lblBuqueInsigniaJ2.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador2.add(lblBuqueInsigniaJ2);
		
		lblAcorazadoJ2 = new LabelBarco();
		lblAcorazadoJ2.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador2.add(lblAcorazadoJ2);
		
		lblPortavionesJ2 = new LabelBarco();
		lblPortavionesJ2.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador2.add(lblPortavionesJ2);
		
		lblFragataJ2 = new LabelBarco();
		lblFragataJ2.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador2.add(lblFragataJ2);
		
		lblGaleonJ2 = new LabelBarco();
		lblGaleonJ2.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador2.add(lblGaleonJ2);
		
		lblCanoneraJ2 = new LabelBarco();
		lblCanoneraJ2.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador2.add(lblCanoneraJ2);
		
		lblCruceroJ2 = new LabelBarco();
		lblCruceroJ2.setHorizontalAlignment(SwingConstants.CENTER);
		panelJugador2.add(lblCruceroJ2);
		
		lblPuntuacionJ1.setText("Puntuación = 3100");
		lblPuntuacionJ2.setText("Puntuación = 3100");
		
		JLabel lblFondo = new JLabel();
		//lblNewLabel.setBackground(Color.RED);
		lblFondo.setOpaque(true);
		lblFondo.setBounds(0, -11, 1045, 776);
		lblFondo.setIcon(imageIcon);
		contentPane.add(lblFondo);
		crearYAsignarBarcos();
		switchEstadoLabelsBarco(2, false);
		// TODO cambiar orientacion con barra espaciadora
	}

	private void crearYAsignarBarcos() {
		//Crecion de Barcos
		buqueInsigniaJ1 = new Barco(Barco.VIDAS_BUQUE_INSIGNIA, "Buque Insignia",1);
		buqueInsigniaJ2 = new Barco(Barco.VIDAS_BUQUE_INSIGNIA, "Buque Insignia",2);
		acorazadoJ1 = new Barco(Barco.VIDAS_ACORAZADO, "Acorazado",1);
		acorazadoJ2 = new Barco(Barco.VIDAS_ACORAZADO, "Acorazado",2);
		portavionesJ1 = new Barco(Barco.VIDAS_PORTAVIONES, "Portaviones",1);
		portavionesJ2 = new Barco(Barco.VIDAS_PORTAVIONES, "Portaviones",2);
		fragataJ1 = new Barco(Barco.VIDAS_FRAGATA, "Fragata",1);
		fragataJ2 = new Barco(Barco.VIDAS_FRAGATA, "Fragata",2);
		galeonJ1 = new Barco(Barco.VIDAS_GALEON, "Galeon",1);
		galeonJ2 = new Barco(Barco.VIDAS_GALEON, "Galeon",2);
		canoneraJ1 = new Barco(Barco.VIDAS_CAÑONERA, "Ca�onera",1);
		canoneraJ2 = new Barco(Barco.VIDAS_CAÑONERA, "Ca�onera",2);
		cruceroJ1 = new Barco(Barco.VIDAS_CRUCERO, "Crucero",1);
		cruceroJ2 = new Barco(Barco.VIDAS_CRUCERO, "Crucero",2);
		lblBuqueInsignia.setBarco(buqueInsigniaJ1);
		//Asignacion de barcos a labels
		lblBuqueInsigniaJ2.setBarco(buqueInsigniaJ2);
		lblAcorazado.setBarco(acorazadoJ1);
		lblAcorazadoJ2.setBarco(acorazadoJ2);
		lblPortaaviones.setBarco(portavionesJ1);
		lblPortavionesJ2.setBarco(portavionesJ2);
		lblFragata.setBarco(fragataJ1);
		lblFragataJ2.setBarco(fragataJ2);
		lblGaleon.setBarco(galeonJ1);
		lblGaleonJ2.setBarco(galeonJ2);
		lblCañonera.setBarco(canoneraJ1);
		lblCanoneraJ2.setBarco(canoneraJ2);
		lblCrucero.setBarco(cruceroJ1);
		lblCruceroJ2.setBarco(cruceroJ2);
		
	}
	
	public void switchEstadoLabelsBarco(int jugador, boolean estado) {
		if (jugador == 1) {
			for (Component c : panelJugador1.getComponents()) {
			    if (c instanceof LabelBarco) { 
			       ((LabelBarco) c).setEstado(estado);
			       
			    }
			}
		}
		
		if (jugador == 2) {
			for (Component c : panelJugador2.getComponents()) {
			    if (c instanceof LabelBarco) { 
			    	 ((LabelBarco) c).setEstado(estado);
			    }
			}
		}
		
	}
	

	public void setLabelBarcoSelecionado(LabelBarco labelBarcoSelecionado) {
		this.labelBarcoSelecionado = labelBarcoSelecionado;
		if (labelBarcoSelecionado == null) {
			panelTablero.setTamañoCursor(1);
			panelTablero.setOrientacion(PanelTablero.HORIZONTAL);
		}
		else {
			panelTablero.setTamañoCursor(labelBarcoSelecionado.getBarco().getNumeroMáximoDeVidas());
			panelTablero.setOrientacion(PanelTablero.HORIZONTAL);
		}
		
	}

	public PanelTablero getPanelTablero() {
		return panelTablero;
	}

	public void setPanelTablero(PanelTablero panelTablero) {
		this.panelTablero = panelTablero;
	}

	public LabelBoton getLblSalir() {
		return lblSalir;
	}

	public void setLblSalir(LabelBoton lblSalir) {
		this.lblSalir = lblSalir;
	}

	public JPanel getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(JPanel panelMenu) {
		this.panelMenu = panelMenu;
	}

	public Barco getPortavionesJ1() {
		return portavionesJ1;
	}

	public void setPortavionesJ1(Barco portavionesJ1) {
		this.portavionesJ1 = portavionesJ1;
	}

	public Barco getFragataJ1() {
		return fragataJ1;
	}

	public void setFragataJ1(Barco fragataJ1) {
		this.fragataJ1 = fragataJ1;
	}

	public Barco getAcorazadoJ1() {
		return acorazadoJ1;
	}

	public void setAcorazadoJ1(Barco acorazadoJ1) {
		this.acorazadoJ1 = acorazadoJ1;
	}

	public Barco getCanoneraJ1() {
		return canoneraJ1;
	}

	public void setCanoneraJ1(Barco canoneraJ1) {
		this.canoneraJ1 = canoneraJ1;
	}

	public Barco getBuqueInsigniaJ1() {
		return buqueInsigniaJ1;
	}

	public void setBuqueInsigniaJ1(Barco buqueInsigniaJ1) {
		this.buqueInsigniaJ1 = buqueInsigniaJ1;
	}

	public Barco getCruceroJ1() {
		return cruceroJ1;
	}

	public void setCruceroJ1(Barco cruceroJ1) {
		this.cruceroJ1 = cruceroJ1;
	}

	public Barco getPortavionesJ2() {
		return portavionesJ2;
	}

	public void setPortavionesJ2(Barco portavionesJ2) {
		this.portavionesJ2 = portavionesJ2;
	}

	public Barco getFragataJ2() {
		return fragataJ2;
	}

	public void setFragataJ2(Barco fragataJ2) {
		this.fragataJ2 = fragataJ2;
	}

	public Barco getAcorazadoJ2() {
		return acorazadoJ2;
	}

	public void setAcorazadoJ2(Barco acorazadoJ2) {
		this.acorazadoJ2 = acorazadoJ2;
	}

	public Barco getCanoneraJ2() {
		return canoneraJ2;
	}

	public void setCanoneraJ2(Barco canoneraJ2) {
		this.canoneraJ2 = canoneraJ2;
	}

	public Barco getBuqueInsigniaJ2() {
		return buqueInsigniaJ2;
	}

	public void setBuqueInsigniaJ2(Barco buqueInsigniaJ2) {
		this.buqueInsigniaJ2 = buqueInsigniaJ2;
	}

	public Barco getCruceroJ2() {
		return cruceroJ2;
	}

	public void setCruceroJ2(Barco cruceroJ2) {
		this.cruceroJ2 = cruceroJ2;
	}

	public JPanel getPanelJugador1() {
		return panelJugador1;
	}

	public void setPanelJugador1(JPanel panelJugador1) {
		this.panelJugador1 = panelJugador1;
	}

	public JLabel getLblJugador() {
		return lblJugador;
	}

	public void setLblJugador(JLabel lblJugador) {
		this.lblJugador = lblJugador;
	}

	public LabelBarco getLblPortaaviones() {
		return lblPortaaviones;
	}

	public void setLblPortaaviones(LabelBarco lblPortaaviones) {
		this.lblPortaaviones = lblPortaaviones;
	}

	public LabelBarco getLblFragata() {
		return lblFragata;
	}

	public void setLblFragata(LabelBarco lblFragata) {
		this.lblFragata = lblFragata;
	}

	public LabelBarco getLblAcorazado() {
		return lblAcorazado;
	}

	public void setLblAcorazado(LabelBarco lblAcorazado) {
		this.lblAcorazado = lblAcorazado;
	}

	public LabelBarco getLblCañonera() {
		return lblCañonera;
	}

	public void setLblCañonera(LabelBarco lblCañonera) {
		this.lblCañonera = lblCañonera;
	}

	public LabelBarco getLblBuqueInsignia() {
		return lblBuqueInsignia;
	}

	public void setLblBuqueInsignia(LabelBarco lblBuqueInsignia) {
		this.lblBuqueInsignia = lblBuqueInsignia;
	}

	public LabelBarco getLblGaleon() {
		return lblGaleon;
	}

	public void setLblGaleon(LabelBarco lblGaleon) {
		this.lblGaleon = lblGaleon;
	}

	public LabelBarco getLblCrucero() {
		return lblCrucero;
	}

	public void setLblCrucero(LabelBarco lblCrucero) {
		this.lblCrucero = lblCrucero;
	}

	public JPanel getPanelJugador2() {
		return panelJugador2;
	}

	public void setPanelJugador2(JPanel panelJugador2) {
		this.panelJugador2 = panelJugador2;
	}

	public JLabel getLblJugador2() {
		return lblJugador2;
	}

	public void setLblJugador2(JLabel lblJugador2) {
		this.lblJugador2 = lblJugador2;
	}

	public LabelBarco getLblBuqueInsigniaJ2() {
		return lblBuqueInsigniaJ2;
	}

	public void setLblBuqueInsigniaJ2(LabelBarco lblBuqueInsigniaJ2) {
		this.lblBuqueInsigniaJ2 = lblBuqueInsigniaJ2;
	}

	public LabelBarco getLblAcorazadoJ2() {
		return lblAcorazadoJ2;
	}

	public void setLblAcorazadoJ2(LabelBarco lblAcorazadoJ2) {
		this.lblAcorazadoJ2 = lblAcorazadoJ2;
	}

	public LabelBarco getLblPortavionesJ2() {
		return lblPortavionesJ2;
	}

	public void setLblPortavionesJ2(LabelBarco lblPortavionesJ2) {
		this.lblPortavionesJ2 = lblPortavionesJ2;
	}

	public LabelBarco getLblFragataJ2() {
		return lblFragataJ2;
	}

	public void setLblFragataJ2(LabelBarco lblFragataJ2) {
		this.lblFragataJ2 = lblFragataJ2;
	}

	public LabelBarco getLblGaleonJ2() {
		return lblGaleonJ2;
	}

	public void setLblGaleonJ2(LabelBarco lblGaleonJ2) {
		this.lblGaleonJ2 = lblGaleonJ2;
	}

	public LabelBarco getLblCanoneraJ2() {
		return lblCanoneraJ2;
	}

	public void setLblCanoneraJ2(LabelBarco lblCanoneraJ2) {
		this.lblCanoneraJ2 = lblCanoneraJ2;
	}

	public LabelBarco getLblCruceroJ2() {
		return lblCruceroJ2;
	}

	public void setLblCruceroJ2(LabelBarco lblCruceroJ2) {
		this.lblCruceroJ2 = lblCruceroJ2;
	}

	public ComponentMover getCm() {
		return cm;
	}

	public void setCm(ComponentMover cm) {
		this.cm = cm;
	}

	public LabelBarco getLabelBarcoSelecionado() {
		return labelBarcoSelecionado;
	}

	public JLabel getLblTurnoDelJugador() {
		return lblTurnoDelJugador;
	}

	public void setLblTurnoDelJugador(JLabel lblTurnoDelJugador) {
		this.lblTurnoDelJugador = lblTurnoDelJugador;
	}

	public JLabel getLblPuntuacionJ1() {
		return lblPuntuacionJ1;
	}

	public void setLblPuntuacionJ1(JLabel lblPuntuacionJ1) {
		this.lblPuntuacionJ1 = lblPuntuacionJ1;
	}

	public JLabel getLblPuntuacionJ2() {
		return lblPuntuacionJ2;
	}

	public void setLblPuntuacionJ2(JLabel lblPuntuacionJ2) {
		this.lblPuntuacionJ2 = lblPuntuacionJ2;
	}
}
