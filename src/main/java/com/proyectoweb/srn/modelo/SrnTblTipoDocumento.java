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
@Table(name = "SRN_TBL_TIPO_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblTipoDocumento.findAll", query = "SELECT s FROM SrnTblTipoDocumento s"),
    @NamedQuery(name = "SrnTblTipoDocumento.findByStrCodTipoDoc", query = "SELECT s FROM SrnTblTipoDocumento s WHERE s.strCodTipoDoc = :strCodTipoDoc"),
    @NamedQuery(name = "SrnTblTipoDocumento.findByStrDescripcion", query = "SELECT s FROM SrnTblTipoDocumento s WHERE s.strDescripcion = :strDescripcion")})
public class SrnTblTipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STR_COD_TIPO_DOC")
    private Short strCodTipoDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STR_DESCRIPCION")
    private String strDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numTipoDocumento")
    private List<SrnTblUsuario> srnTblUsuarioList;

    public SrnTblTipoDocumento() {
    }

    public SrnTblTipoDocumento(Short strCodTipoDoc) {
        this.strCodTipoDoc = strCodTipoDoc;
    }

    public SrnTblTipoDocumento(Short strCodTipoDoc, String strDescripcion) {
        this.strCodTipoDoc = strCodTipoDoc;
        this.strDescripcion = strDescripcion;
    }

    public Short getStrCodTipoDoc() {
        return strCodTipoDoc;
    }

    public void setStrCodTipoDoc(Short strCodTipoDoc) {
        this.strCodTipoDoc = strCodTipoDoc;
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
        hash += (strCodTipoDoc != null ? strCodTipoDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblTipoDocumento)) {
            return false;
        }
        SrnTblTipoDocumento other = (SrnTblTipoDocumento) object;
        if ((this.strCodTipoDoc == null && other.strCodTipoDoc != null) || (this.strCodTipoDoc != null && !this.strCodTipoDoc.equals(other.strCodTipoDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoweb.srn.persistencia.SrnTblTipoDocumento[ strCodTipoDoc=" + strCodTipoDoc + " ]";
    }
    
}
