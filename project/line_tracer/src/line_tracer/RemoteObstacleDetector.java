/**
 * 
 */
package line_tracer;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

/**
 * @author usamimasanori
 *
 */
public class RemoteObstacleDetector implements Runnable {
	UltrasonicSensor sonic_ = null;
	TouchSensor touch_ = null;

	public RemoteObstacleDetector(UltrasonicSensor ultra, TouchSensor touch) {
		sonic_ = ultra;
		touch_ = touch;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true) {
			boolean exist = sonic_.doesExist() || touch_.isDown();
			LCD.drawString("OBST:" + String.valueOf(exist), 0, 7);
			Delay.msDelay(1000);
		}
	}

}
