/**
 * 
 */
package line_tracer;

/**
 * @author usamimasanori
 *
 */
public class ControllerOnOff implements Controller {
	private LightSensor light_;
	private DirectionController direction_;

	/**
	 * 
	 * @param light
	 * @param direction
	 */
	public ControllerOnOff(LightSensor light, DirectionController direction) {
		light_ = light;
		direction_ = direction;
	}
	
	/* (non-Javadoc)
	 * @see line_tracer.Controller#setLightSensor(line_tracer.LightSensor)
	 */
	@Override
	public void setLightSensor(LightSensor light) {
		light_ = light;
	}

	/* (non-Javadoc)
	 * @see line_tracer.Controller#setDirectionController(line_tracer.DirectionController)
	 */
	@Override
	public void setDirectionController(DirectionController direction) {
		direction_ = direction;
	}

	/* (non-Javadoc)
	 * @see line_tracer.Controller#execute()
	 */
	@Override
	public boolean execute() {
		float value = light_.getValue();
		if (light_.getThreashold() > value) {
			direction_.setDirection(+100);
		}
		else if (light_.getThreashold() < value) {
			direction_.setDirection(-100);
		}
		else {
			direction_.setDirection(0);
		}
		return true;
	}

}
