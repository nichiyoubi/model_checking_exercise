/**
 * 
 */
package pc_line_tracer;

import line_tracer_net.Command;

/**
 * @author usamimasanori
 *
 */
public class CommandCI {
	public CommandCI() {
	}
	
	public void help() {
		System.out.println("Command:");
		System.out.println("\t 1:STRAIGHT 2:RIGHT 3:LEFT 4:STOP OTHERS:ERROR");
	}
	
	public String parse(String input) {
		try {
			int no = Integer.parseInt(input);
			switch(no) {
			case 1:
				return Command.STRAIGHT_;
			case 2:
				return Command.RIGHT_;
			case 3:
				return Command.LEFT_;
			case 4:
				return Command.STOP_;
			default:
				return Command.ERROR_;
			}
		}
		catch(NumberFormatException e) {
			return Command.ERROR_;
		}
	}
}
