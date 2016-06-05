/**
 * 
 */
package line_tracer;

import java.io.*;
import java.net.*;

import lejos.hardware.lcd.LCD;
import line_tracer_net.Command;

/**
 * @author usamimasanori
 *
 */
public class Server implements Runnable {
	ServerSocket serverSocket_ = null;
	Socket connSocket_  = null;
	final int PORT_ = 12345;
	DirectionController controller_ = null;
	
	/*
	 * 
	 */
	public Server(DirectionController cnt) {
		try {
			serverSocket_ = new ServerSocket(PORT_);
			connSocket_ = serverSocket_.accept();
			controller_ = cnt;
		}
		catch (IOException e1) {
			try {
				if (connSocket_ != null) {
					connSocket_.close();
				}
				if (serverSocket_ == null) {
					serverSocket_.close();
				}
			}
			catch (IOException e2) {
				return;
			}
		}
	}
	
	/*
	 * 
	 */
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
	
	private boolean recv() {
	  	try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connSocket_.getInputStream()));
			PrintWriter out  = new PrintWriter(connSocket_.getOutputStream(), true);
			String line;

			while((line = in.readLine()) != null) {
				out.println(line);
				out.flush();
				if (!execute(Command.decode(line))) {
					return false;
				}
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
	private boolean execute(Command.CommandType cmd) {
		switch(cmd) {
		case START:
			LCD.drawString("START", 0, 5);
			break;
		case STOP:
			LCD.drawString("STOP", 0, 5);
			break;
		case LEFT:
			LCD.drawString("LEFT", 0, 5);
			controller_.setDirection(100);
			break;
		case RIGHT:
			LCD.drawString("RIGHT", 0, 5);
			controller_.setDirection(-100);
			break;
		default:
			LCD.drawString("ERROR", 0, 5);
			return false;
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
//		echo();
		recv();
	}
}
