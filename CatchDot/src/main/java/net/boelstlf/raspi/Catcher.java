package net.boelstlf.raspi;

import net.boelstlf.raspi.pi4jdevices.i2c.LEDMatrix8x8;
import net.boelstlf.raspi.pi4jdevices.i2c.MPU6050;
import net.boelstlf.raspi.pi4jdevices.i2c.MPU6050.ThreeAxisAndGyro;

public class Catcher extends Dot {
	private MPU6050 mpu = new MPU6050(0x68);
	private ThreeAxisAndGyro accl;

	private int sensitive = 5;
	double rotXOffset;
	double rotYOffset;
	double rotX;
	double rotY;

	public Catcher(int width, int height, LEDMatrix8x8 matrix)
	{
		super(width, height, matrix);
		accl = mpu.readAccl();
		rotXOffset = accl.getXRotation();
		rotYOffset = accl.getYRotation();
		posX = 4;
		posY= 4;
		init();
	}
	
	public void update() {
		accl = mpu.readAccl();

		rotX = accl.getXRotation() - rotXOffset;
		rotY = accl.getYRotation() - rotYOffset;
		String msg = String.format("rotX: %10.3f\trotY: %10.3f", rotX, rotY);
		System.out.println(msg);
		
		if (rotX < -sensitive) {
			this.moveRight();
		}
		if (rotX > sensitive) {
			this.moveLeft();
		}
		if (rotY < -sensitive) {
			this.moveUp();
		}
		if (rotY > sensitive) {
			this.moveDown();
		}
		this.display();
	}

}
