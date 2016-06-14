package br.com.fatec.dao;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.transaction.Transactional;
import org.hibernate.HibernateError;
import br.com.fatec.model.Apartamento;
import br.com.fatec.model.Proprietario;

@RequestScoped
public class ApartamentoDao {

	private EntityManager manager;

	@Inject
	public ApartamentoDao(EntityManager manager) {
		this.manager = manager;
	}
	ApartamentoDao(){}
	
	@Transactional
	public void adiciona(Apartamento apartamento) {		
		System.out.println("Salvando apartamento");
		int id = apartamento.getId();
		TypedQuery<Apartamento> query =  manager.createQuery("select a from Apartamento a where a.id = :id ",Apartamento.class);
		if(query == null){
	   	manager.getTransaction().begin();
		manager.persist(apartamento);
		manager.getTransaction().commit();
		}else{		 
		 manager.getTransaction().begin();
		 manager.merge(apartamento);
		 manager.getTransaction().commit();
		}
	}
	public List<Apartamento> lista(){
		TypedQuery<Apartamento> query =	manager.createQuery("select a from Apartamento a",Apartamento.class);
		return query.getResultList();
	}
	
	
	
	public void deleta(int id) {
		Apartamento a = new Apartamento();
		a.setId(id);
		System.out.println("Deletendo Apartamento com o ID = "+ id);
		try{
			manager.getTransaction().begin();
			manager.remove(manager.getReference(Apartamento.class, id));
			manager.getTransaction().commit();
		}catch(Exception e){
			System.out.println("ERRO AO DELETAR");
			String s = e.getMessage();
			System.out.println(s);
		}
	}
	
	public Apartamento busca(int id){
        TypedQuery<Apartamento> query =  manager.createQuery("select a from Apartamento a where a.id = :id ",Apartamento.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}