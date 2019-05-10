/**
 * 
 */
package br.com.rpires.restaurante.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author digao
 *
 */
@StaticMetamodel(Restaurante.class)
public class Restaurante_ {

	public static volatile SingularAttribute<Restaurante, Integer> id;
	
	public static volatile SingularAttribute<Restaurante, String> nome;
	
	public static volatile ListAttribute<Restaurante, Voto> votos; 
}
