
public class Barco {
	public static final int VIDAS_BUQUE_INSIGNIA = 7;
	public static final int VIDAS_ACORAZADO = 6;
	public static final int VIDAS_PORTAVIONES = 5;
	public static final int VIDAS_FRAGATA = 4;
	public static final int VIDAS_GALEON = 4;
	public static final int VIDAS_CA�ONERA = 3;
	public static final int VIDAS_CRUCERO = 2;
	private String nombre;
	private int numeroM�ximoDeVidas;
	private LabelCelda[] posicion;
	private int vidasActuales;
	private int jugador;
	private int[] posicionesBombardeadas;

	public Barco(int numeroVidas, String nombre, int jugador) {
		numeroM�ximoDeVidas = numeroVidas;
		vidasActuales = numeroVidas;
		posicionesBombardeadas = new int[numeroM�ximoDeVidas];
		for (int i = 0; i < posicionesBombardeadas.length; i++) {
			posicionesBombardeadas[i] = 0;
		}
		this.nombre = nombre;
		this.setJugador(jugador);
	}
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroM�ximoDeVidas() {
		return numeroM�ximoDeVidas;
	}

	public void setNumeroM�ximoDeVidas(int numeroM�ximoDeVidas) {
		this.numeroM�ximoDeVidas = numeroM�ximoDeVidas;
	}

	public LabelCelda[] getPosicion() {
		return posicion;
	}

	public void setPosicion(LabelCelda[] posicion) {
		this.posicion = posicion;
	}

	public int getVidasActuales() {
		return vidasActuales;
	}

	public void setVidasActuales(int vidasActuales) {
		this.vidasActuales = vidasActuales;
	}

	public int getJugador() {
		return jugador;
	}

	public void setJugador(int jugador) {
		this.jugador = jugador;
	}

	public int[] getPosicionesBombardeadas() {
		return posicionesBombardeadas;
	}

	public void setPosicionesBombardeadas(int[] posicionesBombardeadas) {
		this.posicionesBombardeadas = posicionesBombardeadas;
	}
	
	public void setPosicionBombardeada(int posicion) {
		this.posicionesBombardeadas[posicion] = 1;
	}
}
