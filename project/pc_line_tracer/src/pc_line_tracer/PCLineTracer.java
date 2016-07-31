/**
 * 
 */
package pc_line_tracer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import line_tracer_net.Command;

/**
 * @author usamimasanori
 *
 */
public class PCLineTracer {

	/**
	 * @param args[0] address of robot
	 */
	public static void main(String[] args) {
		Socket socket_  = null;
		final int PORT_ = 12345;
		LogWriter log = null;  // ログにはロボットのログのセンサー値の他、ロボットの指示も書き出される
		try {
			log = new LogWriter(new File("./data.csv"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot create a log file.");
			System.exit(-1);
		}
		
		// wait for connection from robot
		DataReceiver ds = new DataReceiver(log); // ログファイルをセットする
		startRecv(ds);
		
		// connection to robot
		while (true) {
			try {
				System.out.println("try to connect " + args[0] + ":" + PORT_);
				socket_ = new Socket(args[0], PORT_);
				break;
			} catch (Exception e1) {
				System.out.println(e1);
			    System.out.println("Connect failed, waiting and trying again");
			    try
			    {
				    Thread.sleep(2000);   //2 seconds
			    }
		        catch(Exception ie){
		            ie.printStackTrace();
		        }
			}	
		}
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket_.getInputStream()));
			PrintWriter out  = new PrintWriter(socket_.getOutputStream());
			BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
			
			
			String input;
			CommandCI ci = new CommandCI();
			ci.help();
			while((input = keyin.readLine()).length() > 0) {
				String cmd = ci.parse(input);
				out.println(cmd);
				out.flush();
				System.out.println("action: " + cmd);
				log.write(cmd);
				
				String line = in.readLine(); /* ロボットからのレスポンス */
				System.out.println("recv:" + line);
				if (line != null) {
					System.out.println(line);
				}
				else {
					System.out.println("a response from robot is null!");
///					break;
				}
				ci.help();
			}
		}
		catch(UnknownHostException e1) {

		}
		catch(IOException e2) {
			
		}
	}
	
	/*
	 * 
	 */
	public static void startRecv(DataReceiver ds) {
		Thread thread = new Thread(ds);
		thread.start();
	}
}
