import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

public class LabelBoton extends JLabel implements MouseListener {
	public static final int LABEL_SALIR = 1;
	public static final int LABEL_EMPEZAR = 2;
	private Color salir = Color.RED;
	private int tipo;
	private boolean estadoReloj;
	private Timer reloj;
	
	public LabelBoton(int tipo, String text, Rectangle dimension) {
		super(text);
		this.tipo = tipo;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBorder(new MatteBorder(1, 1, 4, 1, new Color(128, 128, 128)));
		if (tipo == LABEL_EMPEZAR) {
			setBackground(Color.WHITE);
		}
		if (tipo == LABEL_SALIR) {
			setBackground(Color.RED);
		}
		
		setOpaque(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Stencil", Font.BOLD, 30));
		setBounds(dimension);
		addMouseListener(this);
		estadoReloj = false;
		reloj = new Timer(200, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (estadoReloj) {
					setForeground(new Color(4, 0, 171));
					estadoReloj=false;
				}
				else {
					setForeground(Color.black);
					estadoReloj = true;
				}

			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (tipo == LABEL_SALIR) {
			LabelBoton.this.setBackground(new Color(255, 87, 87));
		}
		
		if (tipo == LABEL_EMPEZAR) {
			reloj.start();
		}
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (tipo == LABEL_SALIR) {
			LabelBoton.this.setBackground(Color.red);
		}
		
		if (tipo == LABEL_EMPEZAR) {
			reloj.stop();
			LabelBoton.this.setForeground(Color.black);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		LabelBoton.this.setBorder(new MatteBorder(1, 1, 2, 1, new Color(128, 128, 128)));
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (tipo == LABEL_SALIR) {
			JFrame frame = (JFrame) SwingUtilities.getRoot(LabelBoton.this);
			LabelBoton.this.setBorder(new MatteBorder(1, 1, 4, 1, new Color(128, 128, 128)));
			int respuesta = JOptionPane.showConfirmDialog(SwingUtilities.getRoot(LabelBoton.this), "¿Seguro que desea salir?", null, JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				//ventanaInicio.dispose();
				frame.dispose();
			}
		}
		
		if (tipo == LABEL_EMPEZAR) {
			JFrame frame = (JFrame) SwingUtilities.getRoot(LabelBoton.this);
			LabelBoton.this.setBorder(new MatteBorder(1, 1, 4, 1, new Color(128, 128, 128)));
			JOptionPane.showMessageDialog(frame, "¿El juego esta a punto de empezar, que uno de los dos jugadores se aparte de la pantalla");
			frame.dispose();
			VentanaJuego juego = new VentanaJuego();
			juego.setVisible(true);
				
		}
		
	}
}
