package br.com.caelum.vraptor.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.websocket.OnOpen;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.fatec.dao.ApartamentoDao;
import br.com.fatec.dao.DespesaDao;
import br.com.fatec.dao.ProprietarioDao;
import br.com.fatec.dao.TipodespesaDao;
import br.com.fatec.dao.VinculaDao;
import br.com.fatec.model.Apartamento;
import br.com.fatec.model.Despesa;
import br.com.fatec.model.Proprietario;
import br.com.fatec.model.Tipodespesa;
import br.com.fatec.model.Vincula;
import br.com.fatec.seguranca.Open;

@Controller
public class DespesaController {

	private final Result result;
	private ProprietarioDao proprietarioDao;
	private ApartamentoDao apartamentoDao;
	private TipodespesaDao tipodespesaDao;
	private VinculaDao vinculaDao;
	private DespesaDao despesaDao;
	/**
		 * @deprecated CDI eyes only
		 */
		protected DespesaController() {
			this(null,null,null,null,null,null);
		}

	@Inject
		public DespesaController(Result result,ProprietarioDao proprietarioDao,ApartamentoDao apartamentoDao, TipodespesaDao tipodespesaDao,
				VinculaDao vinculaDao, DespesaDao despesaDao ) {
			this.result = result;
			this.proprietarioDao = proprietarioDao;
			this.apartamentoDao = apartamentoDao;
			this.tipodespesaDao = tipodespesaDao;
			this.vinculaDao = vinculaDao;
			this.despesaDao = despesaDao;
		}

	@Path("/despesa")
	public void form() {
		List<Apartamento> apartamento = apartamentoDao.lista();
		result.include("apartamento", apartamento);
		List<Tipodespesa> tipodespesa = tipodespesaDao.lista();
		result.include("tipodespesa", tipodespesa);

	}
	
	public void geral() {
		
		List<Tipodespesa> tipodespesa = tipodespesaDao.lista();
		result.include("tipodespesa", tipodespesa);

	}
	
	
	@IncludeParameters
	@Post
	public void adiciona(Despesa despesa) {
		
		Date data = new Date();
		SimpleDateFormat data_formatada = new SimpleDateFormat("dd/MM/yyyy");
		String mostra_data = data_formatada.format(data);
		despesa.setDatalancamento(mostra_data);
		System.out.println("num apartamento: "+despesa.getApartamento()+ "data "+ despesa.getMesano());
		try {
			despesaDao.adiciona(despesa);
		} catch (NullPointerException e) {
			System.out.println("ERRO!");
			e.printStackTrace();
		}
		result.redirectTo(this).lista();
	}
	
	public void lista() {
		List<Proprietario> proprietario = proprietarioDao.lista();
		result.include("proprietario", proprietario);
		List<Vincula> vincula = vinculaDao.lista();
		result.include("vincula", vincula);
		List<Despesa> despesa = despesaDao.lista();
		for(int i=0; i<despesa.size();i++){
			String data = despesa.get(i).getMesano();
			String ano = data.substring(0, 4);
			String mes = data.substring(5);
			String fix = mes+"/"+ano;
			despesa.get(i).setMesano(fix);
			DecimalFormat df = new DecimalFormat("#####0.00");
			System.out.println(df.format(despesa.get(i).getValor()));
		}
		result.include("despesa", despesa);
	}

	@IncludeParameters
	@Post
	public void adicionaGeral(Despesa despesa) {
		Date data = new Date();
		SimpleDateFormat data_formatada = new SimpleDateFormat("dd/MM/yyyy");
		String mostra_data = data_formatada.format(data);
		List<Apartamento> apartamento = apartamentoDao.lista();
		result.include("apartamento", apartamento);
		int idTipo =despesa.getTipodespesa();
		Tipodespesa tipodespesa = tipodespesaDao.busca(idTipo);
		int totalquartos =0;
		for(int i=0;i < apartamento.size();i++){
			totalquartos = totalquartos + apartamento.get(i).getQuartos();
		}
		if(tipodespesa.getValorporquarto()==1){
			for(int i=0;i < apartamento.size();i++){
				Despesa despesa1 = new Despesa();
				despesa1.setDatalancamento(mostra_data);
				despesa1.setMesano(despesa.getMesano());
				despesa1.setTipodespesa(despesa.getTipodespesa());
				float valorporquarto = despesa.getValor()/totalquartos*apartamento.get(i).getQuartos();
				despesa1.setValor(valorporquarto);
				despesa1.setApartamento(apartamento.get(i).getId());
				despesaDao.adiciona(despesa1);

			}
		}else{
		for(int i=0;i < apartamento.size();i++){
			Despesa despesa1 = new Despesa();
			despesa1.setDatalancamento(mostra_data);
			despesa1.setMesano(despesa.getMesano());
			despesa1.setTipodespesa(despesa.getTipodespesa());
			despesa1.setValor(despesa.getValor());
			System.out.println(apartamento.get(i).getId()+"      "+ despesa.getId());
			despesa1.setApartamento(apartamento.get(i).getId());
			despesaDao.adiciona(despesa1);
		}	
			
		}
		result.redirectTo(this).form();
	}
	@Delete("/despesa/lista{id}")
	public void apaga(int id) {
			System.out.println(id);
			despesaDao.deleta(id);
			result.redirectTo(this).lista();

}
}
