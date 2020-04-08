package DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyDTO {
	private int rno;
	private String writer;
	private Date crdateOri;
	private String crdate;
	private int textno;
	private String text;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
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
	public int getTextno() {
		return textno;
	}
	public void setTextno(int textno) {
		this.textno = textno;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	

}
