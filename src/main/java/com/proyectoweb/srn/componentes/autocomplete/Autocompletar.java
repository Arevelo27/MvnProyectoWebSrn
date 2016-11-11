/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.componentes.autocomplete;

import java.util.List;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author TSI
 */
public abstract class Autocompletar<T> implements Autocompletable<T> {

    protected T seleccionado;
    protected String query;
    protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Autocompletar.class);

    /**
     *
     * @param query
     * @return
     */
    @Override
    public abstract List<T> completarBusqueda(String query);

    public abstract void seleccionar(SelectEvent evt);

    public T getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(T seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
