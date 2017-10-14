package net.boelstlf.raspi;

import net.boelstlf.raspi.pi4jdevices.i2c.LEDMatrix8x8;

public class Dot {
	protected LEDMatrix8x8 matrix = null;
	protected int height = 0;
	protected int width = 0;
	public int posX = 0;
	public int posY = 0;

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Dot(int width, int height, LEDMatrix8x8 matrix) {
		this.width = width;
		this.height = height;
		this.matrix = matrix;
	}

	protected void init() {
		matrix.setPixel(posX, posY, true);
		matrix.writeDisplay();
	}

	public void moveUp() {
		if (posY >= 1) {
			// remove the old dot
			matrix.setPixel(posX, posY, false);
			posY--;
			// set new dot
			matrix.setPixel(posX, posY, true);
		}
	}

	public void moveDown() {
		if (posY <= height - 1) {
			// remove the old dot
			matrix.setPixel(posX, posY, false);
			posY++;
			// set new dot
			matrix.setPixel(posX, posY, true);
		}
	}

	public void moveLeft() {
		if (posX >= 1) {
			// remove the old dot
			matrix.setPixel(posX, posY, false);
			posX--;
			// set new dot
			matrix.setPixel(posX, posY, true);
		}
	}

	public void moveRight() {
		if (posX <= width - 1) {
			// remove the old dot
			matrix.setPixel(posX, posY, false);
			posX++;
			// set new dot
			matrix.setPixel(posX, posY, true);
		}
	}

	public void display() {
		matrix.writeDisplay();
	}

}
