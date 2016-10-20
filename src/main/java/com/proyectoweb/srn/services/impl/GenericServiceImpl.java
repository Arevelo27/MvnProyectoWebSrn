/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.services.GenericService;
import java.io.Serializable;
import java.util.List;

public abstract class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {

    public abstract GenericFacade<T, PK> getFacade();

    @Override
    public void create(T entity) {
        getFacade().create(entity);
    }

    @Override
    public void edit(T entity) {
        getFacade().edit(entity);
    }

    @Override
    public void remove(T entity) {
        getFacade().remove(entity);
    }

    @Override
    public T find(Object id) {
        return getFacade().find(id);
    }

    @Override
    public List<T> findAll() {
        return getFacade().findAll();
    }

    @Override
    public Integer findMax() {
        return getFacade().findMax();
    }

    @Override
    public String findMaxString() {
        return getFacade().findMaxString();
    }

    @Override
    public List<T> findByCriteria(int startingAt, int maxPerPage) {
        return getFacade().findByCriteria(startingAt, maxPerPage);
    }

    @Override
    public Integer countAll() {
        return getFacade().countAll();
    }

}
