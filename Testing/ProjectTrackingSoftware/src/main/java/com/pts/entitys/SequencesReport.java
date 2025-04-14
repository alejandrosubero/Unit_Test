package com.pts.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SequencesReport")
public class SequencesReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6067249778827758418L;

	
	@Id
	@GeneratedValue(generator = "sequence_mat_id_generator")
	@SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 1, allocationSize = 1000000)
	@Column(name = "idSequencesReport", updatable = true, nullable = false, length = 25)
	private Long idSequencesReport;
	
	@Column(name = "title", updatable = true, nullable = true, length = 200)
	private String title;

	@Column(name = "codeSecuence", updatable = true, nullable = true, length = 25)
	private String codeSecuence;
	
	@Column(name = "secuenceBefore", updatable = true, nullable = true, length = 25)
	private Long ActualSecuence;

	@Column(name = "secuencia", updatable = true, nullable = true, length = 25)
	private Long nextSecuence;
	
	
	public SequencesReport() {
	}
	
	
	public SequencesReport(String title, String codeSecuence) {
		super();
		this.title = title;
		this.codeSecuence = codeSecuence;
	}


	public void calculateSecuence() {
		
		if (this.ActualSecuence == null && nextSecuence == null) {
			this.ActualSecuence = 0L;
			this.nextSecuence = 1L;
		
		}else if (this.ActualSecuence != null && nextSecuence != null){
			Long nex = this.nextSecuence;
			this.ActualSecuence = nex;
			this.nextSecuence++;
		
		} else {
			
			if (this.ActualSecuence == null && this.nextSecuence != null) {
				this.ActualSecuence = this.nextSecuence - 1;
			}else if (this.ActualSecuence != null && this.nextSecuence == null) {
				this.nextSecuence = this.ActualSecuence+1;
			}
		}
	}
	
	

	public Long getIdSequencesReport() {
		return idSequencesReport;
	}

	public void setIdSequencesReport(Long idSequencesReport) {
		this.idSequencesReport = idSequencesReport;
	}

	public Long getActualSecuence() {
		return ActualSecuence;
	}

	public void setActualSecuence(Long actualsecuence) {
		ActualSecuence = actualsecuence;
	}

	public Long getNextSecuence() {
		return nextSecuence;
	}

	public void setNextSecuence(Long nextSecuence) {
		this.nextSecuence = nextSecuence;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCodeSecuence() {
		return codeSecuence;
	}


	public void setCodeSecuence(String codeSecuence) {
		this.codeSecuence = codeSecuence;
	}
	
	
		
}
