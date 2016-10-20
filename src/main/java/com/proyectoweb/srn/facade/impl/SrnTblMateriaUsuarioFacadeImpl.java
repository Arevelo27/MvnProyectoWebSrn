/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade.impl;

import com.proyectoweb.srn.facade.SrnTblMateriaUsuarioFacade;
import com.proyectoweb.srn.modelo.SrnTblMateriaUsuario;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TSI
 */
@Stateless
public class SrnTblMateriaUsuarioFacadeImpl extends GenericFacadeImpl<SrnTblMateriaUsuario, Integer> implements SrnTblMateriaUsuarioFacade {

    @PersistenceContext(unitName = UtilidadesSeguridad.NOMBRE_PERSISTENCIA)
    private EntityManager em;

@Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SrnTblMateriaUsuarioFacadeImpl() {
        super(SrnTblMateriaUsuario.class);
    }

}
