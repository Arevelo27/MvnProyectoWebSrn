/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.utilidades;

import com.proyectoweb.srn.to.UsuarioTO;
import java.io.Serializable;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author TSI
 */
@ManagedBean
@SessionScoped
public class ReglasDeNavegacion implements Serializable {

    private UsuarioTO usuarioTo;
    private String nombre;

    public ReglasDeNavegacion() {
        try {
            usuarioTo = (UsuarioTO) FacesUtils.getSession().getAttribute("usuario");
            if (usuarioTo == null) {
                System.out.println("No hay registro de usuario");
                UtilidadesSeguridad.getControlSession();
            } else {
                nombre = usuarioTo.getNombre() + " " + usuarioTo.getApellidos();
            }
        } catch (ViewExpiredException e) {
            FacesUtils.controlLog("SEVERE", "Error [ViewExpiredException] en la clase ReglasDeNavegacion: " + e.getMessage());
            UtilidadesSeguridad.getControlSession();
        } catch (Exception ex) {
            FacesUtils.controlLog("SEVERE", "Error [Exception] en la clase ReglasDeNavegacion: " + ex.getMessage());
        }
    }
//    public static String usuario = "inicio.xhtml?faces-redirect=true";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlInicio() {
        return "frmInicio";
    }

    public String getUrlUsuario() {
        return "frmUsuario";
    }

    public String getUrlNotasActuales() {
        return "frmNotasActuales";
    }

    public String getUrlNotasRegistrosExtendido() {
        return "frmNotasRegisExt";
    }

    public String getUrlPensum() {
        return "frmPensum";
    }

    public String getUrlRol() {
        return "frmRol";
    }

    public String getUrlPermisos() {
        return "frmPermisos";
    }

}
