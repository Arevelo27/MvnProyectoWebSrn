/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TSI
 */
@Entity
@Table(name = "SRN_TBL_NOTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblNota.findAll", query = "SELECT s FROM SrnTblNota s"),
    @NamedQuery(name = "SrnTblNota.findByNumIdNota", query = "SELECT s FROM SrnTblNota s WHERE s.numIdNota = :numIdNota"),
    @NamedQuery(name = "SrnTblNota.findByNumParcialI", query = "SELECT s FROM SrnTblNota s WHERE s.numParcialI = :numParcialI"),
    @NamedQuery(name = "SrnTblNota.findByNumParcialIi", query = "SELECT s FROM SrnTblNota s WHERE s.numParcialIi = :numParcialIi"),
    @NamedQuery(name = "SrnTblNota.findByNumParcialIii", query = "SELECT s FROM SrnTblNota s WHERE s.numParcialIii = :numParcialIii"),
    @NamedQuery(name = "SrnTblNota.findByNumNotaAdicional", query = "SELECT s FROM SrnTblNota s WHERE s.numNotaAdicional = :numNotaAdicional"),
    @NamedQuery(name = "SrnTblNota.findByNumPryecto", query = "SELECT s FROM SrnTblNota s WHERE s.numPryecto = :numPryecto"),
    @NamedQuery(name = "SrnTblNota.findByNumNotaFinal", query = "SELECT s FROM SrnTblNota s WHERE s.numNotaFinal = :numNotaFinal")})
public class SrnTblNota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_ID_NOTA")
    private Long numIdNota;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NUM_PARCIAL_I")
    private BigDecimal numParcialI;
    @Column(name = "NUM_PARCIAL_II")
    private BigDecimal numParcialIi;
    @Column(name = "NUM_PARCIAL_III")
    private BigDecimal numParcialIii;
    @Column(name = "NUM_NOTA_ADICIONAL")
    private BigDecimal numNotaAdicional;
    @Column(name = "NUM_PRYECTO")
    private BigDecimal numPryecto;
    @Column(name = "NUM_NOTA_FINAL")
    private BigDecimal numNotaFinal;
    @JoinColumn(name = "NUM_COD_MATERIAUSER", referencedColumnName = "NUM_ID_MATERIAUSER")
    @ManyToOne(optional = false)
    private SrnTblMateriaUsuario numCodMateriauser;

    public SrnTblNota() {
    }

    public SrnTblNota(Long numIdNota) {
        this.numIdNota = numIdNota;
    }

    public Long getNumIdNota() {
        return numIdNota;
    }

    public void setNumIdNota(Long numIdNota) {
        this.numIdNota = numIdNota;
    }

    public BigDecimal getNumParcialI() {
        return numParcialI;
    }

    public void setNumParcialI(BigDecimal numParcialI) {
        this.numParcialI = numParcialI;
    }

    public BigDecimal getNumParcialIi() {
        return numParcialIi;
    }

    public void setNumParcialIi(BigDecimal numParcialIi) {
        this.numParcialIi = numParcialIi;
    }

    public BigDecimal getNumParcialIii() {
        return numParcialIii;
    }

    public void setNumParcialIii(BigDecimal numParcialIii) {
        this.numParcialIii = numParcialIii;
    }

    public BigDecimal getNumNotaAdicional() {
        return numNotaAdicional;
    }

    public void setNumNotaAdicional(BigDecimal numNotaAdicional) {
        this.numNotaAdicional = numNotaAdicional;
    }

    public BigDecimal getNumPryecto() {
        return numPryecto;
    }

    public void setNumPryecto(BigDecimal numPryecto) {
        this.numPryecto = numPryecto;
    }

    public BigDecimal getNumNotaFinal() {
        return numNotaFinal;
    }

    public void setNumNotaFinal(BigDecimal numNotaFinal) {
        this.numNotaFinal = numNotaFinal;
    }

    public SrnTblMateriaUsuario getNumCodMateriauser() {
        return numCodMateriauser;
    }

    public void setNumCodMateriauser(SrnTblMateriaUsuario numCodMateriauser) {
        this.numCodMateriauser = numCodMateriauser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numIdNota != null ? numIdNota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblNota)) {
            return false;
        }
        SrnTblNota other = (SrnTblNota) object;
        if ((this.numIdNota == null && other.numIdNota != null) || (this.numIdNota != null && !this.numIdNota.equals(other.numIdNota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoweb.srn.persistencia.SrnTblNota[ numIdNota=" + numIdNota + " ]";
    }
    
}
