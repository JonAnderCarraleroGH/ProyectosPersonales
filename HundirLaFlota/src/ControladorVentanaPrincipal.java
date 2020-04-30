import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

public class ControladorVentanaPrincipal {
	private VentanaInicio ventanaInicio;
	boolean estado;

	public ControladorVentanaPrincipal(VentanaInicio ventanaInicio) {
		super();
		this.ventanaInicio = ventanaInicio;
		//ventanaInicio.getLblSalir().addMouseListener(mouseListener());
		ventanaInicio.getLblEmpezarAJugar().addMouseListener(mouseListenerEmpezar(ventanaInicio.getLblEmpezarAJugar()));
	}

	private MouseAdapter mouseListener() {
		return new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				/*JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBorder(new MatteBorder(1, 1, 4, 1, (Color) new Color(128, 128, 128)));
				int respuesta = JOptionPane.showConfirmDialog(ventanaInicio, "¿Seguro que desea salir?", null, JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					//ventanaInicio.dispose();
					JFrame frame = (JFrame) SwingUtilities.getRoot(jLabel);
					frame.dispose();
				}*/


			}

			@Override
			public void mousePressed(MouseEvent e) {
				/*JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(128, 128, 128)));*/

			}

			@Override
			public void mouseExited(MouseEvent e) {
				/*JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBackground(Color.red);*/

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				/*JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBackground(new Color(255, 87, 87));*/

			}
		};
	}
	
	private MouseAdapter mouseListenerEmpezar(JLabel jLabel) {
		estado = false;
		Timer reloj = new Timer(200, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (estado) {
					jLabel.setForeground(new Color(4, 0, 171));
					estado=false;
				}
				else {
					jLabel.setForeground(Color.black);
					estado = true;
				}

			}
		});
		return new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBorder(new MatteBorder(1, 1, 4, 1, new Color(128, 128, 128)));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBorder(new MatteBorder(1, 1, 2, 1, new Color(128, 128, 128)));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				reloj.stop();

				JLabel jLabel = (JLabel) e.getSource();
				jLabel.setForeground(Color.black);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				reloj.start();

			}
		};
	}


}
