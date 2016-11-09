/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade.impl;

import com.proyectoweb.srn.facade.SrnTblGeneroFacade;
import com.proyectoweb.srn.modelo.SrnTblGenero;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TSI
 */
@Stateless
public class SrnTblGeneroFacadeImpl extends GenericFacadeImpl<SrnTblGenero, String> implements SrnTblGeneroFacade {

    @PersistenceContext(unitName = UtilidadesSeguridad.NOMBRE_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SrnTblGeneroFacadeImpl() {
        super(SrnTblGenero.class);
    }

    @Override
    public List<SrnTblGenero> buscarTodos() throws Exception {
        return findAll();
    }

}
