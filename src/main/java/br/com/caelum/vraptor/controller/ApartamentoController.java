package br.com.caelum.vraptor.controller;

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
import br.com.fatec.model.Apartamento;
import br.com.fatec.model.Proprietario;
import br.com.fatec.seguranca.Open;

@Controller
public class ApartamentoController {
	private ApartamentoDao apartamentoDao;
	private final Result result;

	/**
		 * @deprecated CDI eyes only
		 */
		protected ApartamentoController() {
			this(null,null);
		}

	@Inject
		public ApartamentoController(Result result, ApartamentoDao apartamentoDao) {
			this.apartamentoDao = apartamentoDao;
			this.result = result;
		}

	@Path("/apartamento")
	public void form() {

	}
	
	@IncludeParameters
	@Post
	public void adiciona(Apartamento apartamento) {
		System.out.println("Adicionando apartamento " + apartamento.getId() + " quartos: "
			+ apartamento.getQuartos());
			apartamentoDao.adiciona(apartamento);
			result.redirectTo(this).lista();
	}
	public void lista() {
		List<Apartamento> apartamento = apartamentoDao.lista();
		result.include("apartamento", apartamento);
	}
	
	
	@Delete("/apartamento/lista{id}")
	public void apaga(int id) {
			System.out.println(id);
			apartamentoDao.deleta(id);
			result.redirectTo(this).lista();
		
		
	}
	@Get("/proprietario{id}")
	public void edita(int id){
		Apartamento apartamento = apartamentoDao.busca(id);
				result.include("apartamento", apartamento);
				result.of(this).form();
	}
	
}
