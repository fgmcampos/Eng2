package br.com.fatec.dao;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateError;
import br.com.fatec.model.Proprietario;

@RequestScoped
public class ProprietarioDao {

	private EntityManager manager;

	@Inject
	public ProprietarioDao(EntityManager manager) {
		this.manager = manager;
	}
	ProprietarioDao(){}
	
	@Transactional
	public void adiciona(Proprietario proprietario) {
		if(proprietario.getId()!=0){
			manager.getTransaction().begin();
			manager.merge(proprietario);
			manager.getTransaction().commit();
		}else{
			manager.getTransaction().begin();
			manager.persist(proprietario);
			manager.getTransaction().commit();
			
		}
			
		}
	
	public List<Proprietario> lista(){
		TypedQuery<Proprietario> query =	manager.createQuery("select p from Proprietario p",Proprietario.class);
		return query.getResultList();
	}
	
	
	
	public void deleta(int id) {
		Proprietario p = new Proprietario();
		p.setId(id);
		System.out.println("Deletendo Proprietario com o ID = "+ id);
		try{
			manager.getTransaction().begin();
			manager.remove(manager.getReference(Proprietario.class, id));
			manager.getTransaction().commit();
		}catch(Exception e){
			System.out.println("ERRO AO DELETAR");
			String s = e.getMessage();
			System.out.println(s);
		}
	}
	
	public Proprietario busca(int id){
        TypedQuery<Proprietario> query =  manager.createQuery("select p from Proprietario p where p.id = :id ",Proprietario.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
