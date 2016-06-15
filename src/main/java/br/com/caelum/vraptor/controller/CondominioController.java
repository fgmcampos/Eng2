package br.com.caelum.vraptor.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.fatec.dao.ApartamentoDao;
import br.com.fatec.dao.CondominioDao;
import br.com.fatec.dao.DespesaDao;
import br.com.fatec.dao.ProprietarioDao;
import br.com.fatec.model.Apartamento;
import br.com.fatec.model.Condominio;
import br.com.fatec.model.Despesa;
import br.com.fatec.model.Proprietario;

	@Controller
	public class CondominioController {

		private ProprietarioDao proprietarioDao;
		private final Result result;
		private ApartamentoDao apartamentoDao;
		private CondominioDao condominioDao;
		private DespesaDao despesaDao;

		/**
		 * @deprecated CDI eyes only
		 */
		protected CondominioController() {
			this(null, null, null, null, null);
		}

		@Inject
		public CondominioController(Result result, ProprietarioDao proprietarioDao, ApartamentoDao apartamentoDao, CondominioDao condominioDao, DespesaDao despesaDao) {
			this.proprietarioDao = proprietarioDao;
			this.result = result;
			this.apartamentoDao = apartamentoDao;
			this.condominioDao = condominioDao;
			this.despesaDao = despesaDao;
		}

		@Path("/condominio")
		public void form() {
			List<Apartamento> apartamento = apartamentoDao.lista();
			result.include("apartamento", apartamento);
		}

		@IncludeParameters
		@Post
		public void adiciona(Condominio condominio) {
				try {
				condominioDao.adiciona(condominio);
			} catch (NullPointerException e) {
				System.out.println("ERRO!");
				e.printStackTrace();
			}
			result.redirectTo(this).lista(condominio.getApartamento(),condominio.getMesano());
			
		}

		public void lista(String apartamento,String mesano ) {
			String data = mesano;
			String ano = data.substring(0, 4);
			String mes = data.substring(5);
			String mesfix = ano+"¬"+mes;
			
			List<Despesa> despesa = despesaDao.lista();
			List<Despesa> despesafix = new ArrayList<Despesa>();;
			for(int i=0; i<despesa.size();i++){
				if(despesa.get(i).getApartamento()== Integer.parseInt(apartamento) && despesa.get(i).getMesano().equals(mesfix)){				
					Despesa despesa2 = despesa.get(i); 
					despesafix.add(despesa2);
					
				
				}
				
			}
			for(int i=0; i<despesafix.size();i++){
				String data2 = despesa.get(i).getMesano();
				String ano2 = data.substring(0, 4);
				String mes2 = data.substring(5);
				String fix = mes+"/"+ano;
				despesafix.get(i).setMesano(fix);
				DecimalFormat df = new DecimalFormat("#####0.00");
				System.out.println(df.format(despesafix.get(i).getValor()));
			}
			result.include("despesa", despesafix);
		}
//		
//		
//		@Delete("/proprietario/lista{id}")
//		public void apaga(int id) {
//				System.out.println(id);
//				proprietarioDao.deleta(id);
//				result.redirectTo(this).lista();
//			
//			
//		}
//		@Get("/proprietario/{id}")
//		public void edita(int id){
//			Proprietario proprietario = proprietarioDao.busca(id);
//					result.include("proprietario", proprietario);
//					result.of(this).form();
//		}
//		
	}