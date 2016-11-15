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
import com.proyectoweb.srn.utilidades.FacesUtils;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author TSI
 */
@ManagedBean(name = "usuarioBn")
@ViewScoped
public class UsuarioControllerMB implements GenericBean<SrnTblUsuario>, Serializable {

    private SrnTblUsuario user;

//    @ManagedProperty(value = "#{usuarioService}")
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

    private List<SrnTblUsuario> listUser;

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
    @Override
    public void init() {
        try {
            listUser = new ArrayList<SrnTblUsuario>();
            buscarTodos();
        } catch (Exception e) {
            FacesUtils.controlLog("SEVERE", "Error en la clase UsuarioControllerMB del metodo init: " + e.getMessage());
        }
    }

    @Override
    public void buscarTodos() throws Exception {
        listUser = usuarioService.findAll();
    }

    @Override
    public void verForm() {
        user = new SrnTblUsuario();
        id = 0;
        documento = 0;
        nombre = "";
        apellido = "";
        login = "";
        password = "";
        email = "";
        estado = "";
        genero = "";
        rol = 0;
        tipoDocumento = 0;

        form = true;
        edit = false;
    }

    @Override
    public void volverForm() {
        try {
            form = false;
            edit = false;
            buscarTodos();
        } catch (Exception e) {
            FacesUtils.controlLog("SEVERE", "Error en la clase UsuarioControllerMB del metodo volverForm: " + e.getMessage());
        }
    }

    @Override
    public void renderizarItem(SrnTblUsuario u) {
        user = u;
        documento = user.getCodDocumento();
        nombre = user.getNombre();
        apellido = user.getApellido();
        login = user.getLogin();
        password = user.getPassword();
        email = user.getEmail();
        estado = user.getEstado().getStrCodEstado();
        genero = user.getGenero().getStrCodGenero();
        rol = user.getCodRol().getNumIdRol();
        tipoDocumento = user.getTipoDocumento().getStrCodTipoDoc();
        form = true;
        edit = true;
    }

    @Override
    public void eliminarItem(SrnTblUsuario u) {
        try {
            usuarioService.remove(u);
            listUser = usuarioService.findAll();
        } catch (Exception e) {
        }
    }

    @Override
    public String aceptar() {
        String navegacion = "";
        try {
            if (preAction()) {
                if (!edit) {
                    id = usuarioService.findMaxId();
                    if (usuarioService.findDocument(documento) == null) {
                        user.setIdUsuario(id);
                        user.setCodDocumento(documento);
                        user.setNombre(nombre);
                        user.setApellido(apellido);
                        user.setLogin(login.trim().toUpperCase());
                        String password_md5 = UtilidadesSeguridad.getMD5(this.password.trim().toUpperCase());
                        user.setPassword(password_md5);
                        user.setEmail(email);
                        user.setEstado(estadoService.findById(estado));
                        user.setGenero(generoService.findById(genero));
                        user.setCodRol(rolService.findById(rol));
                        user.setTipoDocumento(tipoDocService.findById(tipoDocumento));

                        usuarioService.create(user);
                        
                        mensajeInfo("Registro exitoso");
                    } else {
                        mensajeInfo("Este documento que desea registrar ya existe en el sistema");
                    }
                } else {
                    user.setNombre(nombre);
                    user.setApellido(apellido);
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setEstado(estadoService.findById(estado));
                    user.setGenero(generoService.findById(genero));
                    user.setCodRol(rolService.findById(rol));
                    user.setTipoDocumento(tipoDocService.findById(tipoDocumento));
                    usuarioService.edit(user);
                    mensajeInfo("Actualizacón exitosa");
                }
            }
        } catch (Exception e) {
            FacesUtils.controlLog("SEVERE", "Error en la clase UsuarioControllerMB del metodo aceptar: " + e.getMessage());
        }
        return navegacion;
    }

    @Override
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

    public List<SrnTblUsuario> getListUser() {
        return listUser;
    }

    public void setListUser(List<SrnTblUsuario> listUser) {
        this.listUser = listUser;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

}
