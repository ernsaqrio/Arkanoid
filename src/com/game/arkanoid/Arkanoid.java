package com.game.arkanoid;

import java.awt.*;

import java.awt.event.*;

import java.util.*;

@SuppressWarnings("serial")
public class Arkanoid extends Frame implements WindowListener, MouseListener, MouseMotionListener, Runnable {

	Panel panel = new Panel();

	int tx = 220;

	int ty;

	int px;

	int py;

	int score = 0;

	int vidas = 4;

	float incX = 1;

	float incY = 1;

	boolean[] filas = new boolean[30];

	boolean inicio = false;

	Random r = new Random();

	public Arkanoid() {

		setSize(510, 400);

		add(panel);

		panel.setBackground(Color.black);

		panel.addMouseMotionListener(this);

		panel.addMouseListener(this);

		px = 260;

		py = this.getHeight() - 60;

		addWindowListener(this);

		for (int x = 0; x != 30; x++) {

			filas[x] = true;

		}

	}

	@SuppressWarnings("deprecation")
	public static void main(String[] Args) {

		Arkanoid arkanoid = new Arkanoid();

		arkanoid.show();

		arkanoid.run();

	}

	public void dibujar(Color[] colores) {

		Graphics g = panel.getGraphics();

		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(Color.yellow);

		g.fillOval((int) px, (int) py, 10, 10);

		g.setColor(Color.red);

		g.drawOval((int) px, (int) py, 10, 10);

		g.fillRect((int) tx, (int) ty, 80, 20);

		g.setColor(Color.white);

		g.drawRect((int) tx, (int) ty, 80, 20);

		g.setColor(Color.green);

		g.drawString("Arkanoid", 10, 15);

		g.drawString("Lifes:", 275, 15);

		g.setColor(Color.orange);

		switch (vidas) {

		case 4:

			g.drawString("Right click to Start", 100, 220);

			g.setColor(Color.white);

			g.fillRect(320, 5, 15, 10);

			g.fillRect(340, 5, 15, 10);

			g.fillRect(360, 5, 15, 10);

			g.setColor(Color.red);

			g.drawRect(320, 5, 15, 10);

			g.drawRect(340, 5, 15, 10);

			g.drawRect(360, 5, 15, 10);

			break;

		case 3:
			g.setColor(Color.white);

			g.fillRect(320, 5, 15, 10);

			g.fillRect(340, 5, 15, 10);

			g.fillRect(360, 5, 15, 10);

			g.setColor(Color.red);

			g.drawRect(320, 5, 15, 10);

			g.drawRect(340, 5, 15, 10);

			g.drawRect(360, 5, 15, 10);

			break;

		case 2:
			g.setColor(Color.white);

			g.fillRect(320, 5, 15, 10);

			g.fillRect(340, 5, 15, 10);

			g.setColor(Color.red);

			g.drawRect(320, 5, 15, 10);

			g.drawRect(340, 5, 15, 10);

			break;

		case 1:
			g.setColor(Color.white);

			g.fillRect(320, 5, 15, 10);

			g.setColor(Color.red);

			g.drawRect(320, 5, 15, 10);

			break;

		case 0:
			g.drawString("GAME OVER", 230, 200);

			g.drawString("Right click to continue ", 100, 220);

			break;

		}

		g.setColor(Color.white);

		g.drawString("Score:", 400, 15);

		g.drawString(String.valueOf(score), 470, 15);
		if (score == 300) {

			g.drawString("You won!!!", 230, 200);

			g.drawString("Congrats you beat the game!", 70, 220);

			g.drawString("Right click for another game!", 100, 240);

			vidas = 4;

		}

		Graphics[] gl = new Graphics[30];

		int xp = 0;

		int yp = 30;

		int c = 0;

		for (int x = 0; x != 30; x++) {

			gl[x] = panel.getGraphics();

			c++;

			if (c == 11) {

				xp = 0;

				yp = 50;

			}

			if (c == 21) {

				xp = 0;

				yp = 70;

			}

			gl[x].setColor(colores[x]);

			if (filas[x] == true) {

				gl[x].fillRect(xp, yp, 48, 15);

				gl[x].setColor(Color.white);

				gl[x].drawRect(xp, yp, 48, 15);

			}

			else {

				g.clearRect(xp, yp, 48, 15);

			}

			xp += 50;

		}

	}

	public void mover() {

		if (inicio == false) {

		}

		else {

			px += 5 * incX;

			py += 5 * incY;

		}

		if (py > this.getHeight()) {

			vidas--;

			px = 260;

			tx = 220;

			py = this.getHeight() - 60;

			inicio = false;

		}

		if ((px < 0) || (px > this.getWidth() - 20)) {

			incX *= -1;

		}

		if (py < 20) {

			incY *= -1;

		}

		if ((py == ty) && (tx < px) && (px < tx + 80)) {

			incY *= -1;

			if ((px < tx + 20) && (incX > 0)) {

				incX *= -1.5;

			}

			if ((px > tx + 60) && (incX < 0)) {

				incX *= -1.5;

			}

		}

		if ((py < 74) && (py > 56)) {

			if (px < 48) {

				if (filas[20] == true) {

					filas[20] = false;

					incY *= -1;

					score += 10;
					;

				}

			} else if ((px < 98) && (px > 48)) {

				if (filas[21] == true) {

					filas[21] = false;

					incY *= -1;

					score += 10;

				}

			} else if ((px < 148) && (px > 98)) {

				if (filas[22] == true) {

					filas[22] = false;

					incY *= -1;

					score += 10;
					;

				}

			} else if ((px < 198) && (px > 148)) {

				if (filas[23] == true) {

					filas[23] = false;

					incY *= -1;

					score += 10;
					;

				}

			} else if ((px < 248) && (px > 198)) {

				if (filas[24] == true) {

					filas[24] = false;

					incY *= -1;

					score += 10;

				}

			} else if ((px < 298) && (px > 248)) {

				if (filas[25] == true) {

					filas[25] = false;

					incY *= -1;

					score += 10;
					;

				}

			} else if ((px < 348) && (px > 298)) {

				if (filas[26] == true) {

					filas[26] = false;

					incY *= -1;

					score += 10;
					;

				}

			} else if ((px < 398) && (px > 348)) {

				if (filas[27] == true) {

					filas[27] = false;

					incY *= -1;

					score += 10;
					;

				}

			} else if ((px < 448) && (px > 398)) {

				if (filas[28] == true) {

					filas[28] = false;

					incY *= -1;

					score += 10;
					;

				}

			} else if (filas[29] == true) {

				filas[29] = false;

				incY *= -1;

				score += 10;
				;

			}

		}

		if ((py < 58) && (py > 40)) {

			if (px < 48) {

				if (filas[10] == true) {

					filas[10] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 98) && (px > 48)) {

				if (filas[11] == true) {

					filas[11] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 148) && (px > 98)) {

				if (filas[12] == true) {

					filas[12] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 198) && (px > 148)) {

				if (filas[13] == true) {

					filas[13] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 248) && (px > 198)) {

				if (filas[14] == true) {

					filas[14] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 298) && (px > 248)) {

				if (filas[15] == true) {

					filas[15] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 348) && (px > 298)) {

				if (filas[16] == true) {

					filas[16] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 398) && (px > 348)) {

				if (filas[17] == true) {

					filas[17] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 448) && (px > 398)) {

				if (filas[18] == true) {

					filas[18] = false;

					score += 10;
					;

				}

			}

			if ((px < 598) && (px > 448)) {

				if (filas[19] == true) {

					filas[19] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

		}

		if ((py < 43) && (py > 24)) {

			if (px < 48) {

				if (filas[0] == true) {

					filas[0] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 98) && (px > 48)) {

				if (filas[1] == true) {

					filas[1] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 148) && (px > 98)) {

				if (filas[2] == true) {

					filas[2] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 198) && (px > 148)) {

				if (filas[3] == true) {

					filas[3] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 248) && (px > 198)) {

				if (filas[4] == true) {

					filas[4] = false;

					incY *= -1;

					score += 10;
					;

				}

			}

			if ((px < 298) && (px > 248)) {

				if (filas[5] == true) {

					filas[5] = false;

					incY *= -1;

					score += 10;

				}

			}

			if ((px < 348) && (px > 298)) {

				if (filas[6] == true) {

					filas[6] = false;

					incY *= -1;

					score += 10;

				}

			}

			if ((px < 398) && (px > 348)) {

				if (filas[7] == true) {

					filas[7] = false;

					incY *= -1;

					score += 10;

				}

			}

			if ((px < 448) && (px > 398)) {

				if (filas[8] == true) {

					filas[8] = false;

					incY *= -1;

					score += 10;

				}

			}

			if ((px < 598) && (px > 448)) {

				if (filas[9] == true) {

					filas[9] = false;

					incY *= -1;

					score += 10;

				}

			}

		}

		if (tx > this.getWidth() - 80) {

			tx = this.getWidth() - 80;

		}

		ty = this.getHeight() - 50;

	}

	public void run() {

		Color[] colores = new Color[30];

		for (int x = 0; x != 30; x++) {

			colores[x] = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));

		}

		while ((true) && (score != 300)) {

			this.mover();

			this.dibujar(colores);

			try {

				Thread.sleep(50);

			}

			catch (InterruptedException IE) {
			}

		}

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

		if (inicio == false) {

			px = e.getX() + 40;
		}

		tx = e.getX();

	}

	public void windowActivated(WindowEvent e) {

	}

	public void windowClosed(WindowEvent e) {

	}

	public void windowClosing(WindowEvent e) {

		System.exit(0);
	}

	public void windowDeactivated(WindowEvent e) {

	}

	public void windowDeiconified(WindowEvent e) {

	}

	public void windowIconified(WindowEvent e) {

	}

	public void windowOpened(WindowEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

		if (vidas == 4) {

			vidas--;

		}

		if (vidas == 0) {

			vidas = 4;
			score = 0;

			for (int x = 0; x != 30; x++) {

				filas[x] = true;

			}

		}

		inicio = true;

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

}
