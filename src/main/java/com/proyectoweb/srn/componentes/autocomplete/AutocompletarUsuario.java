/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.componentes.autocomplete;

import com.proyectoweb.srn.modelo.SrnTblUsuario;
import com.proyectoweb.srn.services.UsuarioService;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.event.SelectEvent;

/**
 *
 * @author TSI
 */
public abstract class AutocompletarUsuario extends Autocompletar<SrnTblUsuario> implements Serializable {

    public abstract UsuarioService usuarioService();

    @Override
    public List<SrnTblUsuario> completarBusqueda(String query) {
        return usuarioService().findByCriterio(query);
    }

    @Override
    public void seleccionar(SelectEvent evt) {

        seleccionado = (SrnTblUsuario) evt.getObject();
        query = seleccionado.toString();
    }

    public Converter getConversorItems() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext context,
                    UIComponent component, String value) {
                if (value.trim().equals("") || value == null) {
                    return null;
                }

//		SrnTblUsuario c = usuarioService.findById(value);
                SrnTblUsuario c = usuarioService().findById(Integer.parseInt(value));
                seleccionado = c;
                return c;
            }

            @Override
            public String getAsString(FacesContext context,
                    UIComponent component, Object value) {
                if (value == null || value.equals("")) {
                    return "";
                } else {
                    return ((SrnTblUsuario) value).getIdUsuario().toString();
                }
            }
        };

    }
}
