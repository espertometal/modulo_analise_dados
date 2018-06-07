package br.com.univolt.modelo;

import java.util.Calendar;

// POJO - Consumo
public class TabelaConsumo {
	private Long serial;
	private Calendar data;
	private String tempo;
	private String valor;
	private String consumo;
	private Integer codigo;
	
	// métodos get e setter
	public Long getSerial() {
		return serial;
	}
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
}
