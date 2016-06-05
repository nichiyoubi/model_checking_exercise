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

}
