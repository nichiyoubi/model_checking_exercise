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
public class Server {
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
	
	public boolean echo() {
		return false;
	}
}
