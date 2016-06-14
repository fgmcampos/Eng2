package br.com.fatec.dao;


import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateError;
import br.com.fatec.model.Proprietario;
import br.com.fatec.model.Vincula;

@RequestScoped
public class VinculaDao {

	private EntityManager manager;

	@Inject
	public VinculaDao(EntityManager manager) {
		this.manager = manager;
	}
	VinculaDao(){}
	
	@Transactional
	public void adiciona(Vincula vincula) {
			System.out.println("Gravando");
			manager.getTransaction().begin();
			manager.persist(vincula);
			manager.getTransaction().commit();
			
		
			
		}
	
	public List<Vincula> lista(){
		TypedQuery<Vincula> query =	manager.createQuery("select v from Vincula v",Vincula.class);
		return query.getResultList();
	}
	
	
	
	public void deleta(int id) {
		Vincula v = new Vincula();
		v.setId(id);
		System.out.println("Deletendo Vincula com o ID = "+ id);
		try{
			manager.getTransaction().begin();
			manager.remove(manager.getReference(Vincula.class, id));
			manager.getTransaction().commit();
		}catch(Exception e){
			System.out.println("ERRO AO DELETAR");
			String s = e.getMessage();
			System.out.println(s);
		}
	}
	
	
	

}
