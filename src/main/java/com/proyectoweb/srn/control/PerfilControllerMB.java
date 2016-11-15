/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.control;

import com.proyectoweb.srn.modelo.SrnTblUsuario;
import com.proyectoweb.srn.services.EstadoService;
import com.proyectoweb.srn.services.GeneroService;
import com.proyectoweb.srn.services.RolService;
import com.proyectoweb.srn.services.TipoDocService;
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
@ManagedBean(name = "perfilBn")
@ViewScoped
public class PerfilControllerMB implements Serializable {

    private SrnTblUsuario user;
    private UsuarioTO usuarioSession;
    @Inject
    private UsuarioService usuarioService;
    @Inject
    private EstadoService estadoService;
    @Inject
    private RolService rolService;
    @Inject
    private GeneroService generoService;
    @Inject
    private TipoDocService tipoDocService;

    private boolean edit = false;
    private boolean form = false;

    private int id;
    private long documento;
    private String nombre;
    private String apellido;
    private String login;
    private String password;
    private String email;
    private String estado;
    private String genero;
    private int rol;
    private int tipoDocumento;

    @PostConstruct
    public void init() {
        usuarioSession();
        limpiaCampos();
        
        id = usuarioSession.getIdUser();
        user = usuarioService.findById(id);
        documento = user.getCodDocumento();
        nombre = user.getNombre();
        apellido = user.getApellido();
        login = user.getLogin();
        email = user.getEmail();
        estado = user.getEstado().getStrCodEstado();
        genero = user.getGenero().getStrCodGenero();
        rol = user.getCodRol().getNumIdRol();
        tipoDocumento = user.getTipoDocumento().getStrCodTipoDoc();

        form = true;
        edit = false;
    }

    public String aceptar() {
        if (preAction()) {
            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setEmail(email);
            user.setEstado(estadoService.findById(estado));
            user.setGenero(generoService.findById(genero));
            user.setCodRol(rolService.findById(rol));
            user.setTipoDocumento(tipoDocService.findById(tipoDocumento));
            usuarioService.edit(user);
            mensajeInfo("Actualizacón exitosa");
        }
        return "";
    }

    public boolean preAction() {
        boolean accion = true;

        if (estadoService.findById(estado) == null) {
            mensajeError("Estado no encontrado");
            accion = false;
        }

        if (rolService.findById(rol) == null) {
            mensajeError("Rol no encontrado");
            accion = false;
        }

        if (generoService.findById(genero) == null) {
            mensajeError("Genero no encontrado");
            accion = false;
        }

        if (tipoDocService.findById(tipoDocumento) == null) {
            mensajeError("Tipo documento no encontrado");
            accion = false;
        }

        if (!FacesUtils.validaEmail(email)) {
            mensajeError("Por favor valide el email");
            accion = false;
        }
        return accion;
    }

    public void usuarioSession() {
        try {
            if (FacesUtils.getSession().getAttribute("usuario") == null) {
                System.out.println("No hay registro de usuario");
                UtilidadesSeguridad.getControlSession("endsession.jsp");
            } else {
                usuarioSession = (UsuarioTO) FacesUtils.getSession().getAttribute("usuario");
                nombre = usuarioSession.getNombre() + " " + usuarioSession.getApellidos();
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

    public void limpiaCampos() {
        user = new SrnTblUsuario();
        id = 0;
        documento = 0;
        nombre = "";
        apellido = "";
        login = "";
        email = "";
        estado = "";
        genero = "";
        rol = 0;
        tipoDocumento = 0;
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

    public TipoDocService getTipoDocService() {
        return tipoDocService;
    }

    public void setTipoDocService(TipoDocService tipoDocService) {
        this.tipoDocService = tipoDocService;
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

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
