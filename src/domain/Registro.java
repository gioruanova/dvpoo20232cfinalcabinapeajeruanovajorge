package domain;

public class Registro {

	// ATRIBUTOS:
	private Vehiculo vehiculo;
	private Integer horaRegistro;
	private Double total;

	// CONSTRUCTORES:
	public Registro(Vehiculo vehiculo, Integer horaRegistro, Double total) {
		this.vehiculo = vehiculo;
		this.horaRegistro = horaRegistro;
		this.total = total;
	}

	// GETTERS & SETTERS
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Integer getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(Integer horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	// METODOS

	// Metodo para cobrar
	public Double cobrar() {
		Integer horaActual = EstacionPeaje.getHoraRandom();
		boolean horaPico = (horaActual >= 6 && horaActual <= 10) || (horaActual >= 17 && horaActual <= 20);

		Double tarifaBase = 0D;
		if (vehiculo.getCategoriaVehiculo() != null) {
			tarifaBase = vehiculo.getCategoriaVehiculo().getTarifa();
		}
		if (horaPico) {
			tarifaBase *= 1.08;
		}

		double descuento = 0D;
		if (vehiculo.cabinaAsignada() && vehiculo.getCabina().getMedioPago() != null) {
			descuento = vehiculo.getCabina().getMedioPago().getDescuento();
		}

		Double total = tarifaBase * (1 + descuento);

		setTotal(total);
		return total;
	}

	// METODO TOSTRING DEFAULT
	@Override
	public String toString() {
		return "Registro [vehiculo=" + vehiculo + ", horaRegistro=" + horaRegistro + ", total=" + total + "]";
	}

}
