/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.control;

import javax.annotation.PostConstruct;

/**
 *
 * @author Andres-Desarrollo2
 * @param <T>
 */
public interface GenericBean<T> {

    @PostConstruct
    void init();

    void buscarTodos() throws Exception;

    void verForm();

    void volverForm();

    void renderizarItem(T entity);

    void eliminarItem(T entity);

    String aceptar();

    boolean preAction();
}
