import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Balls-Ha Minh Hai");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						
				frame.setContentPane(new CollisionNBounce(1280, 700));				
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
