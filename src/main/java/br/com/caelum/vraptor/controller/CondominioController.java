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
		
		public void pago(int id) {
			Condominio condominio = condominioDao.busca(id);
			System.out.println(condominio.getValorpago());
			result.include("condominio",condominio);
		}
		
			
		@IncludeParameters
		@Post
		public void adiciona(Condominio condominio) {
			Condominio condominio2 = new Condominio();
			condominio2.setApartamento(condominio.getApartamento());;
			condominio2.setDatapagamento(condominio.getDatapagamento());
			condominio2.setDatavencimento(condominio.getDatavencimento());
			condominio2.setId(condominio.getId());
			condominio2.setMesano(condominio.getMesano());
			condominio2.setValorapagar(condominio.getValorapagar());
			condominio2.setValorpago(condominio.getValorpago());

			String data = condominio.getMesano();
			String ano = data.substring(0, 4);
			String mes = data.substring(5);
			String mesfix = ano+"¬"+mes;
			//QUANDO FOR A PRIMEIRA VEZ INSERINDO ALGO NA TABALA CONDOMINIO, DESCOMENTAR O IF ABAIXO
//			if(lista.size()!=0){
			
			
			float valortotal=0;
			List<Despesa> despesa = despesaDao.lista();
			for(int x=0; x<despesa.size();x++){
				if(despesa.get(x).getApartamento()== Integer.parseInt(condominio.getApartamento()) && despesa.get(x).getMesano().equals(mesfix)){				
					valortotal= valortotal + despesa.get(x).getValor();
				}
				}
			condominio.setValorapagar(valortotal);
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
			List<Condominio> lista = condominioDao.lista();
			
			int cont = 0;

			for(int i=0; i < lista.size(); i++){
				if(condominio2.getApartamento().equals(lista.get(i).getApartamento()) && mesfix.equals(lista.get(i).getMesano())){
					if(lista.get(i).getValorpago()!=0){
						System.out.println("redirecionando");
						result.redirectTo(this).pago(lista.get(i).getId());
						cont=1;
					}
				}
			}
			
			if(cont==0){
			condominioDao.adiciona(condominio);
			result.redirectTo(this).lista(condominio.getApartamento(),condominio.getMesano(),condominio);
			}
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
		
		
		
		
		public void pagar(int multa, String apartamento, String mesano, float valor, int id){
			System.out.println(multa+apartamento+mesano+" "+valor+" "+id);
			String data = mesano;
			String ano = data.substring(0, 4);
			String mes = data.substring(5);
			String ano2 = data.substring(0, 4);
			String mes2 = data.substring(5);
			String mesfix = ano+"¬"+mes;
			String vencimento ="teste";
			Condominio condominio = new Condominio();
			condominio.setValorapagar(valor);
			String proxmes2 = "";
			if(Integer.parseInt(mes)==12){
				mes = "01";
				int anoint = Integer.parseInt(ano);
				anoint++;
				vencimento = "10/01/"+anoint;
				proxmes2= ano+"-01";
			}else{
				int mesint = Integer.parseInt(mes);
				mesint++;
				if(mesint<10){
					String meszero = "0"+mesint;
					vencimento = "10/"+meszero+"/"+ano;
					proxmes2=ano+"-"+mes;
				}else{
					vencimento = "10/"+mesint+"/"+ano;
					proxmes2=ano+"-"+mes;

				}
				System.out.println(proxmes2);
				
			}
			condominio.setDatavencimento(vencimento);
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
			condominio.setApartamento(apartamento);
			condominio.setMesano(proxmes2);
			condominio.setValorpago(valor);
			SimpleDateFormat data_formatada = new SimpleDateFormat("dd/MM/yyyy");
			String mostra_data = data_formatada.format(datahoje);
			condominio.setDatapagamento(mostra_data);
			if(datahoje.before(vencimento2)){
				String pago = "Pago";
			}else{
				if(multa==1){
					
					
					
					
					float precomulta = valor +(valor/10)*2;
					condominio.setValorpago(precomulta);
					System.out.println("Valor do condominio + multa ="+precomulta);

					
				}else{
					condominio.setValorpago(valor);
					System.out.println(mostra_data);
					float precomulta = (valor/10)*2;
					System.out.println("Valor do condominio "+ valor+". a multa foi adicionado a despesa do proximo mes");
					Despesa despesa = new Despesa();
					String proxmes = "";
					despesa.setTipodespesa(1);
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
					despesa.setValor(precomulta);
					despesa.setApartamento(Integer.parseInt(apartamento));
					Date data2 = new Date();
					SimpleDateFormat data_formatada2 = new SimpleDateFormat("dd/MM/yyyy");
					String mostra_data2 = data_formatada2.format(data2);
					despesa.setDatalancamento(mostra_data2);
					float multa5= (valor/100)*5;
					despesa.setValor(multa5);
					despesaDao.adiciona(despesa);
					System.out.println("Multa para o proximo mes adicionada");
					
				}
				}
			
			condominioDao.adiciona(condominio);

			

			result.redirectTo(this).pago(id);
		}
		
	}