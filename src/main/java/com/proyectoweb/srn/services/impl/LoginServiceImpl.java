/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblUsuarioFacade;
import com.proyectoweb.srn.modelo.SrnTblUsuario;
import com.proyectoweb.srn.services.LoginService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(LoginServiceImpl.NAME_SERVICE)
public class LoginServiceImpl extends GenericServiceImpl<SrnTblUsuario, String> implements LoginService {

    static final String NAME_SERVICE = "loginService";

    @Inject
    private SrnTblUsuarioFacade usuarioFacade;

    @Override
    public GenericFacade<SrnTblUsuario, String> getFacade() {
        return usuarioFacade;
    }

    @Override
    public SrnTblUsuario login(String login, String contrasena) throws Exception {
        return usuarioFacade.login(login, contrasena);
    }

    @Override
    public boolean LoginControl(String login, String contrasena) throws Exception {
        return usuarioFacade.LoginControl(login, contrasena);
    }

}
