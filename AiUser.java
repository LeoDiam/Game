
import java.awt.Color;
import java.awt.Graphics;

public class AiUser implements User {
	double y, yVel;
	boolean upAccel, downAccel;
	int player, x;
	Ball b1;

	public AiUser(int player, Ball b) {
		upAccel = false;
		downAccel = false;
		b1 = b;
		y = 210;
		yVel = 0;
		this.player = player;
		if (player == 1) {
			x = 20;
		} else {
			x = 660;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int) y, 20, 80);

	}

	public void move() {
		// TODO Auto-generated method stub

		y = b1.getY() - 40;
		if (y < 0)
			y = 0;
		if (y > 420)
			y = 420;

	}

	public int getY() {
		// TODO Auto-generated method stub
		return (int) y;
	}

}
