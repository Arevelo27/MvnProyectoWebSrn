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
@Table(name = "SRN_TBL_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblRol.findAll", query = "SELECT s FROM SrnTblRol s"),
    @NamedQuery(name = "SrnTblRol.findMaxId", query = "SELECT  max(s.numIdRol) FROM SrnTblRol s"),
    @NamedQuery(name = "SrnTblRol.findByNumIdRol", query = "SELECT s FROM SrnTblRol s WHERE s.numIdRol = :numIdRol"),
    @NamedQuery(name = "SrnTblRol.findByStrDescripcion", query = "SELECT s FROM SrnTblRol s WHERE s.strDescripcion = :strDescripcion")})
public class SrnTblRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_ID_ROL")
    private Integer numIdRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STR_DESCRIPCION")
    private String strDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numCodRol")
    private List<SrnTblUsuario> srnTblUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numCodRol")
    private List<SrnTblPermisos> srnTblPermisosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numCodRol")
    private List<SrnTblMenuPermisos> srnTblMenuPermisosList;

    public SrnTblRol() {
    }

    public SrnTblRol(Integer numIdRol) {
        this.numIdRol = numIdRol;
    }

    public SrnTblRol(Integer numIdRol, String strDescripcion) {
        this.numIdRol = numIdRol;
        this.strDescripcion = strDescripcion;
    }

    public Integer getNumIdRol() {
        return numIdRol;
    }

    public void setNumIdRol(Integer numIdRol) {
        this.numIdRol = numIdRol;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    @XmlTransient
    public List<SrnTblUsuario> getSrnTblUsuarioList() {
        return srnTblUsuarioList;
    }

    public void setSrnTblUsuarioList(List<SrnTblUsuario> srnTblUsuarioList) {
        this.srnTblUsuarioList = srnTblUsuarioList;
    }

    @XmlTransient
    public List<SrnTblPermisos> getSrnTblPermisosList() {
        return srnTblPermisosList;
    }

    public void setSrnTblPermisosList(List<SrnTblPermisos> srnTblPermisosList) {
        this.srnTblPermisosList = srnTblPermisosList;
    }

    @XmlTransient
    public List<SrnTblMenuPermisos> getSrnTblMenuPermisosList() {
        return srnTblMenuPermisosList;
    }

    public void setSrnTblMenuPermisosList(List<SrnTblMenuPermisos> srnTblMenuPermisosList) {
        this.srnTblMenuPermisosList = srnTblMenuPermisosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numIdRol != null ? numIdRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblRol)) {
            return false;
        }
        SrnTblRol other = (SrnTblRol) object;
        if ((this.numIdRol == null && other.numIdRol != null) || (this.numIdRol != null && !this.numIdRol.equals(other.numIdRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoweb.srn.persistencia.SrnTblRol[ numIdRol=" + numIdRol + " ]";
    }

}
