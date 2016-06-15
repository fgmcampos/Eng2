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
		if(condominio.getId()!=0){
			manager.getTransaction().begin();
			manager.merge(condominio);
			manager.getTransaction().commit();
		}else{
			manager.getTransaction().begin();
			manager.persist(condominio);
			manager.getTransaction().commit();
			
		}
			
		}
	
//	public List<Proprietario> lista(){
//		TypedQuery<Proprietario> query =	manager.createQuery("select p from Proprietario p",Proprietario.class);
//		return query.getResultList();
//	}
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
//	public Proprietario busca(int id){
//        TypedQuery<Proprietario> query =  manager.createQuery("select p from Proprietario p where p.id = :id ",Proprietario.class);
//        query.setParameter("id", id);
//        return query.getSingleResult();
//    }
}
