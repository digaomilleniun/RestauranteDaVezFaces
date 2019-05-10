/**
 * 
 */
package br.com.rpires.restaurante.repository;

import java.util.Date;
import java.util.List;

import br.com.rpires.restaurante.entity.Restaurante;

/**
 * @author digao
 *
 */
public interface IRestauranteRespository extends IRepository<Restaurante>{

	public Restaurante maiorVotacao();
	
	public List<Restaurante> listar(Date data);

	public List<Restaurante> buscarHistorico(Date data);
}
