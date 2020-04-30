import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class LabelBarco extends JLabel implements MouseListener {
	private Barco barco;
	private ImageIcon image;
	private boolean estado;
	private Timer reloj = new Timer(50, new ActionListener() {
		int a=0;
		@Override
		public void actionPerformed(ActionEvent e) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					if ((a&2) == 0) {
						LabelBarco.this.setBackground(Color.DARK_GRAY);
					}
					else {
						LabelBarco.this.setBackground(Color.black);
					}
					a++;
					if (a==16) {
						reloj.stop();
						if (barco.getVidasActuales()==0) {
							LabelBarco.this.setBackground(Color.DARK_GRAY);
						}else {
							LabelBarco.this.setBackground(Color.black);
						}
						
						a=0;
					}
					
				}
			});	
		}
	});
	
	public LabelBarco() {
		super();
		setOpaque(true);
		setBackground(Color.lightGray);
		addMouseListener(this);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setEstado(true);
	}
	
	public void startTimer() {
		if (!reloj.isRunning()) {
			reloj.start();
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (estado) {
			VentanaJuego frame = (VentanaJuego) SwingUtilities.getRoot(LabelBarco.this);
			if (frame.getPanelTablero().getNumeroDeTurno() == 0) {
				frame.setLabelBarcoSelecionado(LabelBarco.this);
			}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (estado) {
			setBackground(new Color(209, 209, 209));
		}
		
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		if (estado) {
			setBackground(Color.LIGHT_GRAY);
		}
		
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public Barco getBarco() {
		return barco;
	}


	public void setBarco(Barco barco) {
		this.barco = barco;
		this.setText(barco.getNombre()+" "+barco.getVidasActuales() );
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
		LabelBarco.this.setEnabled(estado);
	} 
}
