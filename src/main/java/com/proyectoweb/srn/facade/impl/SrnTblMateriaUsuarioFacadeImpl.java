/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade.impl;

import com.proyectoweb.srn.facade.SrnTblMateriaUsuarioFacade;
import com.proyectoweb.srn.modelo.SrnTblMateriaUsuario;
import com.proyectoweb.srn.modelo.SrnTblUsuario;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.logging.Level;

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

    @Override
    public SrnTblMateriaUsuario findMateriaUser(Integer materia, Integer idDoc, Integer idEst) throws Exception {
        SrnTblMateriaUsuario materiaUsuario = null;
        try {
            Query q = em.createNamedQuery("SrnTblMateriaUsuario.findByMateriaUser", SrnTblMateriaUsuario.class)
                    .setParameter("numCodMateria", materia)
                    .setParameter("numCodDocente", idDoc)
                    .setParameter("numCodEstudiante", idEst);
            materiaUsuario = (SrnTblMateriaUsuario) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Clase [SrnTblMateriaUsuarioFacadeImpl]: error metodo findMateriaUser: " + e.getMessage() + " level: " + Level.SEVERE + " .::. " + e);
        }
        return materiaUsuario;
    }

}
