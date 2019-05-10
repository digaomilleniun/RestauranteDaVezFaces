/**
 * 
 */
package br.com.rpires.restaurante.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author digao
 *
 */
@StaticMetamodel(Voto.class)
public class Voto_ {

	public static volatile SingularAttribute<Voto, Integer> id;
	
	public static volatile SingularAttribute<Voto, String> nome;
	
public static volatile SingularAttribute<Voto, Restaurante> restaurante;
	
}
