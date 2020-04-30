import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class LabelCelda extends JLabel implements MouseListener {
	public static final int FUEGO_JUGADOR1 = 1;
	public static final int FUEGO_JUGADOR0 = 0;
	public static final int SIN_FUEGO = 0;
	private Rectangle colision;
	private boolean isHovered;
	private int posicionI;
	private int posicionJ;
	private PanelTablero panelTablero;
	private boolean esCeldaValida;
	private ControladorBucleJuego controladorBucleJuego;
	private boolean highlight;
	int jugador;

	public LabelCelda(PanelTablero panelTablero, int i, int j) {
		super();
		jugador = SIN_FUEGO;
		posicionI = i;
		highlight = false;
		posicionJ = j;
		this.panelTablero = panelTablero;
		colision = getBounds();
		setBackground(Color.CYAN);
		addMouseListener(this);
		controladorBucleJuego = new ControladorBucleJuego(panelTablero, this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		pintarCeldas();
		//System.out.println(posicionI + " " + posicionJ);
	}

	public void pintarCeldas() {
		LabelCelda.this.setOpacidad(true);
		isHovered = true;
		LabelCelda.this.setBackground(LabelCelda.this.getBackground());
		JFrame frame = (JFrame) SwingUtilities.getRoot(LabelCelda.this);
		panelTablero.setCeldaSeleccionada(this);
		if (panelTablero.getNumeroDeTurno() == 0) {
			pintarCeldasBarco(true);
		}
		else {
			pintarCeldasDisparo(true);
		}
		frame.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		LabelCelda.this.setOpacidad(false);
		isHovered = false;
		JFrame frame = (JFrame) SwingUtilities.getRoot(LabelCelda.this);
		if (panelTablero.getNumeroDeTurno() == 0) {
			pintarCeldasBarco(false);
		}else {
			pintarCeldasDisparo(false);
		}

		frame.repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void pintarCeldasDisparo(boolean b) {
		// determinar el tipo de turno y pintar el disparo en funcion de ello
		//BLOQUE DE FUEGO DE LINEA
		ArrayList<LabelCelda> areaFuego = new ArrayList<LabelCelda>();
		if (panelTablero.isTurnoFuegoDeLinea()) { 
			panelTablero.setTamañoCursor(PanelTablero.FUEGO_LINEA);
			/*if (panelTablero.getOrientacion() == PanelTablero.HORIZONTAL ) {
				for (int i = 0; i < panelTablero.getTablero().length; i++) {
					panelTablero.getTablero()[posicionI][i].setOpacidad(b);
					areaFuego.add(panelTablero.getTablero()[posicionI][i]);
				}
			}*/
			//if (panelTablero.getOrientacion() == PanelTablero.VERTICAL ) {
				for (int i = 0; i < panelTablero.getTablero().length; i++) {
					panelTablero.getTablero()[i][posicionJ].setOpacidad(b);
					areaFuego.add(panelTablero.getTablero()[i][posicionJ]);
				}
			//}
			
		//BLOQUE DE FUEGO BOMBA	
		}else if (panelTablero.isTurnoBomba()) { 
			panelTablero.setTamañoCursor(PanelTablero.FUEGO_BOMBA);
			if (posicionI>1&&posicionI<48&&posicionJ>1&&posicionJ<48) {
				panelTablero.getTablero()[posicionI][posicionJ-1].setOpacidad(b);
				panelTablero.getTablero()[posicionI][posicionJ+1].setOpacidad(b);
				panelTablero.getTablero()[posicionI][posicionJ-2].setOpacidad(b);
				panelTablero.getTablero()[posicionI][posicionJ+2].setOpacidad(b);
				panelTablero.getTablero()[posicionI+1][posicionJ].setOpacidad(b);
				panelTablero.getTablero()[posicionI-1][posicionJ].setOpacidad(b);
				panelTablero.getTablero()[posicionI-2][posicionJ].setOpacidad(b);
				panelTablero.getTablero()[posicionI+2][posicionJ].setOpacidad(b);
				panelTablero.getTablero()[posicionI-1][posicionJ-1].setOpacidad(b);
				panelTablero.getTablero()[posicionI-1][posicionJ+1].setOpacidad(b);
				panelTablero.getTablero()[posicionI+1][posicionJ+1].setOpacidad(b);
				panelTablero.getTablero()[posicionI+1][posicionJ-1].setOpacidad(b);
				panelTablero.getTablero()[posicionI][posicionJ].setOpacidad(b);
				areaFuego.add(panelTablero.getTablero()[posicionI][posicionJ-1]);
				areaFuego.add(panelTablero.getTablero()[posicionI][posicionJ+1]);
				areaFuego.add(panelTablero.getTablero()[posicionI][posicionJ-2]);
				areaFuego.add(panelTablero.getTablero()[posicionI][posicionJ+2]);
				areaFuego.add(panelTablero.getTablero()[posicionI+1][posicionJ]);
				areaFuego.add(panelTablero.getTablero()[posicionI-1][posicionJ]);
				areaFuego.add(panelTablero.getTablero()[posicionI-2][posicionJ]);
				areaFuego.add(panelTablero.getTablero()[posicionI+2][posicionJ]);
				areaFuego.add(panelTablero.getTablero()[posicionI-1][posicionJ-1]);
				areaFuego.add(panelTablero.getTablero()[posicionI-1][posicionJ+1]);
				areaFuego.add(panelTablero.getTablero()[posicionI+1][posicionJ+1]);
				areaFuego.add(panelTablero.getTablero()[posicionI+1][posicionJ-1]);
				areaFuego.add(panelTablero.getTablero()[posicionI][posicionJ]);
				
			}
			
		//BLOQUE DE FUEGO DE SATURACION
		}else if (panelTablero.isTurnoFuegoDeSaturacion()) { // tres lineas completas de fuego	
			panelTablero.setTamañoCursor(PanelTablero.FUEGO_SATURACION);
			if (panelTablero.getOrientacion() == PanelTablero.HORIZONTAL ) {
				for (int i = 0; i < panelTablero.getTablero().length; i++) {
					if ((posicionI != 0 && posicionI != 49)) {
						panelTablero.getTablero()[posicionI-1][i].setOpacidad(b);
						panelTablero.getTablero()[posicionI][i].setOpacidad(b);
						panelTablero.getTablero()[posicionI+1][i].setOpacidad(b);
						areaFuego.add(panelTablero.getTablero()[posicionI-1][i]);
						areaFuego.add(panelTablero.getTablero()[posicionI][i]);
						areaFuego.add(panelTablero.getTablero()[posicionI+1][i]);
					}
					
				}
			}
			/*if (panelTablero.getOrientacion() == PanelTablero.VERTICAL ) {
				for (int i = 0; i < panelTablero.getTablero().length; i++) {
					if (posicionJ != 0 && posicionJ != 49) {
						panelTablero.getTablero()[i][posicionJ-1].setOpacidad(b);
						panelTablero.getTablero()[i][posicionJ].setOpacidad(b);
						panelTablero.getTablero()[i][posicionJ+1].setOpacidad(b);
						areaFuego.add(panelTablero.getTablero()[i][posicionJ-1]);
						areaFuego.add(panelTablero.getTablero()[i][posicionJ]);
						areaFuego.add(panelTablero.getTablero()[i][posicionJ+1]);
					}
				}
			}*/
		} else { //tiro normal
			panelTablero.setTamañoCursor(PanelTablero.FUEGO_NORMAL);
			areaFuego.add(this);
		}
		panelTablero.setAreaFuego(areaFuego);
	}

	public void pintarCeldasBarco(boolean estado) {
		/*if (panelTablero.getOrientacion() == PanelTablero.ATOMICA) {
			this.setOpacidad(estado);
			esCeldaValida = true;
		}*/
		for (int i = 0; i < panelTablero.getTamañoCursor(); i++) {
			if (panelTablero.getOrientacion() == PanelTablero.VERTICAL) {
				if (posicionI+i<50) {
					// aqui va el control de no cambiar fondo
					panelTablero.getTablero()[posicionI+i][posicionJ].setOpacidad(estado);
					if (panelTablero.getTablero()[posicionI+i][posicionJ].isHighlight()) {
						panelTablero.getTablero()[posicionI+i][posicionJ].setOpacidad(true);
					}
					esCeldaValida = true;
				}else {
					esCeldaValida = false;
				}			
			}
			if (panelTablero.getOrientacion() == PanelTablero.HORIZONTAL) {
				if (posicionJ+i<50) {
					// aqui va el control de no cambiar fondo
					panelTablero.getTablero()[posicionI][posicionJ+i].setOpacidad(estado);
					if (panelTablero.getTablero()[posicionI][posicionJ+i].isHighlight()) {
						panelTablero.getTablero()[posicionI][posicionJ+i].setOpacidad(true);
					}
					esCeldaValida = true;
				}else {
					esCeldaValida = false;
				}				
			}
		}
	}
	
	public void setOpacidad(boolean estado) {
		if (this.isHighlight() && !estado) {
			//do nothing
		}else {
			setOpaque(estado);
		}
	}

	public int getPosicionI() {
		return posicionI;
	}

	public void setPosicionI(int posicionI) {
		this.posicionI = posicionI;
	}

	public int getPosicionJ() {
		return posicionJ;
	}

	public void setPosicionJ(int posicionJ) {
		this.posicionJ = posicionJ;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
		if (this.getBackground() == Color.black && !highlight) {
			System.out.println("negro y falso");
		}
	}
	public int getJugador() {
		return jugador;
	}

	public void setJugador(int jugador) {
		this.jugador = jugador;
	}
}
