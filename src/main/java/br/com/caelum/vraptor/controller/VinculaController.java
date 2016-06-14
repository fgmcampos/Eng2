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
import br.com.fatec.dao.ProprietarioDao;
import br.com.fatec.dao.VinculaDao;
import br.com.fatec.model.Apartamento;
import br.com.fatec.model.Proprietario;
import br.com.fatec.model.Vincula;
import br.com.fatec.seguranca.Open;

@Controller
public class VinculaController {

	private final Result result;
	private ProprietarioDao proprietarioDao;
	private ApartamentoDao apartamentoDao;
	private VinculaDao vinculaDao;

	/**
		 * @deprecated CDI eyes only
		 */
		protected VinculaController() {
			this(null,null,null,null);
		}

	@Inject
		public VinculaController(Result result, ProprietarioDao proprietarioDao, ApartamentoDao apartamentoDao,VinculaDao vinculaDao) {
			this.result = result;
			this.proprietarioDao = proprietarioDao;
			this.apartamentoDao = apartamentoDao;
			this.vinculaDao = vinculaDao;
		}

	@Path("/vincula")
	public void form() {
		List<Proprietario> proprietario = proprietarioDao.lista();
		result.include("proprietario", proprietario);
		List<Apartamento> apartamento = apartamentoDao.lista();
		result.include("apartamento", apartamento);
	}
	
	@IncludeParameters
	@Post
	public void adiciona(Vincula vincula) {
		
		System.out.println("num apartamento: "+vincula.getApartamento()+ "nome proprietario: "+ vincula.getProprietario());
		try {
			vinculaDao.adiciona(vincula);
		} catch (NullPointerException e) {
			System.out.println("ERRO!");
			e.printStackTrace();
		}
		result.redirectTo(this).lista();
	}
	
	public void lista() {
		List<Vincula> vincula = vinculaDao.lista();
		result.include("vincula", vincula);
		List<Proprietario> proprietario = proprietarioDao.lista();
		result.include("proprietario", proprietario);
	}
	
	@Delete("/vincula/lista{id}")
	public void apaga(int id) {
			System.out.println(id);
			vinculaDao.deleta(id);
			result.redirectTo(this).lista();

}
}
