package br.com.fatec.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Condominio {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String apartamento;
	private String mesano;
	private String datapagamento;
	private String datavencimento;
	private float valorPago;
	private float valorAPagar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDatapagamento() {
		return datapagamento;
	}
	public void setDatapagamento(String datapagamento) {
		this.datapagamento = datapagamento;
	}
	public String getDatavencimento() {
		return datavencimento;
	}
	public void setDatavencimento(String datavencimento) {
		this.datavencimento = datavencimento;
	}
	public float getValorPago() {
		return valorPago;
	}
	public void setValorPago(float valorPago) {
		this.valorPago = valorPago;
	}
	public float getValorAPagar() {
		return valorAPagar;
	}
	public void setValorAPagar(float valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	public String getApartamento() {
		return apartamento;
	}
	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}
	public String getMesano() {
		return mesano;
	}
	public void setMesano(String mesano) {
		this.mesano = mesano;
	}

	
	
}
