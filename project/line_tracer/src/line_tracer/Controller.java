/**
 * 
 */
package line_tracer;

/**
 * @author usamimasanori
 *
 */
public interface Controller {
	void setLightSensor(LightSensor light);
	void setDirectionController(DirectionController direction);
	boolean execute();
}
