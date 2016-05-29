package br.com.fatec.model;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

public class Condominio {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String mesAno;	
	private Date dataPagamento;
	@NotEmpty
	private Date dataVencimento;
	private float valorPago;
	@NotEmpty
	private float valorAPagar;
	private List<Despesa> listaDEspesa;
	private List<ItemCondominio> listaCondominio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
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
	public List<Despesa> getListaDEspesa() {
		return listaDEspesa;
	}
	public void setListaDEspesa(List<Despesa> listaDEspesa) {
		this.listaDEspesa = listaDEspesa;
	}
	public List<ItemCondominio> getListaCondominio() {
		return listaCondominio;
	}
	public void setListaCondominio(List<ItemCondominio> listaCondominio) {
		this.listaCondominio = listaCondominio;
	}
	
	
}
