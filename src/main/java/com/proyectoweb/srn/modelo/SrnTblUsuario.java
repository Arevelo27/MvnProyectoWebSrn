/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TSI
 */
@Entity
@Table(name = "SRN_TBL_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblUsuario.loginControl", query = "SELECT f FROM SrnTblUsuario f WHERE f.strLogin = :username AND f.strPassword = :password"),
    @NamedQuery(name = "SrnTblUsuario.findMaxId", query = "SELECT  max(s.numIdUsuario) FROM SrnTblUsuario s"),
    @NamedQuery(name = "SrnTblUsuario.findAll", query = "SELECT s FROM SrnTblUsuario s"),
    @NamedQuery(name = "SrnTblUsuario.findByNumIdUsuario", query = "SELECT s FROM SrnTblUsuario s WHERE s.numIdUsuario = :numIdUsuario"),
    @NamedQuery(name = "SrnTblUsuario.findByNumCodDocumento", query = "SELECT s FROM SrnTblUsuario s WHERE s.numCodDocumento = :numCodDocumento"),
    @NamedQuery(name = "SrnTblUsuario.findByStrNombre", query = "SELECT s FROM SrnTblUsuario s WHERE s.strNombre = :strNombre"),
    @NamedQuery(name = "SrnTblUsuario.findByCriterio", query = "SELECT s FROM SrnTblUsuario s WHERE  s.numCodRol.numIdRol = :rol AND (s.strNombre like :criterio or s.strApellido like :criterio or s.numCodDocumento like :criterio)"),
    @NamedQuery(name = "SrnTblUsuario.findByStrEmail", query = "SELECT s FROM SrnTblUsuario s WHERE s.strLogin = :username AND s.strEmail = :email"),
    @NamedQuery(name = "SrnTblUsuario.findByStrApellido", query = "SELECT s FROM SrnTblUsuario s WHERE s.strApellido = :strApellido"),
    @NamedQuery(name = "SrnTblUsuario.findByStrLogin", query = "SELECT s FROM SrnTblUsuario s WHERE s.strLogin = :strLogin"),
    @NamedQuery(name = "SrnTblUsuario.findByStrPassword", query = "SELECT s FROM SrnTblUsuario s WHERE s.strPassword = :strPassword")})
public class SrnTblUsuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioProceso")
    private List<SrnTblControlAuditoria> srnTblControlAuditoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditor")
    private List<SrnTblControlAuditoria> srnTblControlAuditoriaList1;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_ID_USUARIO")
    private Integer numIdUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_COD_DOCUMENTO")
    private long numCodDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STR_NOMBRE")
    private String strNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STR_APELLIDO")
    private String strApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 50, message = "Exede el tamaño permitido, el tamñao permitido es de 50 caracteres")
    @Column(name = "STR_LOGIN")
    private String strLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STR_PASSWORD")
    private String strPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STR_EMAIL")
    private String strEmail;
    @JoinColumn(name = "NUM_TIPO_DOCUMENTO", referencedColumnName = "STR_COD_TIPO_DOC")
    @ManyToOne(optional = false)
    private SrnTblTipoDocumento numTipoDocumento;
    @JoinColumn(name = "NUM_COD_ROL", referencedColumnName = "NUM_ID_ROL")
    @ManyToOne(optional = false)
    private SrnTblRol numCodRol;
    @JoinColumn(name = "STR_GENERO", referencedColumnName = "STR_COD_GENERO")
    @ManyToOne(optional = false)
    private SrnTblGenero strGenero;
    @JoinColumn(name = "STR_ESTADO", referencedColumnName = "STR_COD_ESTADO")
    @ManyToOne(optional = false)
    private SrnTblEstado strEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numCodUsuario")
    private List<SrnTblMateriaUsuario> srnTblMateriaUsuarioList;

    public SrnTblUsuario() {
    }

    public SrnTblUsuario(Integer numIdUsuario) {
        this.numIdUsuario = numIdUsuario;
    }

    public SrnTblUsuario(Integer numIdUsuario, long numCodDocumento, String strNombre, String strApellido, String strLogin, String strPassword) {
        this.numIdUsuario = numIdUsuario;
        this.numCodDocumento = numCodDocumento;
        this.strNombre = strNombre;
        this.strApellido = strApellido;
        this.strLogin = strLogin;
        this.strPassword = strPassword;
    }

    public Integer getIdUsuario() {
        return numIdUsuario;
    }

    public void setIdUsuario(Integer numIdUsuario) {
        this.numIdUsuario = numIdUsuario;
    }

    public long getCodDocumento() {
        return numCodDocumento;
    }

    public void setCodDocumento(long numCodDocumento) {
        this.numCodDocumento = numCodDocumento;
    }

    public String getNombre() {
        return strNombre;
    }

    public void setNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getApellido() {
        return strApellido;
    }

    public void setApellido(String strApellido) {
        this.strApellido = strApellido;
    }

    public String getLogin() {
        return strLogin;
    }

    public void setLogin(String strLogin) {
        this.strLogin = strLogin;
    }

    public String getPassword() {
        return strPassword;
    }

    public void setPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getEmail() {
        return strEmail;
    }

    public void setEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public SrnTblTipoDocumento getTipoDocumento() {
        return numTipoDocumento;
    }

    public void setTipoDocumento(SrnTblTipoDocumento numTipoDocumento) {
        this.numTipoDocumento = numTipoDocumento;
    }

    public SrnTblRol getCodRol() {
        return numCodRol;
    }

    public void setCodRol(SrnTblRol numCodRol) {
        this.numCodRol = numCodRol;
    }

    public SrnTblGenero getGenero() {
        return strGenero;
    }

    public void setGenero(SrnTblGenero strGenero) {
        this.strGenero = strGenero;
    }

    public SrnTblEstado getEstado() {
        return strEstado;
    }

    public void setEstado(SrnTblEstado strEstado) {
        this.strEstado = strEstado;
    }

    @XmlTransient
    public List<SrnTblMateriaUsuario> getSrnTblMateriaUsuarioList() {
        return srnTblMateriaUsuarioList;
    }

    public void setSrnTblMateriaUsuarioList(List<SrnTblMateriaUsuario> srnTblMateriaUsuarioList) {
        this.srnTblMateriaUsuarioList = srnTblMateriaUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numIdUsuario != null ? numIdUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblUsuario)) {
            return false;
        }
        SrnTblUsuario other = (SrnTblUsuario) object;
        if ((this.numIdUsuario == null && other.numIdUsuario != null) || (this.numIdUsuario != null && !this.numIdUsuario.equals(other.numIdUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return strNombre + " " + strApellido;
    }

    @XmlTransient
    public List<SrnTblControlAuditoria> getSrnTblControlAuditoriaList() {
        return srnTblControlAuditoriaList;
    }

    public void setSrnTblControlAuditoriaList(List<SrnTblControlAuditoria> srnTblControlAuditoriaList) {
        this.srnTblControlAuditoriaList = srnTblControlAuditoriaList;
    }

    @XmlTransient
    public List<SrnTblControlAuditoria> getSrnTblControlAuditoriaList1() {
        return srnTblControlAuditoriaList1;
    }

    public void setSrnTblControlAuditoriaList1(List<SrnTblControlAuditoria> srnTblControlAuditoriaList1) {
        this.srnTblControlAuditoriaList1 = srnTblControlAuditoriaList1;
    }

}
