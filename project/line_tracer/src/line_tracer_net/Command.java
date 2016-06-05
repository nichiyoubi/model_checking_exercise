/**
 * 
 */
package line_tracer_net;


/**
 * @author usamimasanori
 *
 */
public class Command {

	public enum CommandType {
		START, STOP, LEFT, RIGHT, ERROR
	}
	
	public CommandType cmd_;
	public String cmdName_;
	
	/*
	 * 
	 */
	public Command() {
		cmd_ = Command.CommandType.ERROR;
		cmdName_ = "ERROR";
	}
	
	/*
	 * 
	 */
	public void setCommand(Command.CommandType cmd) {
		switch(cmd) {
		case START:
			cmd_ = Command.CommandType.START;
			cmdName_ = "START";
			break;
		case STOP:
			cmd_ = Command.CommandType.STOP;
			cmdName_ = "STOP";
			break;
		case LEFT:
			cmd_ = Command.CommandType.LEFT;
			cmdName_ = "LEFT";
			break;
		case RIGHT:
			cmd_ = Command.CommandType.RIGHT;
			cmdName_ = "RIGHT";
			break;
		default:
			cmd_ = Command.CommandType.ERROR;
			cmdName_ = "ERROR";
		}
	}

	static public Command.CommandType decode(String input) {
		switch(input) {
		case "START":
			return Command.CommandType.START;
		case "STOP":
			return Command.CommandType.STOP;
		case "LEFT":
			return Command.CommandType.LEFT;
		case "RIGHT":
			return Command.CommandType.RIGHT;
		default:
			return Command.CommandType.ERROR;
		}
	}
}
