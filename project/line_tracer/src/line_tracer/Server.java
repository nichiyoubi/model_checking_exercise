/**
 * 
 */
package line_tracer;

import java.io.*;
import java.net.*;

/**
 * @author usamimasanori
 *
 */
public class Server implements Runnable {
	ServerSocket serverSocket_ = null;
	Socket connSocket_  = null;
	final int PORT_ = 12345;
	
	/*
	 * 
	 */
	public Server() {
		try {
			serverSocket_ = new ServerSocket(PORT_);
			connSocket_ = serverSocket_.accept();
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
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		echo();
	}
}
