/**
 * 
 */
package br.com.rpires.restaurante.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;

/**
 * @author Rodrigo Pires
 *
 */
@Entity
public class Voto implements Serializable {

	private static final long serialVersionUID = 4474578540302506607L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SQ_VOTO")
	@SequenceGenerator(name = "SQ_VOTO", sequenceName = "SQ_VOTO")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "RESTAURANTE_ID")
	private Restaurante restaurante;
	
	@Column(name = "NOME_PROFISSIONAL", nullable = false)
	private String nomeProfissional;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA", nullable = false)
	private Date data;
	
	@Transient
	private Long quantidade;
	
	public Voto() {
		this.data = new Date();
	}
	
	public Voto(Long count, Restaurante restaurante) {
		this.quantidade = count;
		this.restaurante= restaurante;
	}
	
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNomeProfissional() {
		return nomeProfissional;
	}

	public void setNomeProfissional(String nomeProfissional) {
		this.nomeProfissional = nomeProfissional;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((nomeProfissional == null) ? 0 : nomeProfissional.hashCode());
		result = prime * result + ((restaurante == null) ? 0 : restaurante.hashCode());
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
		Voto other = (Voto) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (nomeProfissional == null) {
			if (other.nomeProfissional != null)
				return false;
		} else if (!nomeProfissional.equals(other.nomeProfissional))
			return false;
		if (restaurante == null) {
			if (other.restaurante != null)
				return false;
		} else if (!restaurante.equals(other.restaurante))
			return false;
		return true;
	}
}
