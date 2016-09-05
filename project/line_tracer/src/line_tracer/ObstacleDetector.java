/**
 * 
 */
package line_tracer;

/**
 * @author usamimasanori
 *
 */
public class ObstacleDetector {
	TouchSensor touch_ = null;
	UltrasonicSensor sonic_ = null;
	
	public ObstacleDetector(UltrasonicSensor sonic, TouchSensor touch) {
		touch_ = touch;
		sonic_ = sonic;
	}
	
	public boolean isExist() {
		return sonic_.doesExist() || touch_.isDown();
	}

}
