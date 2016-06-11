package br.com.caelum.vraptor.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.websocket.OnOpen;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import br.com.fatec.dao.ProprietarioDao;
import br.com.fatec.model.Proprietario;
import br.com.fatec.seguranca.Open;

@Controller
public class ProprietarioController {

	private ProprietarioDao proprietarioDao;
	private final Result result;
	private Validator validator;

	/**
	 * @deprecated CDI eyes only
	 */
	protected ProprietarioController() {
		this(null, null);
	}

	@Inject
	public ProprietarioController(Result result, ProprietarioDao proprietarioDao) {
		this.proprietarioDao = proprietarioDao;
		this.result = result;
	}

	@Path("/proprietario")
	public void form() {

	}

	@IncludeParameters
	@Post
	public void adiciona(Proprietario proprietario) {
		System.out.println("Proprietario Adicionado com sucesso! Nome: " + proprietario.getNome() + " Telefone: "
				+ proprietario.getTelefone());
		try {
			proprietarioDao.adiciona(proprietario);
		} catch (NullPointerException e) {
			System.out.println("ERRO!");
			e.printStackTrace();
		}
		result.redirectTo(this).lista();
	}

	public void lista() {
		List<Proprietario> proprietario = proprietarioDao.lista();
		result.include("proprietario", proprietario);
	}
	
	
	@Delete("/proprietario/lista{id}")
	public void apaga(int id) {
		System.out.println(id);
		//System.out.println("ID = " +id);
		//try {
			proprietarioDao.deleta(id);
		//} catch (EntityNotFoundException e) {
		//	System.out.println("ERRO ao deletar o id" +id);
		//	e.printStackTrace();
		//	
		//}
		result.redirectTo(this).lista();
	}
}