/**
 * 
 */
package br.com.rpires.restaurante.repository;

import java.util.Date;
import java.util.List;

import br.com.rpires.restaurante.entity.RestauranteDoDia;
import br.com.rpires.restaurante.exception.SystemException;

/**
 * @author digao
 *
 */
public interface IRestauranteDoDiaRepository extends IRepository<RestauranteDoDia> {

	public List<RestauranteDoDia> buscarRestauranteVencedorNaSemana(Date data) throws SystemException;
	
	public List<RestauranteDoDia> buscarRestauranteVencedor(Date data) throws SystemException;
}
