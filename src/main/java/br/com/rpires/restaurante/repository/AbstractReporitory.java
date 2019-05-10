/**
 * 
 */
package br.com.rpires.restaurante.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author digao
 *
 */
public abstract class AbstractReporitory<T extends Serializable> implements IRepository<T> {

	@PersistenceContext(unitName = "RestauranteManager")
	protected EntityManager entityManager;

	private Class<T> clazz;

	protected AbstractReporitory(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void salvar(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
	}

	@Override
	public List<T> listar() {
		CriteriaBuilder builder = getBuilder();
	    CriteriaQuery<T> query = builder.createQuery(clazz);
	    Root<T> variableRoot = query.from(clazz);
	    query.select(variableRoot);

	    return entityManager.createQuery(query).getResultList();
	}

	protected CriteriaBuilder getBuilder() {
		return entityManager.getCriteriaBuilder();
	}
	
	protected TypedQuery<T> build(CriteriaQuery<T> query) {
		 return entityManager.createQuery(query);
	}

}
