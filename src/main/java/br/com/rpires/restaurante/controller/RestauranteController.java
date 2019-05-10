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
import br.com.rpires.restaurante.repository.IRestauranteRespository;

/**
 * @author digao
 *
 */
@Named
@ViewScoped
public class RestauranteController implements Serializable {

	private static final long serialVersionUID = 8173871653187869433L;
	
	@Inject
	private IRestauranteRespository respository;
	
	private List<Restaurante> restaurantes;
	
	private Restaurante restaurante;
	
	private Date dataSelecionada;
	
	@PostConstruct
    public void init() {
		restaurante = new Restaurante();
		restaurantes = listar();
	}
	
	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public List<Restaurante> listar() {
		return respository.listar();
	}

	public String novoRestaurante() {
		return "/pages/restauranteForm.xhtml";
	}
	
	public String salvar() {
		try {
			respository.salvar(restaurante);
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO,"SUCESSO", "Restaurante cadastrado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", "CONTATE O ADMINISTRADOR DO SISTEMA"));
		}
		return "/index.xhtml";
	}
	
	public String redirectVotar() {
		return "/pages/votar.xhtml";
	}
	
	public void onDateSelect(SelectEvent event) {
        Date dataSelecionada = (Date) event.getObject();
        FacesContext.getCurrentInstance().getAttributes().put("dataSelecionada", dataSelecionada);
        restaurantes = respository.listar(dataSelecionada);
    }

}
