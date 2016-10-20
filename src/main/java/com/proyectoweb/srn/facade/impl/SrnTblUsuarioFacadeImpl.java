/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade.impl;

import com.proyectoweb.srn.facade.SrnTblUsuarioFacade;
import com.proyectoweb.srn.modelo.SrnTblUsuario;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
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
public class SrnTblUsuarioFacadeImpl extends GenericFacadeImpl<SrnTblUsuario, String> implements SrnTblUsuarioFacade{
    @PersistenceContext(unitName = UtilidadesSeguridad.NOMBRE_PERSISTENCIA)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SrnTblUsuarioFacadeImpl() {
        super(SrnTblUsuario.class);
    }
    
    /**
     *
     * @param login
     * @param password
     * @return
     */
    @Override
    public boolean LoginControl(String login, String password) {
        try {
            Query q = em.createNamedQuery("SrnTblUsuario.loginControl", SrnTblUsuario.class).setParameter("username", login).setParameter("password", password);
            SrnTblUsuario usuario = (SrnTblUsuario) q.getSingleResult();
            if (usuario != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("error metodo LoginControl: " + e.getMessage() + " level: " + Level.SEVERE + " .::. " + e);
            return false;
        }
    }

    /**
     *
     * @param login
     * @param contrasena
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public SrnTblUsuario login(String login, String contrasena) throws Exception {
        SrnTblUsuario usuario = null;
        try {
            Query q = em.createNamedQuery("SrnTblUsuario.loginControl", SrnTblUsuario.class).setParameter("username", login).setParameter("password", contrasena);
            usuario = (SrnTblUsuario) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("error metodo LoginControl: " + e.getMessage() + " level: " + Level.SEVERE + " .::. " + e);
        }
        return usuario;
    }

}
