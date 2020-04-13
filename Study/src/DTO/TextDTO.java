package DTO;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextDTO implements Comparable<TextDTO>{
	private int tno;
	private String title;
	private String writer;
	private Date crdateOri;
	private Time crtimeOri;
	private String crdate;
	private int count;
	private int like;
	private String text;
	private int bno;
	private String isShow;
	
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
		String str = "yyyy/MM/dd ";
		String str2 = "HH:mm";
		SimpleDateFormat form1 = new SimpleDateFormat(str);
		SimpleDateFormat form2 = new SimpleDateFormat(str2);
		crdate = form1.format(crdateOri) + form2.format(crtimeOri);
		
	}
	public String getCrdate() {
		return crdate;
	}
	public void setCrdate(String crdate) {
		this.crdate = crdate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
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
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	
	public Time getCrtimeOri() {
		return crtimeOri;
	}
	public void setCrtimeOri(Time crtimeOri) {
		this.crtimeOri = crtimeOri;
	}
	@Override
	public int compareTo(TextDTO o) {
		return o.tno - this.tno ;
	}
	
}
