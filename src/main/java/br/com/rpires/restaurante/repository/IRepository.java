/**
 * 
 */
package br.com.rpires.restaurante.repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author digao
 *
 */
public interface IRepository<T extends Serializable> {

	public void salvar(T entity);
	
	public List<T> listar();
}
