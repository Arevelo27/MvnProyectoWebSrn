/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade.impl;

import com.proyectoweb.srn.facade.SrnTblRolFacade;
import com.proyectoweb.srn.modelo.SrnTblRol;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TSI
 */
@Stateless
public class SrnTblRolFacadeImpl extends GenericFacadeImpl<SrnTblRol, Integer> implements SrnTblRolFacade {

    @PersistenceContext(unitName = UtilidadesSeguridad.NOMBRE_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SrnTblRolFacadeImpl() {
        super(SrnTblRol.class);
    }

    @Override
    public Integer findMaxId() {
        Integer id = 1;
        try {
            Integer maxId = (Integer) em.createNamedQuery("SrnTblRol.findMaxId").getSingleResult();
            if (maxId != null) {
                maxId++;
                id = maxId;
            }
        } catch (Exception e) {
            System.out.println("error metodo findMaxId: " + e.getMessage() + "level: " + Level.SEVERE + " .::. " + e);
//            LogUtil.log("error metodo findMax:" + e.getMessage(), Level.SEVERE, e);
        }
        return id;

    }

    @Override
    public List<SrnTblRol> buscarTodos() throws Exception {
        return findAll();
    }
}
