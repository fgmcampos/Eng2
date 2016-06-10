package br.com.caelum.vraptor.controller;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.websocket.OnOpen;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.fatec.seguranca.Open;

@Controller
public class DespesaController {

	private final Result result;

	/**
		 * @deprecated CDI eyes only
		 */
		protected DespesaController() {
			this(null);
		}

	@Inject
		public DespesaController(Result result) {
			this.result = result;
		}

	@Path("/despesa")
	public void form() {

	}

}
