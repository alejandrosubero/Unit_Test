package com.pts.pojo;

public class SequencesReportPojo {

	private Long idSequencesReport;
	private String title;
	private String codeSecuence;
	private Long ActualSecuence;
	private Long nextSecuence;

	public SequencesReportPojo() {}
	
	
	public Long getIdSequencesReport() {
		return idSequencesReport;
	}

	public void setIdSequencesReport(Long idSequencesReport) {
		this.idSequencesReport = idSequencesReport;
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

	public Long getActualSecuence() {
		return ActualSecuence;
	}

	public void setActualSecuence(Long actualSecuence) {
		ActualSecuence = actualSecuence;
	}

	public Long getNextSecuence() {
		return nextSecuence;
	}

	public void setNextSecuence(Long nextSecuence) {
		this.nextSecuence = nextSecuence;
	}

}
