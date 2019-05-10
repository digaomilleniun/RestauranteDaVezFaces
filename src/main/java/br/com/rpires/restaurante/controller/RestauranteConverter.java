/**
 * 
 */
package br.com.rpires.restaurante.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rpires.restaurante.entity.Restaurante;

/**
 * @author digao
 *
 */
@FacesConverter(forClass = Restaurante.class)
public class RestauranteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()) {
			Restaurante restaurante = (Restaurante) component.getAttributes().get(value);
            return restaurante;
        }
        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		 if (value instanceof Restaurante) {
			 Restaurante entity= (Restaurante) value;
	            if (entity != null && entity instanceof Restaurante && entity.getId() != null) {
	            	component.getAttributes().put( entity.getId().toString(), entity);
	                return entity.getId().toString();
	            }
	        }
	        return "";
	}

}
