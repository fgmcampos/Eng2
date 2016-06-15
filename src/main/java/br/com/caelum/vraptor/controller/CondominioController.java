package br.com.caelum.vraptor.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.fatec.dao.TipodespesaDao;
import br.com.fatec.model.Apartamento;
import br.com.fatec.model.Condominio;
import br.com.fatec.model.Despesa;
import br.com.fatec.model.Proprietario;
import br.com.fatec.model.Tipodespesa;

	@Controller
	public class CondominioController {

		private ProprietarioDao proprietarioDao;
		private final Result result;
		private ApartamentoDao apartamentoDao;
		private CondominioDao condominioDao;
		private DespesaDao despesaDao;
		private TipodespesaDao tipodespesaDao;
		private String apartamento;
		private String mesano;

		/**
		 * @deprecated CDI eyes only
		 */
		protected CondominioController() {
			this(null, null, null, null, null,null);
		}

		@Inject
		public CondominioController(Result result, ProprietarioDao proprietarioDao, ApartamentoDao apartamentoDao, 
				CondominioDao condominioDao, DespesaDao despesaDao, TipodespesaDao tipodespesaDao) {
			this.proprietarioDao = proprietarioDao;
			this.result = result;
			this.apartamentoDao = apartamentoDao;
			this.condominioDao = condominioDao;
			this.despesaDao = despesaDao;
			this.tipodespesaDao = tipodespesaDao;
		}

		@Path("/condominio")
		public void form() {
			List<Apartamento> apartamento = apartamentoDao.lista();
			result.include("apartamento", apartamento);
		}

		@IncludeParameters
		@Post
		public void adiciona(Condominio condominio) {
			
			String data = condominio.getMesano();
			String ano = data.substring(0, 4);
			String mes = data.substring(5);
			String mesfix = ano+"¬"+mes;
			float valortotal=0;
			List<Despesa> despesa = despesaDao.lista();
			for(int i=0; i<despesa.size();i++){
				if(despesa.get(i).getApartamento()== Integer.parseInt(condominio.getApartamento()) && despesa.get(i).getMesano().equals(mesfix)){				
					valortotal= valortotal + despesa.get(i).getValor();
				}
				}
			condominio.setValorAPagar(valortotal);
			String vencimento ="teste";
			if(Integer.parseInt(mes)==12){
				mes = "01";
				int anoint = Integer.parseInt(ano);
				anoint++;
				vencimento = "10/"+mes+"/"+anoint;
				condominio.setDatavencimento(vencimento);
			}else{
				int mesint = Integer.parseInt(mes);
				mesint++;
				vencimento = "10/"+mesint+"/"+ano;
				condominio.setDatavencimento(vencimento);
				
			}
				try {
					
				condominioDao.adiciona(condominio);
			} catch (NullPointerException e) {
				System.out.println("ERRO!");
				e.printStackTrace();
			}
			result.redirectTo(this).lista(condominio.getApartamento(),condominio.getMesano(),condominio);
			
		}

		public void lista(String apartamento,String mesano, Condominio condominio) {
			String data = mesano;
			String ano = data.substring(0, 4);
			String mes = data.substring(5);
			String mesfix = ano+"¬"+mes;
			float valortotal=0;
			String vencimento ="teste";
			DecimalFormat df = new DecimalFormat("#####0.00");
			List<Despesa> despesa = despesaDao.lista();
			List<Despesa> despesafix = new ArrayList<Despesa>();;
			for(int i=0; i<despesa.size();i++){
				if(despesa.get(i).getApartamento()== Integer.parseInt(apartamento) && despesa.get(i).getMesano().equals(mesfix)){				
					Despesa despesa2 = despesa.get(i); 
					despesafix.add(despesa2);
					valortotal= valortotal + despesa.get(i).getValor();
				
				}
				
			}
			for(int i=0; i<despesafix.size();i++){
				String fix = mes+"/"+ano;
				despesafix.get(i).setMesano(fix);
				

				System.out.println(df.format(despesafix.get(i).getValor()));
			}			
			result.include("despesa", despesafix);
			List<Tipodespesa> tipodespesa = tipodespesaDao.lista();
			result.include("tipodespesa", tipodespesa);
			result.include("valortotal", df.format(valortotal));
			if(Integer.parseInt(mes)==12){
				mes = "01";
				int anoint = Integer.parseInt(ano);
				anoint++;
				vencimento = "10/"+mes+"/"+anoint;
			}else{
				int mesint = Integer.parseInt(mes);
				mesint++;
				vencimento = "10/"+mesint+"/"+ano;
				
			}
			
			result.include("vencimento", vencimento);
			float multa2 = (valortotal/100)*2;
			float multa5 = (valortotal/100)*5;
			result.include("multa2", df.format(multa2));
			result.include("multa5", df.format(multa5));
			result.include(condominio);
		}
		
		
		@Delete("/condominio/lista{id}")
		public void apaga(int id) {
				Despesa despesa = despesaDao.busca(id);
				String apartamento = Integer.toString(despesa.getApartamento());
				String mesano = despesa.getMesano();
				
			
				System.out.println(apartamento +"   " + mesano);
				despesaDao.deleta(id);
				result.redirectTo(this).lista(apartamento,mesano,null);
						
		}
		
		
		public void pagar(int multa, String apartamento, String mesano, float valor){
			String data = mesano;
			String ano = data.substring(0, 4);
			String mes = data.substring(5);
			String ano2 = data.substring(0, 4);
			String mes2 = data.substring(5);
			String mesfix = ano+"¬"+mes;
			String vencimento ="teste";
			if(Integer.parseInt(mes)==12){
				mes = "01";
				int anoint = Integer.parseInt(ano);
				anoint++;
				vencimento = "10/"+mes+"/"+anoint;
			}else{
				int mesint = Integer.parseInt(mes);
				mesint++;
				if(mesint<10){
					String meszero = "0"+mesint;
					vencimento = "10/"+meszero+"/"+ano;
				}else{
					vencimento = "10/"+mesint+"/"+ano;
				}
				
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			Date vencimento2 = new Date();
			try {
				Date vencimentodate = sdf.parse(vencimento);
				vencimento2 = vencimentodate;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			Date datahoje = new Date();
			if(datahoje.before(vencimento2)){
				String pago = "Pago";
			}else{
				if(multa==1){
					Condominio condominio = new Condominio();
					condominio.setValorAPagar(valor);
					condominio.setApartamento(apartamento);
					condominio.setMesano(mesano);				
					float precomulta = valor +(valor/10)*2;
					condominio.setValorPago(precomulta);
					System.out.println("Valor do condominio + multa ="+precomulta);
					condominioDao.adiciona(condominio);
					
				}else{
					Condominio condominio = new Condominio();
					condominio.setValorAPagar(valor);
					condominio.setApartamento(apartamento);
					condominio.setMesano(mesano);
					condominio.setValorPago(valor);
					System.out.println("Valor do condominio "+ valor+". a multa foi adicionado a despesa do proximo mes");
					condominioDao.adiciona(condominio);
					Despesa despesa = new Despesa();
					String proxmes = "";
					despesa.setTipodespesa(8);
					if(Integer.parseInt(mes2)==12){
					int proxano =Integer.parseInt(ano2)+1;
					proxmes = proxano+"-01";
					}else{
					if(Integer.parseInt(mes2)<10){
					proxmes = ano2+"-0"+(Integer.parseInt(mes2)+1);
					}else{
						proxmes = ano2+"-"+(Integer.parseInt(mes2)+1);
					}
					}			
					despesa.setMesano(proxmes);
					despesa.setApartamento(Integer.parseInt(apartamento));
					Date data2 = new Date();
					SimpleDateFormat data_formatada = new SimpleDateFormat("dd/MM/yyyy");
					String mostra_data = data_formatada.format(data2);
					despesa.setDatalancamento(mostra_data);
					float multa5= (valor/100)*5;
					despesa.setValor(multa5);
					despesaDao.adiciona(despesa);
					System.out.println("Multa para o proximo mes adicionada");
					
				}
				}
			



			result.redirectTo(this).form();
		}
		
	}