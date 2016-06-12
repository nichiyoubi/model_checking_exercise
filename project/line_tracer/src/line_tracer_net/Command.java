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
		START, STOP, LEFT, RIGHT, STRAIGHT, ERROR
	}
	public static final String START_ = "START";
	public static final String STOP_ = "STOP";
	public static final String LEFT_ = "LEFT";
	public static final String RIGHT_ = "RIGHT";
	public static final String STRAIGHT_ = "STRAIGHT";
	public static final String ERROR_ = "ERROR";
}
