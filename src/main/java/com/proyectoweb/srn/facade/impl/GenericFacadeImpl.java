/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author TSI
 * @param <T>
 * @param <E>
 */
public abstract class GenericFacadeImpl<T, E extends Serializable> implements GenericFacade<T, E> {

    private final Class<T> entityClass;
    private EntityManager em;

    /**
     *
     * @param entityClass
     */
    public GenericFacadeImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     *
     * @return
     */
    protected abstract EntityManager getEntityManager();

    /**
     *
     * @param entity
     */
    @Override
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     *
     * @param entity
     */
    @Override
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     *
     * @param entity
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        List<T> list = null;
        try {
            list = getEntityManager().createQuery("select object(o) from " + entityClass.getSimpleName() + " as o").getResultList();
        } catch (Exception e) {
            System.out.println("error metodo findAll: " + e.getMessage() + " level: " + Level.SEVERE + " .::. " + e);
//            LogUtil.log("error metodo findAll:" + egetMessage(), Level.SEVERE, e);
        }
        return list;
    }

    /**
     *
     * @return
     */
    public Integer findMax() {
        Integer id = 1;
        try {
            Integer maxId = (Integer) getEntityManager().createQuery("select max(o.id) from " + entityClass.getSimpleName() + " as o").getSingleResult();
            if (maxId != null) {
                maxId++;
                id = maxId;
            }
        } catch (Exception e) {
            System.out.println("error metodo findMax: " + e.getMessage() + "level: " + Level.SEVERE + " .::. " + e);
//            LogUtil.log("error metodo findMax:" + e.getMessage(), Level.SEVERE, e);
        }
        return id;

    }

    /**
     *
     * @return
     */
    public String findMaxString() {
        String id = "01";
        try {
            String maxId = (String) getEntityManager().createQuery("select max(o.id) from " + entityClass.getSimpleName() + " as o").getSingleResult();

            if (null != maxId) {
                Integer numeroMaximo = Integer.parseInt(maxId);
                numeroMaximo++;

                id = numeroMaximo.toString();
                if (id.length() == 1) {
                    id = "0" + id;
                }
            }
        } catch (Exception e) {
            System.out.println("error metodo findMaxString: " + e.getMessage() + "level: " + Level.SEVERE + " .::. " + e);
//            LogUtil.log("error metodo findMaxString:" + e.getMessage(), Level.SEVERE, e);
        }
        return id;
    }

    /**
     *
     * @param startingAt
     * @param maxPerPage
     * @return
     */
    @Override
    public List<T> findByCriteria(int startingAt, int maxPerPage) {
        List<T> list = null;
        try {
            Query query = getEntityManager().createQuery("select object(o) from " + entityClass.getSimpleName() + " as o");
            query.setFirstResult(startingAt);
            query.setMaxResults(maxPerPage);
            list = query.getResultList();
        } catch (Exception e) {
            System.out.println("error metodo findByCriteria: " + e.getMessage() + "level: " + Level.SEVERE + " .::. " + e);
//            LogUtil.log("error metodo findAll:" + e.getMessage(), Level.SEVERE, e);
        }
        return list;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer countAll() {
        Integer id = 1;
        try {
            Integer maxId = (Integer) getEntityManager().createQuery("select count(o.id) from " + entityClass.getSimpleName() + " as o").getSingleResult();
            if (maxId != null) {
                maxId++;
                id = maxId;
            }
        } catch (Exception e) {
            System.out.println("error metodo countAll: " + e.getMessage() + "level: " + Level.SEVERE + " .::. " + e);
//            LogUtil.log("error metodo findMax:" + e.getMessage(), Level.SEVERE, e);
        }
        return id;
    }
}
