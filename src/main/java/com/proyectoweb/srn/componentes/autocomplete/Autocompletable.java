/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.componentes.autocomplete;

import java.util.List;

/**
 *
 * @author TSI
 * @param <T>
 */
public interface Autocompletable<T> {

    abstract List<T> completarBusqueda(String query);
}
