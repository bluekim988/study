package DTO;

public class BoardDTO implements Comparable<BoardDTO>{
	private int bno;
	private String bname;
	private String bInfo;
	private String isShow;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getbInfo() {
		return bInfo;
	}
	public void setbInfo(String bInfo) {
		this.bInfo = bInfo;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	

	@Override
	public int compareTo(BoardDTO o) {
		return this.bno - o.bno;
	}

}
