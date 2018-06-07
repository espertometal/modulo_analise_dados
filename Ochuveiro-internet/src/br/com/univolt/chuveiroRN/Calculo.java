package br.com.univolt.chuveiroRN;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Calculo {

	private final Long potencia = 15500L;
	//private final Double TARIFA = 0.300710;//
	private final Double TARIFA = 1.000000;
	// private final Double TARIFA = 0.03;
	
	private int ms;
	private Double segundos;
	private Double minutos;
	private Double horas;

	public Calculo(String milisegundos) {
		this.ms = Integer.parseInt(milisegundos);
		this.segundos = Double.parseDouble(milisegundos) / 1000;
		this.minutos = segundos / 60;
		this.horas = minutos / 60;
	}

		
	// formula: Pot*horas
	// Pot = Potencia chuveiro
	private Double getEnergia() {
		Double pot = (double) (potencia / 1000); // conversão de W para kW

		return pot * horas;
	}

	// formula: tarifa * EnergiaConsumida
	public Double getCusto() {

		if (horas != 0.0) {
			Double custo = TARIFA * getEnergia();
			return custo;
		}
		return null;
	}

	public String msToHourSecond() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MILLISECOND, ms);
		return new SimpleDateFormat("HH:mm:ss").format(c.getTime());
	}
}
