/**
 * 
 */
package br.com.rpires.restaurante.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.rpires.restaurante.util.DateUtil;


/**
 * @author Rodrigo Pires
 *
 */
@Entity
public class RestauranteDoDia implements Serializable {
	
	private static final long serialVersionUID = 4076302731590751353L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SQ_RESTAURANTE_DO_DIA")
	@SequenceGenerator(name = "SQ_RESTAURANTE_DO_DIA", sequenceName = "SQ_RESTAURANTE_DO_DIA")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "RESTAURANTE_ID")
	private Restaurante restaurante;
	
	@Column(name = "QTD_VOTOS", nullable = false)
	private Long qtdVotos;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ULTIMA_ESCOLHA", nullable = false)
	private Date ultimaEscolha;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Long getQtdVotos() {
		return qtdVotos;
	}

	public void setQtdVotos(Long qtdVotos) {
		this.qtdVotos = qtdVotos;
	}

	public Date getUltimaEscolha() {
		return ultimaEscolha;
	}

	public void setUltimaEscolha(Date ultimaEscolha) {
		this.ultimaEscolha = ultimaEscolha;
	}
	
	public String getDataFormatada() throws ParseException {
		return DateUtil.format(this.ultimaEscolha);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qtdVotos == null) ? 0 : qtdVotos.hashCode());
		result = prime * result + ((restaurante == null) ? 0 : restaurante.hashCode());
		result = prime * result + ((ultimaEscolha == null) ? 0 : ultimaEscolha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestauranteDoDia other = (RestauranteDoDia) obj;
		if (qtdVotos == null) {
			if (other.qtdVotos != null)
				return false;
		} else if (!qtdVotos.equals(other.qtdVotos))
			return false;
		if (restaurante == null) {
			if (other.restaurante != null)
				return false;
		} else if (!restaurante.equals(other.restaurante))
			return false;
		if (ultimaEscolha == null) {
			if (other.ultimaEscolha != null)
				return false;
		} else if (!ultimaEscolha.equals(other.ultimaEscolha))
			return false;
		return true;
	}
	

}
