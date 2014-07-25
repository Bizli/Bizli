package com.test.parser;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TBLMETERDETAIL")
public class BeanD1 implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;
	@Column(name = "METERNO")
	private String meterNo = null;
	@Column(name = "PTPRIMARYTOSEC")
	private String ptprimarytosec =  null;
	@Column(name = "CTPRIMARYTOSEC")
	private String ctprimarytosec =  null;
	@Column(name = "PROGPTPRIMARY")
	private String progptprimary =  null;
	@Column(name = "PROGPTSECONDARY")
	private String progptsecondary =  null;
	@Column(name = "PROGCTSECONDARY")
	private String progctsecondary =  null;
	@Column(name = "METERCLASS")
	private String meterclass =  null;
	@Column(name = "METERRATING")
	private String meterrating =  null;
	@Column(name = "METERTYPE")
	private String metertype =  null;
	@Column(name = "METERSCALING")
	private String meterscaling =  null;
	@Column(name = "METERPROGNAME")
	private String meterprogname =  null;
	@Column(name = "MANUFACTURECODE")
	private String manufacturecode =  null;
	//@Column(name = "METERNO")
	//private boolean invalidMeterTimeFormat = false;
	
	public String getPtprimarytosec() {
		return ptprimarytosec;
	}
	public void setPtprimarytosec(String ptprimarytosec) {
		this.ptprimarytosec = ptprimarytosec;
	}
	public String getCtprimarytosec() {
		return ctprimarytosec;
	}
	public void setCtprimarytosec(String ctprimarytosec) {
		this.ctprimarytosec = ctprimarytosec;
	}
	public String getProgptprimary() {
		return progptprimary;
	}
	public void setProgptprimary(String progptprimary) {
		this.progptprimary = progptprimary;
	}
	public String getProgptsecondary() {
		return progptsecondary;
	}
	public void setProgptsecondary(String progptsecondary) {
		this.progptsecondary = progptsecondary;
	}
	public String getProgctsecondary() {
		return progctsecondary;
	}
	public void setProgctsecondary(String progctsecondary) {
		this.progctsecondary = progctsecondary;
	}
	public String getMeterclass() {
		return meterclass;
	}
	public void setMeterclass(String meterclass) {
		this.meterclass = meterclass;
	}
	public String getMeterrating() {
		return meterrating;
	}
	public void setMeterrating(String meterrating) {
		this.meterrating = meterrating;
	}
	public String getMetertype() {
		return metertype;
	}
	public void setMetertype(String metertype) {
		this.metertype = metertype;
	}
	public String getMeterscaling() {
		return meterscaling;
	}
	public void setMeterscaling(String meterscaling) {
		this.meterscaling = meterscaling;
	}
	public String getMeterprogname() {
		return meterprogname;
	}
	public void setMeterprogname(String meterprogname) {
		this.meterprogname = meterprogname;
	}
	public String getManufacturecode() {
		return manufacturecode;
	}
	public void setManufacturecode(String manufacturecode) {
		this.manufacturecode = manufacturecode;
	}
	/*public boolean isInvalidMeterTimeFormat() {
		return invalidMeterTimeFormat;
	}
	public void setInvalidMeterTimeFormat(boolean invalidMeterTimeFormat) {
		this.invalidMeterTimeFormat = invalidMeterTimeFormat;
	}*/
	public String getMeterNo() {
		return meterNo;
	}
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	
}
