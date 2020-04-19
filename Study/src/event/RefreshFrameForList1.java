package event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Frame.List1;

public class RefreshFrameForList1 extends MouseAdapter {

	List1 obj;
	String bname;
	
	public RefreshFrameForList1(List1 obj) {
		this.obj = obj;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		obj.f1.dispose();
		bname = obj.bname;
		List1 reList = new List1(bname);
		reList.mouseClicked(null);
	}

}
