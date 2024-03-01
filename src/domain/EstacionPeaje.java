package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EstacionPeaje {

	// ATRIBUTOS:
	private Long idEstacionPeaje;
	private String descripcionPeaje;
	private List<Cabina> cabina = new ArrayList<>();
	private static Random random = new Random();

	// CONSTRUCTORES:
	public EstacionPeaje() {

	}

	public EstacionPeaje(Long idEstacionPeaje, String descripcionPeaje, List<Cabina> cabina) {
		this.idEstacionPeaje = idEstacionPeaje;
		this.descripcionPeaje = descripcionPeaje;
		this.cabina = cabina;
	}

	// GETTERS & SETTERS
	public Long getIdEstacionPeaje() {
		return idEstacionPeaje;
	}

	public void setIdEstacionPeaje(Long idEstacionPeaje) {
		this.idEstacionPeaje = idEstacionPeaje;
	}

	public String getDescripcionPeaje() {
		return descripcionPeaje;
	}

	public void setDescripcionPeaje(String descripcionPeaje) {
		this.descripcionPeaje = descripcionPeaje;
	}

	public List<Cabina> getCabina() {
		return cabina;
	}

	public void setCabina(List<Cabina> cabina) {
		this.cabina = cabina;
	}

	public static Random getRandom() {
		return random;
	}

	public static void setRandom(Random random) {
		EstacionPeaje.random = random;
	}

	// METODOS
	public static Integer getHoraRandom() {
		if (random == null) {
			random = new Random();
		}
		return random.nextInt(24);
	}

	// METODOS
	public List<Long> idsCabinaEfectivo() {
		List<Long> idsCabinaEfectivo = new ArrayList<>();
		for (Cabina cabina : cabina) {
			if (cabina.getMedioPago() instanceof Efectivo) {
				idsCabinaEfectivo.add(cabina.getIdPeaje());
			}
		}
		return idsCabinaEfectivo;
	}

	public double promedioMora() {
		int totalDiasDemora = 0;
		int cantidadCabinas = 0;

		for (Cabina cabina : cabina) {
			MedioPago medioDePago = cabina.getMedioPago();

			if (medioDePago instanceof Pase || medioDePago instanceof Sube) {
				Integer diasDemora = medioDePago.getDiasDeMora();

				if (diasDemora != null) {
					totalDiasDemora += diasDemora;
					cantidadCabinas++;
				}
			}
		}
		if (cantidadCabinas > 0) {
			return (double) totalDiasDemora / cantidadCabinas;
		} else {
			return 0D;
		}
	}
	
	

	// METODO TOSTRING DEFAULT
	@Override
	public String toString() {
		return "EstacionPeaje [" + " ID= " + idEstacionPeaje + ", Descripcion='" + descripcionPeaje + '|' + ", Cabina: "
				+ ']';
	}
}