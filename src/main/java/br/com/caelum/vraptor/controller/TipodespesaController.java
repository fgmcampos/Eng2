package br.com.caelum.vraptor.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.fatec.dao.ApartamentoDao;
import br.com.fatec.dao.ProprietarioDao;
import br.com.fatec.dao.TipodespesaDao;
import br.com.fatec.dao.VinculaDao;
import br.com.fatec.model.Apartamento;
import br.com.fatec.model.Proprietario;
import br.com.fatec.model.Tipodespesa;
import br.com.fatec.model.Vincula;

@Controller
public class TipodespesaController {
	
	private final Result result;
	private TipodespesaDao tipodespesaDao;
	

	/**
		 * @deprecated CDI eyes only
		 */
		protected TipodespesaController() {
			this(null,null);
		}

	@Inject
		public TipodespesaController(Result result,TipodespesaDao tipodespesaDao) {
			this.result = result;
			this.tipodespesaDao = tipodespesaDao;


		}

	@Path("/tipodespesa")
	public void form() {
		
	}
	
	@IncludeParameters
	@Post
	public void adiciona(Tipodespesa tipodespesa) {
		try {
			System.out.println(tipodespesa.getValorporquarto());
			tipodespesaDao.adiciona(tipodespesa);
		} catch (NullPointerException e) {
			System.out.println("ERRO!");
			e.printStackTrace();
		}
		result.redirectTo(this).lista();
	}
	
	public void lista() {
		List<Tipodespesa> tipodespesa = tipodespesaDao.lista();
		result.include("tipodespesa", tipodespesa);
	}
	
	
	@Delete("/tipodespesa/lista{id}")
	public void apaga(int id) {
			System.out.println(id);
			tipodespesaDao.deleta(id);
			result.redirectTo(this).lista();
		
	}
}
