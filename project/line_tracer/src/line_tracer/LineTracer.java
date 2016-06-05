/**
 * 
 */
package line_tracer;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import lejos.hardware.ev3.*;
import lejos.hardware.*;

/**
 * @author usamimasanori
 *
 */
public class LineTracer {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LightSensorImpl light =  new LightSensorImpl(SensorPort.S2);
		WheelImpl rightWheel = new WheelImpl(MotorPort.B);
		WheelImpl leftWheel = new WheelImpl(MotorPort.C);
		DirectionControllerImpl direction = new DirectionControllerImpl(rightWheel, leftWheel);
		ControllerOnOff controller = new ControllerOnOff(light, direction);

		LCD.drawString("Line Tracer Test.", 0, 2);

		Server server = new Server(direction);
		Thread thread = new Thread(server);
		thread.start();

		trace(light, controller, direction);
	}
	
	public static void trace(LightSensor light, Controller controller, DirectionController direction) {
		Key enter   = ((EV3)BrickFinder.getLocal()).getKey("Enter");
		
		light.setThreashold(0.3F);
		
		while(enter.isUp()) {
			Delay.msDelay(100);
		}
		
		for(int i = 0; i < 1200; i++) {
			Delay.msDelay(100);
			controller.execute();
			Float val = light.getValue();
			LCD.drawString("light=" + String.valueOf(val), 0, 3);
			if (enter.isDown()) {
				direction.stop();
				System.exit(0);
			}
		}
	}
}
