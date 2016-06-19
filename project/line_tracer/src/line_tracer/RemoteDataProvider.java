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
	private final int PORT_ = 12346;
	private final String ADDRESS_ = "192.168.11.3";
	private LightSensor light_ = null;
	private Wheel rightWheel_ = null;
	private Wheel leftWheel_ = null;
	
	/**
	 * 
	 * @param light
	 * @param right
	 * @param left
	 */
	public RemoteDataProvider(LightSensor light, Wheel right, Wheel left) {
		light_ = light;
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
				float light = light_.getValue();
				int right = rightWheel_.getSpeed();
				int left = leftWheel_.getSpeed();
				
				/// データを整形する
				String output = "light, " + String.valueOf(light) + ", " +
								"motor(right), " + String.valueOf(right) + ", " +
								"motor(left), " + String.valueOf(left);
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
