import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	float x, y; // Ball's center x and y
	float speedX, speedY; // Ball's speed per step in x and y
	float radius; // Ball's radius
	Color color; // Ball's color

	public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color) {
		this.x = x;
		this.y = y;
		this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float) (-speed * (float) Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
	}

	public void update(float incr) {
		this.x += this.speedX * incr;
		this.y += this.speedY * incr;
	}

	/** Draw itself using the given graphics context. */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
	}

	/** Return mass */
	public float getMass() {
		return 2 * radius * radius * radius / 1000f;
	}

}
