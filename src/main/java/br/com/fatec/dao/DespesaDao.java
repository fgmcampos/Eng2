package br.com.fatec.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.fatec.model.Despesa;
import br.com.fatec.model.Proprietario;
import br.com.fatec.model.Vincula;

@RequestScoped
public class DespesaDao {

	private EntityManager manager;

	@Inject
	public DespesaDao(EntityManager manager) {
		this.manager = manager;
	}
	DespesaDao(){}
	
	@Transactional
	public void adiciona(Despesa despesa) {
		if(despesa.getId()!=0){
			manager.getTransaction().begin();
			manager.merge(despesa);
			manager.getTransaction().commit();
		}else{
			manager.getTransaction().begin();
			manager.persist(despesa);
			manager.getTransaction().commit();
			
		}
	}
	public List<Despesa> lista(){
		TypedQuery<Despesa> query =	manager.createQuery("select d from Despesa d",Despesa.class);
		return query.getResultList();
	}
	
	public void deleta(int id) {
		Despesa d = new Despesa();
		d.setId(id);
		System.out.println("Deletendo Despesas com o ID = "+ id);
		try{
			manager.getTransaction().begin();
			manager.remove(manager.getReference(Despesa.class, id));
			manager.getTransaction().commit();
		}catch(Exception e){
			System.out.println("ERRO AO DELETAR");
			String s = e.getMessage();
			System.out.println(s);
		}
	}
	
	public Despesa busca(int id){
        TypedQuery<Despesa> query =  manager.createQuery("select d from Despesa d where d.id = :id ",Despesa.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}