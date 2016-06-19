/**
 * 
 */
package line_tracer;

import java.io.*;
import java.net.*;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import line_tracer_net.Command;

/**
 * @author usamimasanori
 *
 */
public class RemoteController implements Runnable {
	ServerSocket serverSocket_ = null;
	Socket connSocket_  = null;
	final int PORT_ = 12345;
	DirectionController controller_ = null;
	
	/*
	 * 
	 */
	public RemoteController(DirectionController cnt) {
		controller_ = cnt;
	}
	
	/*
	 * 
	 */
	public boolean connect() {
		try {
			serverSocket_ = new ServerSocket(PORT_);
			connSocket_ = serverSocket_.accept();
			return true;
		}
		catch (IOException e1) {
			try {
				if (connSocket_ != null) {
					connSocket_.close();
				}
				if (serverSocket_ == null) {
					serverSocket_.close();
				}
				return false;
			}
			catch (IOException e2) {
				return false;
			}
		}	
	}
	
	/*
	 * 
	 */
/*
	public boolean echo() {
	  	try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connSocket_.getInputStream()));
			PrintWriter out  = new PrintWriter(connSocket_.getOutputStream(), true);
			String line;

			while((line = in.readLine()) != null) {
				out.println(line);
				out.flush();
			}
			return true;
		}
		catch(IOException e1) {
			try {
				if (connSocket_ != null) {
					connSocket_.close();
				}
				if (serverSocket_ != null) {
					serverSocket_.close();
				}
				return false;
			}
			catch(IOException e2) {
				return false;
			}
		}
	}
*/
	
	private boolean recv() {
		int cnt = 0;
		while(!connect()) {
			Delay.msDelay(1000);
			LCD.drawString("CONN2:" + (cnt++), 0, 6);
		}
		LCD.drawString("RCONT", 0, 6);

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connSocket_.getInputStream()));
			PrintWriter out  = new PrintWriter(connSocket_.getOutputStream(), true);
			String line;

			while((line = in.readLine()) != null) {
				out.println(line);
				out.flush();
				execute(line);
			}
			return true;
		}
		catch(IOException e1) {
			try {
				if (connSocket_ != null) {
					connSocket_.close();
				}
				if (serverSocket_ != null) {
					serverSocket_.close();
				}
				return false;
			}
			catch(IOException e2) {
				return false;
			}
		}
	}
	
	/*
	 * 
	 */
	private void execute(String cmd) {
		switch(cmd) {
		case Command.START_:
			LCD.drawString("START   ", 0, 4);
			controller_.setDirection(0);
			break;
		case Command.STOP_:
			LCD.drawString("STOP    ", 0, 4);
			controller_.stop();
			break;
		case Command.LEFT_:
			LCD.drawString("LEFT    ", 0, 4);
			controller_.setDirection(100);
			break;
		case Command.RIGHT_:
			LCD.drawString("RIGHT   ", 0, 4);
			controller_.setDirection(-100);
			break;
		case Command.STRAIGHT_:
			LCD.drawString("STRAIGHT", 0, 4);
			controller_.setDirection(0);
			break;
		default:
			LCD.drawString("ERROR", 0, 4);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		recv();
	}
}
