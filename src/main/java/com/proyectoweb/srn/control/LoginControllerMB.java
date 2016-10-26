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
import com.proyectoweb.srn.mail.MailService;
import com.proyectoweb.srn.mail.impl.MailServiceImpl;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String gmail_dir;
    private static final String CONTENT_MAIL_TEST = "Prueba del envío de correo electrónico.";
    private static final String TITLE_MAIL_TEST = "Test de envío de email.";
    private UsuarioTO usuarioTo;
    private final HttpServletRequest httpServletRequest;
    private SrnTblUsuario usuario;
    boolean esNull;

//    @ManagedProperty(value = "#{loginService}")
    @Inject
    private LoginService loginService;

    @Inject
    private MailService service;

    ;

    /**
     *
     */
    public LoginControllerMB() {
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
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
                } else {
                    mensajeError("Usuario o Clave incorrecta!!!");
                    return "";
                }
            } else {
                mensajeError("Los campos no pueden ser nulos");
                return "";
            }

        } catch (ViewExpiredException e) {
            FacesUtils.controlLog("INFO", e.getMessage());
            UtilidadesSeguridad.getControlSession("endsession.jsp");
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
        if (FacesUtils.getSession().getAttribute("usuario") == null) {
            UtilidadesSeguridad.getControlSession("endsession.jsp");
        }
    }

    public String recuperarClave() {
        try {
            esNull = FacesUtils.isNotNull(username) && FacesUtils.isNotNull(gmail_dir);
            if (esNull) {
                String vlrClave = username + (int) Math.floor(Math.random() * (10000 - 1 + 1) + (3));
                usuario = loginService.recuperarClave(username, gmail_dir);
                if (usuario != null) {
                    esNull = false;

                    boolean enviado = service.send(gmail_dir, usuario.toString(), vlrClave);
                    if (enviado) {
                        System.out.println("se envio");
                        String password_md5 = UtilidadesSeguridad.getMD5(vlrClave);
                        usuario.setPassword(password_md5);
                        loginService.edit(usuario);

                        username = "";
                        gmail_dir = "";

                        mensajeInfo("Correo enviado, por favor verifique su cuenta y vuelva a iniciar session");
                    } else {
                        System.out.println("No se envio");
                    }
                } else {
                    mensajeError("No existe este usuario");
                    return "";
                }
            } else {
                mensajeError("Varificar Datos");
                return "";
            }
        } catch (Exception e) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return "";
    }

    public void mensajeError(String cadena) {
        RequestContext.getCurrentInstance().update("growl");
        FacesUtils.addErrorMessage(cadena);
    }

    public void mensajeInfo(String cadena) {
        RequestContext.getCurrentInstance().update("growl");
        FacesUtils.addInfoMessage(cadena);
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

    public String getGmail_dir() {
        return gmail_dir;
    }

    public void setGmail_dir(String gmail_dir) {
        this.gmail_dir = gmail_dir;
    }

}
