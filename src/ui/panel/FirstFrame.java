package ui.panel;

import javax.swing.JFrame;

public class FirstFrame extends JFrame {

	private static final long serialVersionUID = 5773901362664553696L;
	
	public FirstFrame(JFrame lastFrame) {
		if(lastFrame!=null) {
			lastFrame.removeAll();
			lastFrame.setVisible(false);
		}
		
	}

}
