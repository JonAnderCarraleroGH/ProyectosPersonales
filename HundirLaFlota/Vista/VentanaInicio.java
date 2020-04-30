import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private LabelBoton lblEmpezarAJugar;
	private LabelBoton lblSalir;
	private ControladorVentanaPrincipal controladorVentanaPrincipal;
	private JPanel panel;
	private ComponentMover cm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
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
	public VentanaInicio() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		java.net.URL urlImagen=getClass().getResource("fondo.jpg");
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(urlImagen).getImage().getScaledInstance(660, 425, Image.SCALE_SMOOTH));
		
		
		JLabel lblHundir = new JLabel("HUNDIR");
		lblHundir.setFont(new Font("Stencil", Font.BOLD, 30));
		lblHundir.setHorizontalAlignment(SwingConstants.CENTER);
		lblHundir.setBounds(210, 45, 249, 34);
		contentPane.add(lblHundir);
		
		JLabel lblLa = new JLabel("LA");
		lblLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblLa.setFont(new Font("Stencil", Font.BOLD, 30));
		lblLa.setBounds(210, 79, 249, 34);
		contentPane.add(lblLa);
		
		JLabel lblFlota = new JLabel("FLOTA");
		lblFlota.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlota.setFont(new Font("Stencil", Font.BOLD, 30));
		lblFlota.setBounds(210, 114, 249, 34);
		contentPane.add(lblFlota);
		
		lblEmpezarAJugar = new LabelBoton(LabelBoton.LABEL_EMPEZAR, "EMPEZAR A JUGAR", new Rectangle(157, 202, 358, 34));
		contentPane.add(lblEmpezarAJugar);
		
		lblSalir = new LabelBoton(LabelBoton.LABEL_SALIR, "SALIR", new Rectangle(210, 247, 249, 34));
		contentPane.add(lblSalir);
		
		JLabel label = new JLabel();
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setOpaque(true);
		label.setBounds(0, 0, 644, 386);
		label.setIcon(imageIcon);
		contentPane.add(label);
		
		
		
		panel = new JPanel();
		panel.setBounds(0, 0, 644, 39);
		contentPane.add(panel);
		//label.setBounds(5, 5, 440, 290);
		cm = new ComponentMover(this,panel);
		controladorVentanaPrincipal = new ControladorVentanaPrincipal(this);
		
	}

	public JLabel getLblEmpezarAJugar() {
		return lblEmpezarAJugar;
	}

	public void setLblEmpezarAJugar(LabelBoton lblEmpezarAJugar) {
		this.lblEmpezarAJugar = lblEmpezarAJugar;
	}

	public LabelBoton getLblSalir() {
		return lblSalir;
	}

	public void setLblSalir(LabelBoton lblSalir) {
		this.lblSalir = lblSalir;
	}
}
