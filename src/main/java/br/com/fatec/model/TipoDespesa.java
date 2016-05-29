package br.com.fatec.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

public class TipoDespesa {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private boolean valorPorQuarto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isValorPorQuarto() {
		return valorPorQuarto;
	}
	public void setValorPorQuarto(boolean valorPorQuarto) {
		this.valorPorQuarto = valorPorQuarto;
	}

}
