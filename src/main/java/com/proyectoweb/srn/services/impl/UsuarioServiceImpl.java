/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblUsuarioFacade;
import com.proyectoweb.srn.modelo.SrnTblUsuario;
import com.proyectoweb.srn.services.UsuarioService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(UsuarioServiceImpl.NAME_SERVICE)
public class UsuarioServiceImpl extends GenericServiceImpl<SrnTblUsuario, Integer> implements UsuarioService {

    static final String NAME_SERVICE = "usuarioService";

    @Inject
    private SrnTblUsuarioFacade usuarioFacade;

    @Override
    public GenericFacade<SrnTblUsuario, Integer> getFacade() {
        return usuarioFacade;
    }

    @Override
    public Integer findMaxId() throws Exception {
        return usuarioFacade.findMaxId();
    }

    @Override
    public List<SrnTblUsuario> findAll() {
        return usuarioFacade.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SrnTblUsuario> findByCriterio(String criterio) {
        return usuarioFacade.findByCriterio(criterio);
    }


}
