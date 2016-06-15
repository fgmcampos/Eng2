package br.com.fatec.dao;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateError;

import br.com.fatec.model.Condominio;
import br.com.fatec.model.Proprietario;

@RequestScoped
public class CondominioDao {

	private EntityManager manager;

	@Inject
	public CondominioDao(EntityManager manager) {
		this.manager = manager;
	}
	CondominioDao(){}
	
	@Transactional
	public void adiciona(Condominio condominio) {
		List<Condominio> lista = lista();
		int cont = lista.size();
		int cont2 =0;
		String data = condominio.getMesano();
		String ano = data.substring(0, 4);
		String mes = data.substring(5);
		String mesfix = ano+"¬"+mes;
		
		for(int i=0; i < lista.size(); i++){
			if(condominio.getApartamento().equals(lista.get(i).getApartamento()) && mesfix.equals(lista.get(i).getMesano())){
				int id =lista.get(i).getId();
				condominio.setId(id);
				manager.getTransaction().begin();
				manager.merge(condominio);
				manager.getTransaction().commit();
			}else{
				cont2++;
			}
			
		if(cont2==cont){
			manager.getTransaction().begin();
			manager.persist(condominio);
			manager.getTransaction().commit();
		}
		}
			
			
		
			
}
	
	public List<Condominio> lista(){
		TypedQuery<Condominio> query =	manager.createQuery("select c from Condominio c",Condominio.class);
		return query.getResultList();
	}
//	
//	
//	
//	public void deleta(int id) {
//		Proprietario p = new Proprietario();
//		p.setId(id);
//		System.out.println("Deletendo Proprietario com o ID = "+ id);
//		try{
//			manager.getTransaction().begin();
//			manager.remove(manager.getReference(Proprietario.class, id));
//			manager.getTransaction().commit();
//		}catch(Exception e){
//			System.out.println("ERRO AO DELETAR");
//			String s = e.getMessage();
//			System.out.println(s);
//		}
//	}
//	
	public Condominio busca(int id){
        TypedQuery<Condominio> query =  manager.createQuery("select c from Condominio c where c.id = :id ",Condominio.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
