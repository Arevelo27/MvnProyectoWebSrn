/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TSI
 */
@Entity
@Table(name = "SRN_TBL_CONTROL_AUDITORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblControlAuditoria.findAll", query = "SELECT s FROM SrnTblControlAuditoria s"),
    @NamedQuery(name = "SrnTblControlAuditoria.findByIdProceso", query = "SELECT s FROM SrnTblControlAuditoria s WHERE s.idProceso = :idProceso"),
    @NamedQuery(name = "SrnTblControlAuditoria.findByProcesoRealizado", query = "SELECT s FROM SrnTblControlAuditoria s WHERE s.procesoRealizado = :procesoRealizado"),
    @NamedQuery(name = "SrnTblControlAuditoria.findByRolUsuario", query = "SELECT s FROM SrnTblControlAuditoria s WHERE s.rolUsuario = :rolUsuario"),
    @NamedQuery(name = "SrnTblControlAuditoria.findByFechaProceso", query = "SELECT s FROM SrnTblControlAuditoria s WHERE s.fechaProceso = :fechaProceso"),
    @NamedQuery(name = "SrnTblControlAuditoria.findByVistaProceso", query = "SELECT s FROM SrnTblControlAuditoria s WHERE s.vistaProceso = :vistaProceso"),
    @NamedQuery(name = "SrnTblControlAuditoria.findByDescProceso", query = "SELECT s FROM SrnTblControlAuditoria s WHERE s.descProceso = :descProceso"),
    @NamedQuery(name = "SrnTblControlAuditoria.findByDescAuditor", query = "SELECT s FROM SrnTblControlAuditoria s WHERE s.descAuditor = :descAuditor"),
    @NamedQuery(name = "SrnTblControlAuditoria.findByFechaAuditada", query = "SELECT s FROM SrnTblControlAuditoria s WHERE s.fechaAuditada = :fechaAuditada")})
public class SrnTblControlAuditoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROCESO")
    private Long idProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PROCESO_REALIZADO")
    private String procesoRealizado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ROL_USUARIO")
    private String rolUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PROCESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "VISTA_PROCESO")
    private String vistaProceso;
    @Size(max = 400)
    @Column(name = "DESC_PROCESO")
    private String descProceso;
    @Size(max = 300)
    @Column(name = "DESC_AUDITOR")
    private String descAuditor;
    @Column(name = "FECHA_AUDITADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAuditada;
    @JoinColumn(name = "USUARIO_PROCESO", referencedColumnName = "NUM_ID_USUARIO")
    @ManyToOne(optional = false)
    private SrnTblUsuario usuarioProceso;
    @JoinColumn(name = "AUDITOR", referencedColumnName = "NUM_ID_USUARIO")
    @ManyToOne(optional = false)
    private SrnTblUsuario auditor;

    public SrnTblControlAuditoria() {
    }

    public SrnTblControlAuditoria(Long idProceso) {
        this.idProceso = idProceso;
    }

    public SrnTblControlAuditoria(Long idProceso, String procesoRealizado, String rolUsuario, Date fechaProceso, String vistaProceso) {
        this.idProceso = idProceso;
        this.procesoRealizado = procesoRealizado;
        this.rolUsuario = rolUsuario;
        this.fechaProceso = fechaProceso;
        this.vistaProceso = vistaProceso;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public String getProcesoRealizado() {
        return procesoRealizado;
    }

    public void setProcesoRealizado(String procesoRealizado) {
        this.procesoRealizado = procesoRealizado;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getVistaProceso() {
        return vistaProceso;
    }

    public void setVistaProceso(String vistaProceso) {
        this.vistaProceso = vistaProceso;
    }

    public String getDescProceso() {
        return descProceso;
    }

    public void setDescProceso(String descProceso) {
        this.descProceso = descProceso;
    }

    public String getDescAuditor() {
        return descAuditor;
    }

    public void setDescAuditor(String descAuditor) {
        this.descAuditor = descAuditor;
    }

    public Date getFechaAuditada() {
        return fechaAuditada;
    }

    public void setFechaAuditada(Date fechaAuditada) {
        this.fechaAuditada = fechaAuditada;
    }

    public SrnTblUsuario getUsuarioProceso() {
        return usuarioProceso;
    }

    public void setUsuarioProceso(SrnTblUsuario usuarioProceso) {
        this.usuarioProceso = usuarioProceso;
    }

    public SrnTblUsuario getAuditor() {
        return auditor;
    }

    public void setAuditor(SrnTblUsuario auditor) {
        this.auditor = auditor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProceso != null ? idProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblControlAuditoria)) {
            return false;
        }
        SrnTblControlAuditoria other = (SrnTblControlAuditoria) object;
        if ((this.idProceso == null && other.idProceso != null) || (this.idProceso != null && !this.idProceso.equals(other.idProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoweb.srn.modelo.SrnTblControlAuditoria[ idProceso=" + idProceso + " ]";
    }
    
}
