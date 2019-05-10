/**
 * 
 */
package br.com.rpires.restaurante.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.rpires.restaurante.entity.RestauranteDoDia;
import br.com.rpires.restaurante.exception.SystemException;
import br.com.rpires.restaurante.util.DateUtil;

/**
 * @author digao
 *
 */
@Stateless
public class RestauranteDoDiaRepostory extends AbstractReporitory<RestauranteDoDia> implements IRestauranteDoDiaRepository {

	protected RestauranteDoDiaRepostory() {
		super(RestauranteDoDia.class);
	}

	@Override
	public List<RestauranteDoDia> buscarRestauranteVencedorNaSemana(Date data) throws SystemException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT r FROM RestauranteDoDia r ");
		sb.append("where r.ultimaEscolha between :dtInicio and :dtFim");
		
		TypedQuery<RestauranteDoDia> query = entityManager.createQuery(sb.toString(), RestauranteDoDia.class);
		query.setParameter("dtInicio", DateUtil.atStartOfDay(DateUtil.getPrimeiroDiaSemana(data)));
		query.setParameter("dtFim", DateUtil.atEndOfDay(DateUtil.getUltimoDiaSemana(data)));
		
		return  query.getResultList();
	}

	@Override
	public List<RestauranteDoDia> buscarRestauranteVencedor(Date data) throws SystemException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT r FROM RestauranteDoDia r ");
		sb.append("where r.ultimaEscolha between :dtInicio and :dtFim");
		
		TypedQuery<RestauranteDoDia> query = entityManager.createQuery(sb.toString(), RestauranteDoDia.class);
		query.setParameter("dtInicio", DateUtil.atStartOfDay(data));
		query.setParameter("dtFim", DateUtil.atEndOfDay(data));
		
		return  query.getResultList();
	}

}
