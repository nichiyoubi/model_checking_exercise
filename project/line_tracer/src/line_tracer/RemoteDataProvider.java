/**
 * 
 */
package line_tracer;

import java.io.*;
import java.net.*;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

/**
 * @author usamimasanori
 *
 */
public class RemoteDataProvider implements Runnable {
	private Socket connSocket_ = null;
	private final int PORT_ = 12345;
	private final String ADDRESS_ = "192.168.11.8";
	private LightSensor leftLight_ = null;
	private LightSensor rightLight_ = null;
	private Wheel rightWheel_ = null;
	private Wheel leftWheel_ = null;
	
	/**
	 * 
	 * @param light
	 * @param right
	 * @param left
	 */
	public RemoteDataProvider(LightSensor light_left, LightSensor light_right, Wheel right, Wheel left) {
		leftLight_ = light_left;
		rightLight_ = light_right;
		rightWheel_ = right;
		leftWheel_ = left;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean connect() {
		try {
			connSocket_ = new Socket();
			connSocket_.connect(new InetSocketAddress(ADDRESS_, PORT_));
			return true;
		}
		catch (IOException e1) {
			try {
				if (connSocket_ != null) {
					connSocket_.close();
				}
				return false;
			}
			catch (IOException e2) {
				return false;
			}
		}

	}
	
	/**
	 * 
	 */
	public void run() {
		int cnt = 0;
		while(!connect()) {
			Delay.msDelay(1000);
			LCD.drawString("CONN:" + (cnt++), 0, 5);
		}
		LCD.drawString("RDATA ", 0, 5);
		
		try {
			PrintWriter out  = new PrintWriter(connSocket_.getOutputStream());
			while(true) {
				float l_light = leftLight_.getValue();
				float r_light = rightLight_.getValue();
//				int right = rightWheel_.getSpeed();
//				int left = leftWheel_.getSpeed();
				
				/// データを整形する
				String output = "{ sensor_l: " + String.valueOf(l_light) + ", " +
								"sensor_r: " + String.valueOf(r_light) + "}";
				///　データを送信する
				out.println(output);
				out.flush();				
				Delay.msDelay(1000);
			}
		}
		catch(IOException e1) {
			try {
				if (connSocket_ != null) {
					connSocket_.close();
				}
			}
			catch(IOException e2) {
			}
		}
	}
}
