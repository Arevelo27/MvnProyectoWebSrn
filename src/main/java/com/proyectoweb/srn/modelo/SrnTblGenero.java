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
@Table(name = "SRN_TBL_GENERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblGenero.findAll", query = "SELECT s FROM SrnTblGenero s"),
    @NamedQuery(name = "SrnTblGenero.findByStrCodGenero", query = "SELECT s FROM SrnTblGenero s WHERE s.strCodGenero = :strCodGenero"),
    @NamedQuery(name = "SrnTblGenero.findByStrDescripcion", query = "SELECT s FROM SrnTblGenero s WHERE s.strDescripcion = :strDescripcion")})
public class SrnTblGenero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STR_COD_GENERO")
    private String strCodGenero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STR_DESCRIPCION")
    private String strDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "strGenero")
    private List<SrnTblUsuario> srnTblUsuarioList;

    public SrnTblGenero() {
    }

    public SrnTblGenero(String strCodGenero) {
        this.strCodGenero = strCodGenero;
    }

    public SrnTblGenero(String strCodGenero, String strDescripcion) {
        this.strCodGenero = strCodGenero;
        this.strDescripcion = strDescripcion;
    }

    public String getStrCodGenero() {
        return strCodGenero;
    }

    public void setStrCodGenero(String strCodGenero) {
        this.strCodGenero = strCodGenero;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (strCodGenero != null ? strCodGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblGenero)) {
            return false;
        }
        SrnTblGenero other = (SrnTblGenero) object;
        if ((this.strCodGenero == null && other.strCodGenero != null) || (this.strCodGenero != null && !this.strCodGenero.equals(other.strCodGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoweb.srn.persistencia.SrnTblGenero[ strCodGenero=" + strCodGenero + " ]";
    }
    
}
