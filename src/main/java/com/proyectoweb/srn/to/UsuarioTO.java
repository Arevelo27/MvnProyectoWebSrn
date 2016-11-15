/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.to;

import com.proyectoweb.srn.modelo.SrnTblEstado;
import com.proyectoweb.srn.modelo.SrnTblGenero;
import com.proyectoweb.srn.modelo.SrnTblRol;
import com.proyectoweb.srn.modelo.SrnTblTipoDocumento;

/**
 *
 * @author TSI
 */
public class UsuarioTO {

    //atributos
    private int idUser;
    private String codigo;
    private String nombre;
    private String apellidos;
    private SrnTblRol rolCodigo;
    private SrnTblEstado estado;
    private SrnTblTipoDocumento tipoDocumento;
    private SrnTblGenero genero;
    private String login;
    private String contrasena;
    private String email;

    public UsuarioTO() {

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public SrnTblEstado getEstado() {
        return estado;
    }

    public void setEstado(SrnTblEstado estado) {
        this.estado = estado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SrnTblRol getRolCodigo() {
        return rolCodigo;
    }

    public void setRolCodigo(SrnTblRol rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SrnTblTipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(SrnTblTipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public SrnTblGenero getGenero() {
        return genero;
    }

    public void setGenero(SrnTblGenero genero) {
        this.genero = genero;
    }

}
