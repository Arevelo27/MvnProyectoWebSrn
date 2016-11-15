/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade.impl;

import com.proyectoweb.srn.facade.SrnTblMateriaFacade;
import com.proyectoweb.srn.modelo.SrnTblMateria;
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
public class SrnTblMateriaFacadeImpl extends GenericFacadeImpl<SrnTblMateria, Integer> implements SrnTblMateriaFacade {

    @PersistenceContext(unitName = UtilidadesSeguridad.NOMBRE_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SrnTblMateriaFacadeImpl() {
        super(SrnTblMateria.class);
    }

    @Override
    public List<SrnTblMateria> findByCriterio(String criterio) {
        List<SrnTblMateria> list = null;
        try {
            list = em.createNamedQuery("SrnTblMateria.findByCriterio").setParameter("criterio", "%" + criterio.trim() + "%").getResultList();
        } catch (Exception e) {
            System.out.println("Clase [SrnTblMateriaFacadeImpl]: error metodo findByCriterio: " + e.getMessage() + " level: " + Level.SEVERE + " .::. " + e);
//            LogUtil.log("error metodo findAll:" + egetMessage(), Level.SEVERE, e);
        }
        return list;
    }

}
