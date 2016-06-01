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
	
	/*
	 * (non-Javadoc)
	 * @see lejos.hardware.motor.BaseRegulatedMotor#forward()
	 */
	@Override
	public synchronized void forward() {
		super.forward();
	}
	
	/*
	 * (non-Javadoc)
	 * @see lejos.hardware.motor.BaseRegulatedMotor#backward()
	 */
	@Override
	public synchronized void backward() {
		super.backward();
	}
	
	/*
	 * (non-Javadoc)
	 * @see lejos.hardware.motor.BaseRegulatedMotor#stop()
	 */
	@Override
	public synchronized void stop() {
		super.stop();
	}
}
