import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ControladorPanelTablero {
	private PanelTablero panelTablero;
	private VentanaJuego ventanaJuego;
	
	
	public ControladorPanelTablero(PanelTablero panelTablero) {
		this.panelTablero = panelTablero;
		ventanaJuego = (VentanaJuego) SwingUtilities.getRoot(panelTablero);
		this.panelTablero.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 0 && panelTablero.getNumeroDeTurno() == 0 && panelTablero.getCeldaSeleccionada()!=null) {
					if (panelTablero.getOrientacion() == PanelTablero.VERTICAL) {
						panelTablero.setOrientacion(PanelTablero.HORIZONTAL);	
					}
					else {
						panelTablero.setOrientacion(PanelTablero.VERTICAL);
					}
					//panelTablero.setHorientacion(PanelTablero.HORIZONTAL);
						panelTablero.resetearLabels();
					if (panelTablero.getNumeroDeTurno() == 0) {
						panelTablero.getCeldaSeleccionada().pintarCeldasBarco(true);
					}else {
						panelTablero.getCeldaSeleccionada().pintarCeldasDisparo(true);
					}
					JFrame frame = (JFrame) SwingUtilities.getRoot(panelTablero);
					frame.repaint();
				}	
			}
		});
		
		this.panelTablero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("listener");
				panelTablero.setCeldaSeleccionada(null);
				
			}
		});
	}
	
	
}
