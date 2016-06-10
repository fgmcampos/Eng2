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
		if(proprietario.getId()==0){
			manager.getTransaction().begin();
			manager.persist(proprietario);
			manager.getTransaction().commit();
		}else{
			manager.merge(proprietario);
		}
			
		}
}
