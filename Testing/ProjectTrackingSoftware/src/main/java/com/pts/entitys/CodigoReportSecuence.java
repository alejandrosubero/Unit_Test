package com.pts.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CodigoReportSecuence")
public class CodigoReportSecuence {

	@Id
	@GeneratedValue(generator = "sequence_mat_id_generator")
	@SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 250, allocationSize = 1000)
	@Column(name = "idCodigoReportSecuence", updatable = true, nullable = false, length = 25)
	private Long idCodigoReportSecuence;

	@Column(name = "codigo", updatable = true, nullable = true, length = 200)
	private String codigo;

	@Column(name = "secuencia", updatable = true, nullable = true, length = 200)
	private Long secuencia;

	@Column(name = "description", updatable = true, nullable = true, length = 200)
	private String description;


	
	public CodigoReportSecuence() {
	}

	public Long getIdCodigoReportSecuence() {
		return idCodigoReportSecuence;
	}

	public void setIdCodigoReportSecuence(Long idCodigoReportSecuence) {
		this.idCodigoReportSecuence = idCodigoReportSecuence;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idCodigoReportSecuence == null) ? 0 : idCodigoReportSecuence.hashCode());
		result = prime * result + ((secuencia == null) ? 0 : secuencia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodigoReportSecuence other = (CodigoReportSecuence) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idCodigoReportSecuence == null) {
			if (other.idCodigoReportSecuence != null)
				return false;
		} else if (!idCodigoReportSecuence.equals(other.idCodigoReportSecuence))
			return false;
		if (secuencia == null) {
			if (other.secuencia != null)
				return false;
		} else if (!secuencia.equals(other.secuencia))
			return false;
		return true;
	}

	
	
}
