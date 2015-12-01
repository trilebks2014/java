import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class CollisionNBounce extends JPanel {
	List<Ball> ballsL = new ArrayList<Ball>();
	private GameField gameField;
	private DrawCanvas canvas;
	private int canvasWidth;
	private int canvasHeight;
	
	public CollisionNBounce(int width, int height) {
		canvasWidth = width;
		canvasHeight = height;
		// init balls
		float x;
		float y;
		float radius;
		float speed;
		float angleInDegree;
		Color color;
		
		for(int i=1;i<=100;i++) {
			radius=(float)(Math.random()*25+15);
			x=(float)(Math.random()*(1280-2*radius));
			y=(float)(Math.random()*(700-2*radius));
			speed=(float)(Math.random()*5+1);
			angleInDegree=(float)(Math.random()*360);
			color=new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
			ballsL.add(new Ball(x, y, radius, speed, angleInDegree, color));			
		}	

		gameField = new GameField(0, 0, canvasWidth, canvasHeight, Color.WHITE, Color.RED);

		canvas = new DrawCanvas();

		this.setLayout(new BorderLayout());
		this.add(canvas, BorderLayout.CENTER);
		// Handling window resize. Adjust container box to fill the screen.
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component) e.getSource();
				Dimension dim = c.getSize();
				gameField.set(0, 0, dim.width, dim.height);
			}
		});
		// Start the ball bouncing
		gameStart();
	}

	/** Start the ball bouncing. */
	public void gameStart() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					gameUpdate(false);
					repaint();
					// Delay and give other thread a chance
					try {
						Thread.sleep(50);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		gameThread.start(); // Invoke GaemThread.run()
	}

	/**
	 * detects collision, bounces, calculate final velocities
	 * 
	 * @param isStriker
	 */
	public void gameUpdate(boolean isStriker) {
		// Check collision between the balls and the box
		for (int i = 0; i < ballsL.size(); i++) {
			PhysicsUtils.collisionWithWall(new Rectangle(gameField.minX, gameField.minY, gameField.maxX, gameField.maxY), ballsL.get(i));
		}
		// Check collision between two balls
		for (int i = 0; i < ballsL.size(); i++) {
			for (int j = 0; j < ballsL.size(); j++) {
				if (i < j) {
					PhysicsUtils.intersect(ballsL.get(i), ballsL.get(j));
				}
			}
		}
		// update positions increments
		for (int i = 0; i < ballsL.size(); i++) {
			ballsL.get(i).update(1);
		}
	}

	/** The custom drawing panel for the bouncing ball (inner class). */
	class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			// Draw the balls and field
			gameField.draw(g);
			for (Ball ball : ballsL) {
				ball.draw(g);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return (new Dimension(canvasWidth, canvasHeight));
		}
	}
}
