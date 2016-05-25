/**
 * 
 */
package pc_line_tracer;

import java.net.*;
import java.io.*;

/**
 * @author usamimasanori
 *
 */
public class PCLineTracer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket_  = null;
		final int PORT_ = 12345;
		
		try {
			socket_ = new Socket(args[0], PORT_);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket_.getInputStream()));
			PrintWriter out  = new PrintWriter(socket_.getOutputStream());
			BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
			
			String input;
			while((input = keyin.readLine()).length() > 0) {
				out.println(input);
				String line = in.readLine();
				if (line != null) {
					System.out.println(line);
				}
				else {
					break;
				}
			}
		}
		catch(UnknownHostException e1) {

		}
		catch(IOException e2) {
			
		}
	}

}
