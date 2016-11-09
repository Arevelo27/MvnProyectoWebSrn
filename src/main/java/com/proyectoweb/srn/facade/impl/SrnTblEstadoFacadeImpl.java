/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade.impl;

import com.proyectoweb.srn.facade.SrnTblEstadoFacade;
import com.proyectoweb.srn.modelo.SrnTblEstado;
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
public class SrnTblEstadoFacadeImpl extends GenericFacadeImpl<SrnTblEstado, String> implements SrnTblEstadoFacade {

    @PersistenceContext(unitName = UtilidadesSeguridad.NOMBRE_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SrnTblEstadoFacadeImpl() {
        super(SrnTblEstado.class);
    }

    @Override
    public List<SrnTblEstado> buscarTodos() throws Exception {
        return findAll();
    }

}
