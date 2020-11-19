import java.applet.Applet;
import java.awt.color.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sun.prism.Image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics.*;

public class PingPong extends Applet implements Runnable, KeyListener {
	final int width = 700, height = 500;
	Thread thread;
	HumanUser p1;
	AiUser p2;
	Ball b1;
	boolean gameStarted;

	public void init() {
		this.resize(width, height);
		gameStarted = false;
		this.addKeyListener(this);
		p1 = new HumanUser(1);

		b1 = new Ball();
		p2 = new AiUser(2, b1);

		thread = new Thread(this);
		thread.start();

	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		if (b1.getX() < -10 || b1.getX() > 710) {
			g.setColor(Color.red);
			g.drawString("Game over", 350, 350);
		} else {
			p1.draw(g);
			b1.draw(g);
			p2.draw(g);
		}
		if (!gameStarted) {
			g.setColor(Color.white);
			g.drawString("Ping-Pong", 340, 100);
			g.drawString("Press enter to begin ", 310, 130);
		}

	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		for (;;) {
			if (gameStarted) {
				p1.move();
				b1.move();
				p2.move();
				b1.checkPaddleCollision(p1, p2);
				repaint();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(true);

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(false);

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}

	}

}
