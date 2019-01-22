package co.megadodo.chessai;

import javax.swing.JFrame;

public class Chess {
	
	public static void main(String[]args) {
		new Chess();
	}
	
	public JFrame frame;
	
	public Chess() {
		frame = new JFrame("Chess");
		frame.setSize(1000-15,1000);
		frame.add(new ChessPanel(frame));
		frame.setVisible(true);
	}

}
