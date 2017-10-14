package net.boelstlf.raspi;

import java.util.Random;

import net.boelstlf.raspi.pi4jdevices.i2c.LEDMatrix8x8;
import net.boelstlf.raspi.pi4jdevices.i2c.SSD1306_I2C_Display;

/**
 * 
 */

/**
 * @author boelstlf
 *
 */
public class CatchDot extends Thread {
	public static String version = "0.1";

	private SSD1306_I2C_Display display = null;
	private LEDMatrix8x8 matrix = null;
	private Catcher catcher = null;
	private Dot dot = null;
	private boolean running = true;
	private int round = 1;
	private int points = 0;
	private Random rand = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting 'Catch the Dot Game' v" + version + "...");
		System.out.println("");

		CatchDot catchDot = new CatchDot();
		catchDot.start();

	}

	public CatchDot() {
		matrix = new LEDMatrix8x8(0x70);
		display = new SSD1306_I2C_Display(0x3c);

		display.clearImage();

		this.catcher = new Catcher(8, 8, matrix);
		rand = new Random();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {

		setNewDot();
		// start catching
		while (running) {
			catcher.update();

			if (catcher.getPosX() == dot.getPosX() && catcher.getPosY() == dot.getPosY()) {
				points++;
				round++;
				setNewDot();
			}

			display.clearImage();
			display.setString(1, 1, "round: '" + round + "'");
			display.setString(2, 1, "points: '" + points + "'");

			display.displayImage();

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void setNewDot() {
		this.dot = new Dot(8, 8, matrix);
		int posX = rand.nextInt(8);
		int posY = rand.nextInt(8);
		System.out.println("set new dot at X: '" + posX + "'; y: '" + posY + "'");
		dot.setPosX(posX);
		dot.setPosY(posY);
		dot.init();

	}

	public void stopping() {
		running = false;
	}

}
