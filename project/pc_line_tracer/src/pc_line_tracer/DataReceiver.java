/**
 * 
 */
package pc_line_tracer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author usamimasanori
 *
 */
public class DataReceiver implements Runnable {
	private ServerSocket serverSocket_ = null;
	private Socket connSocket_ = null;
	private final int PORT_ = 12345;
	private File file_ = null;

	/*
	 * 
	 */
	public DataReceiver() {
		try {
			serverSocket_ = new ServerSocket(PORT_);
			connSocket_ = serverSocket_.accept();
			file_ = new File("./data.csv");
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
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	  	try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connSocket_.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file_)));
			String line;

			System.out.println("DataReceiver Start.");
			while((line = in.readLine()) != null) {
				System.out.println(line);
				out.println(line);
				out.flush();
			}
			
			out.close();
			connSocket_.close();
			serverSocket_.close();
		}
		catch(IOException e1) {
			try {
				if (connSocket_ != null) {
					connSocket_.close();
				}
				if (serverSocket_ != null) {
					serverSocket_.close();
				}
			}
			catch(IOException e2) {
			}
		}
	}
}
