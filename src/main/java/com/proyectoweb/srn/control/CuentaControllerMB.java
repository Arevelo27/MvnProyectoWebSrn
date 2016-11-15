/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.control;

import com.proyectoweb.srn.modelo.SrnTblUsuario;
import com.proyectoweb.srn.services.UsuarioService;
import com.proyectoweb.srn.to.UsuarioTO;
import com.proyectoweb.srn.utilidades.FacesUtils;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author TSI
 */
@ManagedBean(name = "cuentaBn")
@ViewScoped
public class CuentaControllerMB implements Serializable {

    private SrnTblUsuario user;
    private UsuarioTO usuarioSession;
    @Inject
    private UsuarioService usuarioService;

    private boolean edit = false;
    private boolean form = false;

    private int id;
    private String passwordOld;
    private String password;

    @PostConstruct
    public void init() {
        usuarioSession();
        id = usuarioSession.getIdUser();
        user = usuarioService.findById(id);

        form = true;
        edit = false;
    }

    public String aceptar() {
        if (preAction()) {
            user.setPassword(encriptarClave(this.password));
            usuarioService.edit(user);
            mensajeInfo("Actualizacón exitosa");
        }
        return "";
    }

    public boolean preAction() {
        boolean accion = true;
        if (!user.getPassword().equals(encriptarClave(this.passwordOld))) {
            mensajeError("La contraseña que edito no existe");
            accion = false;
        } else if (user.getPassword().equals(encriptarClave(password))) {
            mensajeError("Esta contraseña ya ha sido registrada anteriormente");
            accion = false;
        }
        return accion;
    }

    public String encriptarClave(String clave) {
        String password_md5;
        return password_md5 = UtilidadesSeguridad.getMD5(clave.trim().toUpperCase());
    }

    public void usuarioSession() {
        try {
            if (FacesUtils.getSession().getAttribute("usuario") == null) {
                System.out.println("No hay registro de usuario");
                UtilidadesSeguridad.getControlSession("endsession.jsp");
            } else {
                usuarioSession = (UsuarioTO) FacesUtils.getSession().getAttribute("usuario");
            }
        } catch (IllegalStateException ie) {
            FacesUtils.controlLog("SEVERE", "Error [IllegalStateException] en la clase NotaActualControllerMB: " + ie.getMessage());
            UtilidadesSeguridad.getControlSession("endsession.jsp");
        } catch (ViewExpiredException e) {
            FacesUtils.controlLog("SEVERE", "Error [ViewExpiredException] en la clase NotaActualControllerMB: " + e.getMessage());
            UtilidadesSeguridad.getControlSession("endsession.jsp");
        } catch (Exception ex) {
            FacesUtils.controlLog("SEVERE", "Error [Exception] en la clase ReglasDeNavegacion: " + ex.getMessage());
        }
    }

    public void mensajeError(String cadena) {
        RequestContext.getCurrentInstance().update("growl");
        FacesUtils.addErrorMessage(cadena);
    }

    public void mensajeInfo(String cadena) {
        RequestContext.getCurrentInstance().update("growl");
        FacesUtils.addInfoMessage(cadena);
    }

    public SrnTblUsuario getUser() {
        return user;
    }

    public void setUser(SrnTblUsuario user) {
        this.user = user;
    }

    public UsuarioTO getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(UsuarioTO usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

}
