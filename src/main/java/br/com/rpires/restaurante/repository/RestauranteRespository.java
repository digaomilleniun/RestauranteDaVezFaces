/**
 * 
 */
package br.com.rpires.restaurante.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.rpires.restaurante.entity.Restaurante;
import br.com.rpires.restaurante.util.DateUtil;

/**
 * @author digao
 *
 */
@Stateless
public class RestauranteRespository extends AbstractReporitory<Restaurante> implements IRestauranteRespository {

	protected RestauranteRespository() {
		super(Restaurante.class);
	}

	@Override
	public List<Restaurante> listar() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT r FROM Restaurante r ");
		sb.append("group by r ");
		
		TypedQuery<Restaurante> query = entityManager.createQuery(sb.toString(), Restaurante.class);
		
		List<Restaurante> results = query.getResultList();
		results.forEach(item -> item.getVotos().size());
		return results;
	}
	
	@Override
	public List<Restaurante> listar(Date data) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT r FROM Restaurante r ");
		sb.append("where r.dataCadastro between :dtInicio and :dtFim ");
		sb.append("group by r ");
		
		TypedQuery<Restaurante> query = entityManager.createQuery(sb.toString(), Restaurante.class);
		query.setParameter("dtInicio", DateUtil.atStartOfDay(data));
		query.setParameter("dtFim", DateUtil.atEndOfDay(data));
		
		List<Restaurante> results = query.getResultList();
		results.forEach(item -> item.getVotos().size());
		return results;
	}

	@Override
	public Restaurante maiorVotacao() {
		return null;
	}

	@Override
	public List<Restaurante> buscarHistorico(Date data) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT r FROM Restaurante r ");
		sb.append("inner join r.votos v on r = v.restaurante ");
		sb.append("and v.data between :dtInicio and :dtFim ");
		sb.append("group by r ");
		
		TypedQuery<Restaurante> query = entityManager.createQuery(sb.toString(), Restaurante.class);
		query.setParameter("dtInicio", DateUtil.atStartOfDay(data));
		query.setParameter("dtFim", DateUtil.atEndOfDay(data));
		
		List<Restaurante> results = query.getResultList();
		results.forEach(item -> item.getVotos().size());
		return results;
	}

}
