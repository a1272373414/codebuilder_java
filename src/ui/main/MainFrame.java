package ui.main;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 2943141619736401172L;

	public MainFrame() {
		try {
			jbInit();
			moreInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void moreInit() {
		this.setTitle("代码生成器");
		this.setBounds(0, 0, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void jbInit() {
		this.setLayout(new GridBagLayout());
	}

	GridBagLayout g1 = new GridBagLayout();

	JPanel mainPanel = new JPanel();

	public static void main(String[] args) {
		new MainFrame();
	}

}
