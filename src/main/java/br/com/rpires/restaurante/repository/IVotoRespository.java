/**
 * 
 */
package br.com.rpires.restaurante.repository;

import br.com.rpires.restaurante.entity.RestauranteDoDia;
import br.com.rpires.restaurante.entity.Voto;
import br.com.rpires.restaurante.exception.SystemException;

/**
 * @author digao
 *
 */
public interface IVotoRespository extends IRepository<Voto>{

	public RestauranteDoDia finalizarVotacao() throws SystemException;
}
