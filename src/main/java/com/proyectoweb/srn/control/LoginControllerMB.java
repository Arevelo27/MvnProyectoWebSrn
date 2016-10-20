/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.control;

import com.proyectoweb.srn.modelo.SrnTblUsuario;
import com.proyectoweb.srn.services.LoginService;
import com.proyectoweb.srn.to.UsuarioTO;
import com.proyectoweb.srn.utilidades.FacesUtils;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import java.io.Serializable;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author TSI
 */
@ManagedBean(name = "login")
@RequestScoped
public class LoginControllerMB implements Serializable {

//    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private UsuarioTO usuarioTo;
    private final HttpServletRequest httpServletRequest;
    private SrnTblUsuario usuario;
    boolean esNull;

//    @ManagedProperty(value = "#{loginService}")
    @Inject
    private LoginService loginService;

    /**
     *
     */
    public LoginControllerMB() {
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
        usuario = new SrnTblUsuario();
    }

    /**
     *
     * @return
     */
    public String loginControl() {
        try {
            usuarioTo = new UsuarioTO();
            String password_md5 = UtilidadesSeguridad.getMD5(this.password);
            System.out.println(password_md5);

            esNull = FacesUtils.isNotNull(username) && FacesUtils.isNotNull(password);
            if (esNull) {
                if (loginService.LoginControl(username, password_md5)) {
                    esNull = false;
                    usuario = loginService.login(username, password_md5);

                    usuarioTo.setApellidos(usuario.getApellido());
                    usuarioTo.setCodigo(usuario.getCodDocumento() + "");
                    usuarioTo.setContrasena(password_md5);
                    usuarioTo.setEstado(usuario.getEstado().getStrCodEstado());
                    usuarioTo.setNombre(usuario.getNombre());
                    usuarioTo.setRolCodigo(usuario.getCodRol().getStrDescripcion());
                    usuarioTo.setLogin(username);

                    FacesUtils.getSession().setAttribute("usuario", usuarioTo);
                    return "frmInicio.xhtml?faces-redirect=true";
                }
            } else {
                RequestContext.getCurrentInstance().update("growl");
                FacesUtils.addErrorMessage("Varificar Datos");
                return "";
            }
            RequestContext.getCurrentInstance().update("growl");
            FacesUtils.addErrorMessage("Usuario o Clave incorrecta!!!");

        } catch (ViewExpiredException e) {
            FacesUtils.controlLog("INFO", e.getMessage());
            UtilidadesSeguridad.getControlSession();
        } catch (Exception ex) {
            System.out.println("Error en la clase LoginControllerMB del metodo loginControl: " + ex.getMessage());
            FacesUtils.controlLog("SEVERE", "Error en la clase LoginControllerMB del metodo: " + ex.getMessage());
        }
        return "";
    }

    /**
     *
     */
    public void controlSession() {
        UtilidadesSeguridad.getControlSession();
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     *
     * @return
     */
//    public FsuperTblUsuarios getUsuario() {
//        return usuario;
//    }
    /**
     *
     * @param usuario
     */
//    public void setUsuario(FsuperTblUsuarios usuario) {
//        this.usuario = usuario;
//    }
}
