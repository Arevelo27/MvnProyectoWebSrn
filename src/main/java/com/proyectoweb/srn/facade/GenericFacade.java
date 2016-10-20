/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author TSI
 * @param <T>
 * @param <PK>
 */
public interface GenericFacade<T, PK extends Serializable> {

//    Class<T> getEntityClass();
    
    void create(T entity);

    void edit(T entity);

    void remove(T entity);

    T find(Object id);

    List<T> findAll();

    Integer findMax();

    String findMaxString();

    List<T> findByCriteria(int startingAt, int maxPerPage);

    Integer countAll();

}
