package br.com.caelum.vraptor.controller;

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
import br.com.fatec.dao.ProprietarioDao;
import br.com.fatec.model.Apartamento;
import br.com.fatec.model.Condominio;
import br.com.fatec.model.Proprietario;

	@Controller
	public class CondominioController {

		private ProprietarioDao proprietarioDao;
		private final Result result;
		private ApartamentoDao apartamentoDao;
		private CondominioDao condominioDao;

		/**
		 * @deprecated CDI eyes only
		 */
		protected CondominioController() {
			this(null, null, null, null);
		}

		@Inject
		public CondominioController(Result result, ProprietarioDao proprietarioDao, ApartamentoDao apartamentoDao, CondominioDao condominioDao) {
			this.proprietarioDao = proprietarioDao;
			this.result = result;
			this.apartamentoDao = apartamentoDao;
			this.condominioDao = condominioDao;
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
			result.redirectTo(this).form();
		}
//
//		public void lista() {
//			List<Proprietario> proprietario = proprietarioDao.lista();
//			result.include("proprietario", proprietario);
//		}
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