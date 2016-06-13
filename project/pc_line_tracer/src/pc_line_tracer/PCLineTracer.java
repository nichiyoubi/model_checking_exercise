/**
 * 
 */
package pc_line_tracer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author usamimasanori
 *
 */
public class PCLineTracer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket socket_  = null;
		final int PORT_ = 12345;
		
		try {
			socket_ = new Socket(args[0], PORT_);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket_.getInputStream()));
			PrintWriter out  = new PrintWriter(socket_.getOutputStream());
			BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Init");
			
			String input;
			CommandCI ci = new CommandCI();
			ci.help();
			while((input = keyin.readLine()).length() > 0) {
				out.println(ci.parse(input));
				out.flush();
				
				String line = in.readLine();
				System.out.println("recv:" + line);
				if (line != null) {
					System.out.println(line);
				}
				else {
					break;
				}
				ci.help();
			}
		}
		catch(UnknownHostException e1) {

		}
		catch(IOException e2) {
			
		}
	}
}
