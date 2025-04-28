package src;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		Panel p = new Panel(1000, 500);
		f.setSize(1000,500);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);;
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.addKeyListener(p);
		String s = "Hello";
		int i = 1;
		System.out.println(s+i);
	}
}




