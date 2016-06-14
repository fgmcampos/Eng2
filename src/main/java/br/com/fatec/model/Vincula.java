package br.com.fatec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Vincula {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int proprietario;
	@NotNull
	private int apartamento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProprietario() {
		return proprietario;
	}
	public void setProprietario(int proprietario) {
		this.proprietario = proprietario;
	}
	public int getApartamento() {
		return apartamento;
	}
	public void setApartamento(int apartamento) {
		this.apartamento = apartamento;
	}
	
	
	
}
