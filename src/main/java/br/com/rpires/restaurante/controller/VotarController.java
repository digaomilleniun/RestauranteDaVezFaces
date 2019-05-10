/**
 * 
 */
package br.com.rpires.restaurante.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.rpires.restaurante.entity.Restaurante;
import br.com.rpires.restaurante.entity.RestauranteDoDia;
import br.com.rpires.restaurante.entity.Voto;
import br.com.rpires.restaurante.exception.SystemException;
import br.com.rpires.restaurante.repository.IRestauranteDoDiaRepository;
import br.com.rpires.restaurante.repository.IRestauranteRespository;
import br.com.rpires.restaurante.repository.IVotoRespository;

/**
 * @author digao
 *
 */
@Named
@ViewScoped
public class VotarController implements Serializable {

	private static final long serialVersionUID = -952401374499859389L;

	@Inject
	private IRestauranteRespository respository;
	
	@Inject
	private IVotoRespository votoRepository;
	
	@Inject
	private IRestauranteDoDiaRepository restauranteDiaRepository;
	
	private List<Restaurante> restaurantes;
	
	private List<Restaurante> restaurantesHistorico;
	
	private Restaurante restauranteSelecionado;
	
	private RestauranteDoDia restauranteVencedor;
	
	private Voto voto;
	
	private Date dataSelecionada;
	
	@PostConstruct
    public void init() {
		try {
			
			voto = new Voto();
			restauranteSelecionado = new Restaurante();
			restaurantes = listar();
			restaurantesHistorico = buscarHistorico(new Date());
			
			buscarRestauranteVencedor(getDataSelecionadaIndex());
			
		} catch (SystemException e) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", "CONTATE O ADMINISTRADOR DO SISTEMA"));
		}
		
		adicionarScopoFlash();
	}

	private Date getDataSelecionadaIndex() {
		Date dataSelecionada = (Date) FacesContext.getCurrentInstance().getAttributes().get("dataSelecionada");
		return dataSelecionada;
	}

	private void buscarRestauranteVencedor(Date dataSelecionada) throws SystemException {
		
		if (dataSelecionada == null) {
			dataSelecionada = new Date();
		}
		
		List<RestauranteDoDia> rest = restauranteDiaRepository.buscarRestauranteVencedor(dataSelecionada);
		
		if (rest != null && rest.size() > 0) {
			restauranteVencedor = rest.get(0);
		} else {
			restauranteVencedor = null;
		}
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Restaurante getRestauranteSelecionado() {
		return restauranteSelecionado;
	}

	public void setRestauranteSelecionado(Restaurante restauranteSelecionado) {
		this.restauranteSelecionado = restauranteSelecionado;
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public List<Restaurante> getRestaurantesHistorico() {
		return restaurantesHistorico;
	}

	public void setRestaurantesHistorico(List<Restaurante> restaurantesHistorico) {
		this.restaurantesHistorico = restaurantesHistorico;
	}

	public RestauranteDoDia getRestauranteVencedor() {
		return restauranteVencedor;
	}

	public void setRestauranteVencedor(RestauranteDoDia restauranteVencedor) {
		this.restauranteVencedor = restauranteVencedor;
	}

	public List<Restaurante> listar() {
		return respository.listar();
	}
	
	private List<Restaurante> buscarHistorico(Date data) {
		return respository.buscarHistorico(data);
	}
	
	public String votar() {
		try {
			voto.setRestaurante(restauranteSelecionado);
			voto.setData(new Date());
			votoRepository.salvar(voto);
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO,"SUCESSO", "Voto computado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", "CONTATE O ADMINISTRADOR DO SISTEMA"));
		}
		
		adicionarScopoFlash();
		
		return "/index.xhtml?faces-redirect=true";
	}
	
	public String redirectIndex() {
		return "/index.xhtml";
	}
	
	public String verHistorico() {
		return "/pages/historico.xhtml";
	}
	
	public String finalizarVotacao() {
		try {
			restauranteVencedor = votoRepository.finalizarVotacao();
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO,"SUCESSO", "Votação finalizada com sucesso"));
		} catch (SystemException e) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", e.getMessage()));
		}
		
		adicionarScopoFlash();
		 
		return "/index.xhtml?faces-redirect=true";
	}

	private void adicionarScopoFlash() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public void onDateSelect(SelectEvent event) {
        Date dataSelecionada = (Date) event.getObject();
        restaurantesHistorico = buscarHistorico(dataSelecionada);
    }
	
	public Boolean existeRestauranteVencedor() {
		try {
					
			buscarRestauranteVencedor(getDataSelecionadaIndex());
			
			if (restauranteVencedor != null) {
				return true;
			}
			
		} catch (SystemException e) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", e.getMessage()));
		}
		adicionarScopoFlash();
		return false;
		
		
	}
}
