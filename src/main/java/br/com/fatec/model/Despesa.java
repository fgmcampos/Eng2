package br.com.fatec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Despesa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String datalancamento;
	@NotNull
	private int apartamento;
	@NotNull
	private int tipodespesa;
	@NotEmpty
	private String mesano;
	@NotNull
	private float valor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMesano() {
		return mesano;
	}

	public void setMesano(String mesano) {
		this.mesano = mesano;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getDatalancamento() {
		return datalancamento;
	}

	public void setDatalancamento(String datalancamento) {
		this.datalancamento = datalancamento;
	}

	public int getApartamento() {
		return apartamento;
	}

	public void setApartamento(int apartamento) {
		this.apartamento = apartamento;
	}

	public int getTipodespesa() {
		return tipodespesa;
	}

	public void setTipodespesa(int tipodespesa) {
		this.tipodespesa = tipodespesa;
	}

}
