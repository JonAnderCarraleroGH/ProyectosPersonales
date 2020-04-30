import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class PanelTablero extends JPanel {
	public static final String NOMBRE_CANCIÓN = "cancionFondo.wav";
	public static final String BARCO_COLOCADO = "barcoColocado.wav";
	public static final String EXPLOSION = "explosion.wav";
	public static final String SIRENA_BOMBA = "sirena.wav";
	public static Color COLORJ1 = new Color(199, 255, 202);
	public static Color COLORJ1_HA_GOLPEADO = new Color(0, 255, 14);
	public static Color COLORJ2 = new Color(252, 78, 78);
	public static Color COLORJ2_HA_GOLPEADO = new Color(117, 38, 38);
	public static final int FUEGO_BOMBA = 1;
	public static final int FUEGO_SATURACION = 2;
	public static final int FUEGO_LINEA = 3;
	public static final int FUEGO_NORMAL = 4;
	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 2;
	private LabelCelda[][] tablero;
	private int orientacion; //cambiar
	private LabelCelda celdaSeleccionada;
	private int tamañoCursor; //cambiar
	private ControladorPanelTablero controladorPanelTablero;
	private double numeroDeTurno;
	private int turnoDeJugador;
	private boolean turnoFuegoDeLinea;
	private boolean turnoBomba;
	private boolean turnoFuegoDeSaturacion;
	private ArrayList<LabelCelda> areaFuego = new ArrayList<LabelCelda>();


	public PanelTablero () {
		super();
		numeroDeTurno = 0;
		turnoDeJugador = 1;
		orientacion = HORIZONTAL;
		tablero = new LabelCelda[50][50];
		setOpaque(false);
		setBounds(130, 66, 788, 632);
		setLayout(new GridLayout(50, 50, 0, 0));
		iniciarTablero();
		controladorPanelTablero = new ControladorPanelTablero(this);
		setFocusable(true);
		turnoFuegoDeLinea = (numeroDeTurno%7 == 0 && numeroDeTurno!=7);
		turnoBomba = (numeroDeTurno%5 == 0 && numeroDeTurno!=5);
		turnoFuegoDeSaturacion = (numeroDeTurno%13 == 0 && numeroDeTurno!=1);
		playSound(NOMBRE_CANCIÓN);
	}

	private void iniciarTablero() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				LabelCelda label = new LabelCelda(this,i,j);
				label.setBorder(new LineBorder(Color.CYAN));
				tablero[i][j] = label;
				add(label);
			}
		}

	}

	public void resetearLabels() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (!tablero[i][j].isHighlight() && tablero[i][j].getJugador()==LabelCelda.SIN_FUEGO) {
					tablero[i][j].setOpaque(false);
					tablero[i][j].setBackground(Color.cyan);
				}

			}
		}
		//JFrame frame = (JFrame) SwingUtilities.getRoot(panelTablero);
		//frame.repaint();
	}

	public void resetearHighlights() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j].setHighlight(false);

			}
		}
	}

	public  synchronized void playSound(String nombre) {
		new Thread(new Runnable() {
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(getClass().getClassLoader().getResource(nombre).getFile()).toURI().toURL());
					clip.open(inputStream);
					clip.start(); 
					if (nombre.equals(NOMBRE_CANCIÓN)) {
						clip.loop(Clip.LOOP_CONTINUOUSLY);
						FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
						double gain = 0.25;   
						float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
						gainControl.setValue(dB);
					}
					if (nombre.equals(SIRENA_BOMBA)) {
						long start = System.currentTimeMillis();
						while (clip.isRunning()) {
							System.out.println("empieza");
							if (System.currentTimeMillis() == start+5) {
								System.out.println("if true");
								clip.stop();
							}
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public LabelCelda[][] getTablero() {
		return tablero;
	}

	public void setTablero(LabelCelda[][] tablero) {
		this.tablero = tablero;
	}

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(int horientacion) {
		this.orientacion = horientacion;
	}

	public int getTamañoCursor() {
		return tamañoCursor;
	}

	public void setTamañoCursor(int tamañoCursor) {
		this.tamañoCursor = tamañoCursor;
	}

	public LabelCelda getCeldaSeleccionada() {
		return celdaSeleccionada;
	}

	public void setCeldaSeleccionada(LabelCelda celdaSeleccionada) {
		this.celdaSeleccionada = celdaSeleccionada;
	}

	public ControladorPanelTablero getControladorPanelTablero() {
		return controladorPanelTablero;
	}

	public void setControladorPanelTablero(ControladorPanelTablero controladorPanelTablero) {
		this.controladorPanelTablero = controladorPanelTablero;
	}

	public double getNumeroDeTurno() {
		return numeroDeTurno;
	}

	public void setNumeroDeTurno(int numeroDeTurno) {
		this.numeroDeTurno = numeroDeTurno;
		turnoFuegoDeLinea = (numeroDeTurno%7 == 0 && numeroDeTurno!=7);
		turnoBomba = (numeroDeTurno%5 == 0 && numeroDeTurno!=5);
		turnoFuegoDeSaturacion = (numeroDeTurno%13 == 0 && numeroDeTurno!=1);
		if (isTurnoBomba() || isTurnoFuegoDeLinea() || isTurnoFuegoDeSaturacion()) {
			playSound(PanelTablero.SIRENA_BOMBA);
			JOptionPane.showMessageDialog(this, "Turno de fuego especial,preparaos!");
		}
		if (numeroDeTurno>0) {
			
		}
	}

	public int getTurnoDeJugador() {
		return turnoDeJugador;
	}

	public void setTurnoDeJugador(int turnoDeJugador) {
		this.turnoDeJugador = turnoDeJugador;
		VentanaJuego frame = (VentanaJuego) SwingUtilities.getRoot(PanelTablero.this);
		frame.getLblTurnoDelJugador().setText("TURNO DEL JUGADOR "+turnoDeJugador);
		if (turnoDeJugador == 1) {
			frame.getLblTurnoDelJugador().setForeground(COLORJ1);
		}else {
			frame.getLblTurnoDelJugador().setForeground(COLORJ2);
		}
	}

	public boolean isTurnoFuegoDeLinea() {
		return turnoFuegoDeLinea;
	}

	public void setTurnoFuegoDeLinea(boolean turnoFuegoDeLinea) {
		this.turnoFuegoDeLinea = turnoFuegoDeLinea;
	}

	public boolean isTurnoBomba() {
		System.out.println(numeroDeTurno);
		System.out.println(turnoBomba);
		return turnoBomba;
	}

	public void setTurnoBomba(boolean turnoBomba) {
		this.turnoBomba = turnoBomba;
	}

	public boolean isTurnoFuegoDeSaturacion() {
		return turnoFuegoDeSaturacion;
	}

	public void setTurnoFuegoDeSaturacion(boolean turnoFuegoDeSaturacion) {
		this.turnoFuegoDeSaturacion = turnoFuegoDeSaturacion;
	}

	public ArrayList<LabelCelda> getAreaFuego() {
		return areaFuego;
	}

	public void setAreaFuego(ArrayList<LabelCelda> areaFuego) {
		this.areaFuego = areaFuego;
	}
}
