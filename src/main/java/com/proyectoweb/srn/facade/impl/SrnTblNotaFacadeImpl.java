/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade.impl;

import com.proyectoweb.srn.facade.SrnTblNotaFacade;
import com.proyectoweb.srn.modelo.SrnTblNota;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TSI
 */
@Stateless
public class SrnTblNotaFacadeImpl extends GenericFacadeImpl<SrnTblNota, Integer> implements SrnTblNotaFacade{
    @PersistenceContext(unitName = UtilidadesSeguridad.NOMBRE_PERSISTENCIA)
    private EntityManager em;

@Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SrnTblNotaFacadeImpl() {
        super(SrnTblNota.class);
    }

    @Override
    public Integer findMaxId() throws Exception {
        Integer id = 1;
        try {
            Integer maxId = (Integer) em.createNamedQuery("SrnTblNota.findMaxId").getSingleResult();
            if (maxId != null) {
                maxId++;
                id = maxId;
            }
        } catch (Exception e) {
            System.out.println("Clase [SrnTblNotaFacadeImpl]: error metodo findMaxId: " + e.getMessage() + "level: " + Level.SEVERE + " .::. " + e);
        }
        return id;
    }

    @Override
    public List<SrnTblNota> buscarTodos() throws Exception {
        return findAll();
    }

    @Override
    public List<SrnTblNota> buscarNotasAsignadasUsuario(Integer userDoc, Integer userEst) throws Exception {
        List<SrnTblNota> list = null;
        try {
            list = em.createNamedQuery("SrnTblNota.findByMateriasAsignadas").setParameter("numIdUsuario", userEst).setParameter("numIdDocente", userDoc).getResultList();
        } catch (Exception e) {
            System.out.println("Clase [SrnTblNotaFacadeImpl]: error metodo findAll: " + e.getMessage() + " level: " + Level.SEVERE + " .::. " + e);
        }
        return list;
    }
    
    @Override
    public SrnTblNota findMateriaUser(long codigo) throws Exception {
        SrnTblNota usuario = null;
        try {
            Query q = em.createNamedQuery("SrnTblNota.findByNotaMateriaUser", SrnTblNota.class).setParameter("codMateriaUser", codigo);
            usuario = (SrnTblNota) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Clase [SrnTblNotaFacadeImpl]: error metodo findMateriaUser: " + e.getMessage() + " level: " + Level.SEVERE + " .::. " + e);
        }
        return usuario;
    }
}
