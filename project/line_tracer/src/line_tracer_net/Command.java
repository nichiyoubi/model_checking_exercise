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
	public static final String LEFT_ = "{ direction: 2}";
	public static final String RIGHT_ = "{ direction: 1}";
	public static final String STRAIGHT_ = "{ direction: 0}";
	public static final String ERROR_ = "ERROR";
}
