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
public class VinculaController {

	private final Result result;

	/**
		 * @deprecated CDI eyes only
		 */
		protected VinculaController() {
			this(null);
		}

	@Inject
		public VinculaController(Result result) {
			this.result = result;
		}

	@Path("/vincula")
	public void form() {

	}

}
