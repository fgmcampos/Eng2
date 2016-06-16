package br.com.fatec.dao;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateError;

import br.com.fatec.model.Apartamento;
import br.com.fatec.model.Proprietario;
import br.com.fatec.model.Tipodespesa;

@RequestScoped
public class TipodespesaDao {

	private EntityManager manager;

	@Inject
	public TipodespesaDao(EntityManager manager) {
		this.manager = manager;
	}

	TipodespesaDao() {
	}

	@Transactional
	public void adiciona(Tipodespesa tipodespesa) {

		System.out.println("Gravando");
		manager.getTransaction().begin();
		manager.persist(tipodespesa);
		manager.getTransaction().commit();

	}

	public List<Tipodespesa> lista() {
		TypedQuery<Tipodespesa> query = manager.createQuery("select t from Tipodespesa t", Tipodespesa.class);
		return query.getResultList();
	}
	
	public void deleta(int id) {
		if(id!=1){
		
		Tipodespesa t = new Tipodespesa();
		t.setId(id);
		System.out.println("Deletendo Tipodespesa com o ID = "+ id);
		try{
			manager.getTransaction().begin();
			manager.remove(manager.getReference(Tipodespesa.class, id));
			manager.getTransaction().commit();
		}catch(Exception e){
			System.out.println("ERRO AO DELETAR");
			String s = e.getMessage();
			System.out.println(s);
		}
		}else{
			System.out.println("Nao é possivel deletar a multa");
		}
	}
	
	public Tipodespesa busca(int id){
        TypedQuery<Tipodespesa> query =  manager.createQuery("select t from Tipodespesa t where t.id = :id ",Tipodespesa.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
