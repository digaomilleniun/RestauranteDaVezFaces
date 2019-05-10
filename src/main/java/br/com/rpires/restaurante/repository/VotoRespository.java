/**
 * 
 */
package br.com.rpires.restaurante.repository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.com.rpires.restaurante.entity.RestauranteDoDia;
import br.com.rpires.restaurante.entity.Voto;
import br.com.rpires.restaurante.exception.SystemException;

/**
 * @author digao
 *
 */
@Stateless
public class VotoRespository extends AbstractReporitory<Voto> implements IVotoRespository {
	
	@Inject
	private IRestauranteDoDiaRepository restauranteDiaRepository;

	protected VotoRespository() {
		super(Voto.class);
	}
	
	private Voto getRestauranteMaisVotado() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT new Voto(count(1), v.restaurante) FROM Voto v ");
		sb.append("group by v.restaurante");
		
		TypedQuery<Voto> query = entityManager.createQuery(sb.toString(), Voto.class);
		List<Voto> results = query.getResultList();
		Voto voto = null;
		
		if (results != null && results.size() > 1) {
			 voto = results.stream().max(Comparator.comparing(Voto::getQuantidade)).orElseThrow(NoSuchElementException::new);
		} else if (results != null && results.size() == 1) {
			voto = results.get(0);
		}
		return voto;
	}

	public RestauranteDoDia elegerRestaurante() throws SystemException {
		Voto voto = getRestauranteMaisVotado();
		
		if (voto != null) {
			List<RestauranteDoDia> results = restauranteDiaRepository.buscarRestauranteVencedorNaSemana(new Date());
			if (results != null && results.size() > 0) {
				throw new SystemException("Restaurante já foi escolhido está semana");
			}
			
			RestauranteDoDia restDoDia = new RestauranteDoDia();
			restDoDia.setRestaurante(voto.getRestaurante());
			restDoDia.setQtdVotos(voto.getQuantidade());
			restDoDia.setUltimaEscolha(new Date());
			
			restauranteDiaRepository.salvar(restDoDia);
			
			return restDoDia;
		}
		
		throw new SystemException("Não existe votação  para os restaurantes até o momento.");
		
	}
	
	@Override
	public RestauranteDoDia finalizarVotacao() throws SystemException {
		RestauranteDoDia rest = elegerRestaurante();
		return rest;
		
	}

}
