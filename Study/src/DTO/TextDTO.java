package DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TextDTO {
	private int tno;
	private String title;
	private String writer;
	private Date crdateOri;
	private String crdate;
	private int count;
	private String text;
	private int bno;
	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getCrdateOri() {
		return crdateOri;
	}
	public void setCrdateOri(Date crdateOri) {
		this.crdateOri = crdateOri;
		String str = "yyyy/MM/dd HH:mm";
		SimpleDateFormat form1 = new SimpleDateFormat(str);
		crdate = form1.format(crdateOri);
		
	}
	public String getCrdate() {
		return crdate;
	}
	public void setCrdate(String crdate) {
		this.crdate = crdate;
	}
	public void setCrdate(Date date) {
		setCrdateOri(date);
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	
	
	
}
