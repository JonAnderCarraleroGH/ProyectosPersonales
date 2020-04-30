import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ControladorBucleJuego {
	
	private PanelTablero panelTablero;
	private LabelCelda labelCelda;
	private MouseListener listener;
	private int puntosJ1;
	private int puntosJ2;

	public ControladorBucleJuego(PanelTablero tablero, LabelCelda labelCelda) {
		this.panelTablero = tablero;
		this.labelCelda = labelCelda;
		puntosJ1 = 3100;
		puntosJ2 = 3100;
		listener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaJuego ventanaJuego = (VentanaJuego) SwingUtilities.getRoot(labelCelda);
				if (panelTablero.getNumeroDeTurno() == 0 && ventanaJuego.getLabelBarcoSelecionado()!=null) {
					colocarBarcos( labelCelda);
				}
				if (panelTablero.getNumeroDeTurno() > 0){
					bucleJuego();
				}
				
			}

		};
		this.labelCelda.addMouseListener(listener);
	}


	private void bucleJuego() {
		ArrayList<LabelCelda> areaFuego = new ArrayList<LabelCelda>();
		areaFuego = panelTablero.getAreaFuego();
		//REVISAR
		panelTablero.playSound(PanelTablero.EXPLOSION);
		for (LabelCelda labelCelda : areaFuego) {
			labelCelda.setOpaque(true);
			if (labelCelda.getJugador() != LabelCelda.SIN_FUEGO && labelCelda.getJugador() != panelTablero.getTurnoDeJugador()) {
				labelCelda.setBackground(Color.black);
				labelCelda.setHighlight(true);
				labelCelda.removeMouseListener(labelCelda);
				labelCelda.removeMouseListener(listener);
			}else {
				labelCelda.setHighlight(true);
				labelCelda.setJugador(panelTablero.getTurnoDeJugador());
				if (panelTablero.getTurnoDeJugador() == 2) {
					labelCelda.setBackground(PanelTablero.COLORJ2);
				}else {
					labelCelda.setBackground(PanelTablero.COLORJ1);
				}
			}
		}
		if (panelTablero.getTurnoDeJugador() == 2) {
			gestionarFuegoABarcos(2, areaFuego);
			if (haGanado(2)) {
				JOptionPane.showMessageDialog(null, "El jugador 2 ha ganado");
			}else {
				panelTablero.setNumeroDeTurno((int) (panelTablero.getNumeroDeTurno()+1));
				panelTablero.setTurnoDeJugador(1);
				prepararTablero(1);
			}
			
		}else {
			gestionarFuegoABarcos(1, areaFuego);
			if (haGanado(1)) {
				JOptionPane.showMessageDialog(null, "El jugador 1 ha ganado");
			}else {
				panelTablero.setTurnoDeJugador(2);
				prepararTablero(2);
			}
			
		}
		
	}

	private boolean haGanado(int jugador) {
		VentanaJuego frame = (VentanaJuego) SwingUtilities.getRoot(panelTablero);
		JPanel panelJugador;
		if (jugador == 1) {
			panelJugador = frame.getPanelJugador2();
		}else {
			panelJugador = frame.getPanelJugador1();
		}
		
		for (Component c : panelJugador.getComponents()) {
			if (c instanceof LabelBarco) {
				if (((LabelBarco) c).getBarco().getVidasActuales() != 0) {
					return false;
				}
			}
		}
		return true;
	}


	private void prepararTablero(int jugador) {
		LabelCelda[][] tablero = panelTablero.getTablero();
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				LabelCelda labelCelda = tablero[i][j];
				if (jugador ==1 && (labelCelda.getBackground() == PanelTablero.COLORJ1 || labelCelda.getBackground() == PanelTablero.COLORJ1_HA_GOLPEADO || labelCelda.getBackground() == Color.black)) {
					labelCelda.setOpaque(true);
					labelCelda.setHighlight(true);
					panelTablero.repaint();
				}
				if (jugador ==1 && (labelCelda.getBackground() == PanelTablero.COLORJ2 || labelCelda.getBackground() == PanelTablero.COLORJ2_HA_GOLPEADO || labelCelda.getBackground() == Color.black)) {
					labelCelda.setOpaque(false);
					labelCelda.setHighlight(false);
					if (labelCelda.getBackground() == Color.black) {
						labelCelda.setOpaque(true);
						labelCelda.setHighlight(true);
						panelTablero.repaint();
					}
				}
				if (jugador ==2 && (labelCelda.getBackground() == PanelTablero.COLORJ2 || labelCelda.getBackground() == PanelTablero.COLORJ2_HA_GOLPEADO || labelCelda.getBackground() == Color.black)) {
					labelCelda.setOpaque(true);
					labelCelda.setHighlight(true);
					panelTablero.repaint();
				}
				if (jugador ==2 && (labelCelda.getBackground() == PanelTablero.COLORJ1 || labelCelda.getBackground() == PanelTablero.COLORJ1_HA_GOLPEADO || labelCelda.getBackground() == Color.black)) {
					labelCelda.setOpaque(false);
					labelCelda.setHighlight(false);
					if (labelCelda.getBackground() == Color.black) {
						labelCelda.setOpaque(true);
						labelCelda.setHighlight(true);
						panelTablero.repaint();
					}
				}
			}
		}
		
	}


	private void gestionarFuegoABarcos(int jugador, ArrayList<LabelCelda> areaFuego) {
		VentanaJuego frame = (VentanaJuego) SwingUtilities.getRoot(panelTablero);
		JPanel panelJugador;
		if (jugador == 1) {
			panelJugador = frame.getPanelJugador2();
		}else {
			panelJugador = frame.getPanelJugador1();
		}
		for (LabelCelda labelCelda : areaFuego) {
			for (Component c : panelJugador.getComponents()) {
				if (c instanceof LabelBarco) {
					Barco barco = ((LabelBarco) c).getBarco();
					LabelCelda[] posicionBarco = barco.getPosicion();
					int i = 0;
					for (LabelCelda celdaBarco : posicionBarco) {
						if (celdaBarco == labelCelda) {
							// TODO play sonido
							
							//bajar vida a barco y cambiar texto a label
							if (barco.getVidasActuales() > 0 && barco.getPosicionesBombardeadas()[i] != 1) { //Controla si el barco sigue "activo" y si la celda ya ha sido golpeada
								if ((jugador == 2 && labelCelda.getBackground()!=PanelTablero.COLORJ2_HA_GOLPEADO) || (jugador == 1 && labelCelda.getBackground()!=PanelTablero.COLORJ1_HA_GOLPEADO)) {
									barco.setVidasActuales(barco.getVidasActuales()-1);
									((LabelBarco) c).startTimer();
									JOptionPane.showMessageDialog(null, "El jugador "+ jugador + "ha acertado en "+ barco.getNombre()+ "enemig@");
									((LabelBarco) c).setText(barco.getNombre() + barco.getVidasActuales());
									barco.setPosicionBombardeada(i);
								}
								//SET FONDO
								if (jugador == 2) {
									labelCelda.setBackground(PanelTablero.COLORJ2_HA_GOLPEADO);
									puntosJ2-=100;
									frame.getLblPuntuacionJ2().setText("Puntuacion: "+puntosJ2);
								}else {
									puntosJ1-=100;
									labelCelda.setBackground(PanelTablero.COLORJ1_HA_GOLPEADO);
									frame.getLblPuntuacionJ1().setText("Puntuacion: "+puntosJ1);
								}
							}
							
							
						}
						i++;
					}
				}
				
			}
		}
	}


	public void colocarBarcos(LabelCelda labelCelda) {
		VentanaJuego ventanaJuego = (VentanaJuego) SwingUtilities.getRoot(labelCelda);
		Barco barcoSeleccionado = ventanaJuego.getLabelBarcoSelecionado().getBarco();
		int tamañoBarco = panelTablero.getTamañoCursor();
		LabelCelda [] posicionBarco = new LabelCelda[tamañoBarco];
		//Bloque para inicializar el subarray para la posicion del barco
		try {
			for (int i = 0; i < tamañoBarco; i++) {
				if (panelTablero.getOrientacion() == PanelTablero.HORIZONTAL) {
					posicionBarco[i] = panelTablero.getTablero()[panelTablero.getCeldaSeleccionada().getPosicionI()][panelTablero.getCeldaSeleccionada().getPosicionJ()+i];
					//no dejar que vuelva a cambiar no resetear todas a mala idea
				}

				if (panelTablero.getOrientacion() == PanelTablero.VERTICAL) {
					posicionBarco[i] = panelTablero.getTablero()[panelTablero.getCeldaSeleccionada().getPosicionI()+i][panelTablero.getCeldaSeleccionada().getPosicionJ()];
					//no dejar que vuelva a cambiar
				}
			}
		
		//if (panelTablero.getNumeroDeTurno() == 0 && ventanaJuego.getLabelBarcoSelecionado()!=null) { // si es turno de colocar barcos y hay barco seleccionado
			if (esCeldaValida(ventanaJuego, tamañoBarco, posicionBarco)) {
				int respuesta = JOptionPane.showConfirmDialog(panelTablero, "¿Desea colocar el barco aqui?");
				if (respuesta == JOptionPane.YES_OPTION) {
					panelTablero.playSound(PanelTablero.BARCO_COLOCADO);
					for (int i = 0; i < tamañoBarco; i++) {
						if (panelTablero.getOrientacion() == PanelTablero.HORIZONTAL) {
							panelTablero.getTablero()[panelTablero.getCeldaSeleccionada().getPosicionI()][panelTablero.getCeldaSeleccionada().getPosicionJ()+i].setBackground(Color.red);
							panelTablero.getTablero()[panelTablero.getCeldaSeleccionada().getPosicionI()][panelTablero.getCeldaSeleccionada().getPosicionJ()+i].setHighlight(true);
							//no dejar que vuelva a cambiar no resetear todas a mala idea
						}

						if (panelTablero.getOrientacion() == PanelTablero.VERTICAL) {
							panelTablero.getTablero()[panelTablero.getCeldaSeleccionada().getPosicionI()+i][panelTablero.getCeldaSeleccionada().getPosicionJ()].setBackground(Color.red);
							panelTablero.getTablero()[panelTablero.getCeldaSeleccionada().getPosicionI()+i][panelTablero.getCeldaSeleccionada().getPosicionJ()].setHighlight(true);
							//no dejar que vuelva a cambiar
						}
					}
					barcoSeleccionado.setPosicion(posicionBarco);
					ventanaJuego.getLabelBarcoSelecionado().setBackground(Color.black);
					ventanaJuego.getLabelBarcoSelecionado().setEstado(false);
					ventanaJuego.setLabelBarcoSelecionado(null);
					if (barcosColocados(ventanaJuego)) {
						if (panelTablero.getTurnoDeJugador() == 1) {
							JOptionPane.showMessageDialog(ventanaJuego, "El jugador 1 ha acabado de poner sus barcos, que el jugador1 se aleje del ordenador y vuelva el jugador 2");
							panelTablero.setTurnoDeJugador(2);
							ventanaJuego.switchEstadoLabelsBarco(2, true);
						}else {
							JOptionPane.showMessageDialog(ventanaJuego, "El jugador 2 ha acabado de colocar sus barcos, el juego va a empezar, que los dos jugadores se coloquen frente al ordenador");
							panelTablero.setNumeroDeTurno((int) (panelTablero.getNumeroDeTurno()+1));
							panelTablero.setTurnoDeJugador(1);
						}
						panelTablero.resetearHighlights();
						panelTablero.resetearLabels();
					}
				}
			}else {
				JOptionPane.showMessageDialog(ventanaJuego, "Los barcos no pueden ni cruzarse ni solaparse");
			}
		//}
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(ventanaJuego, "Los tramposos no son bienvenidos, elige una posición en la que entre el barco");
		}
	}

	private boolean esCeldaValida(VentanaJuego ventanaJuego, int tamañoBarco, LabelCelda[] posicionBarco) {
		int jugador = panelTablero.getTurnoDeJugador();
		JPanel panelDeJugador;
		if (jugador == 1) {
			panelDeJugador = ventanaJuego.getPanelJugador1();
		}else {
			panelDeJugador = ventanaJuego.getPanelJugador2();
		}
		for (Component c : panelDeJugador.getComponents()) {
			if (c instanceof LabelBarco) { 
				Barco barco = ((LabelBarco) c).getBarco();
				if (barco.getPosicion() != null) {
					for (int i = 0; i < barco.getPosicion().length; i++) {
						for (int j = 0; j < posicionBarco.length; j++) {
							if (barco.getPosicion()[i] == posicionBarco[j] && (barco.getJugador() == panelTablero.getTurnoDeJugador())) {
								return false;
							}
						}
						
					}
				}
				
			}
		}
		return true;//cambiar

	}

	private boolean barcosColocados(VentanaJuego ventanaJuego) {
		int jugador = panelTablero.getTurnoDeJugador();
		JPanel panelDeJugador;
		if (jugador == 1) {
			panelDeJugador = ventanaJuego.getPanelJugador1();
		}else {
			panelDeJugador = ventanaJuego.getPanelJugador2();
		}
		for (Component c : panelDeJugador.getComponents()) {
			if (c instanceof LabelBarco) { 
				if (((LabelBarco) c).getBarco().getPosicion() == null) {
					return false;
				}
			}
		}
		return true;//cambiar

	}

}
