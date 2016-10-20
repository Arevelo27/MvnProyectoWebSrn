/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.componentes.selectores;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Andres-Desarrollo2
 */
public abstract class SelectorBase<T, PK extends Serializable> {

    private List<SelectItem> lista;

    public abstract void cargarLista();

    public List<SelectItem> getLista() {
        return lista;
    }

    public void setLista(List<SelectItem> lista) {
        this.lista = lista;
    }
}
