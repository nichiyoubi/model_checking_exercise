/**
 * 
 */
package line_tracer;

import lejos.hardware.motor.*;
import lejos.hardware.port.*;

/**
 * @author usamimasanori
 *
 */
public class WheelImpl extends EV3LargeRegulatedMotor implements Wheel {

	public WheelImpl(Port port) {
		super(port);
	}
	
	/* (non-Javadoc)
	 * @see line_tracer.Wheel#getMaximumSpeed()
	 */
	@Override
	public int getMaximumSpeed() {
		return (int)getMaxSpeed();
	}

	/* (non-Javadoc)
	 * @see line_tracer.Wheel#getMinimumSpeed()
	 */
	@Override
	public int getMinimumSpeed() {
		return 0;
	}
}
