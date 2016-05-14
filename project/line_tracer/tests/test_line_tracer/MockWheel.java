/**
 * 
 */
package test_line_tracer;

import line_tracer.Wheel;
import lejos.hardware.motor.*;

/**
 * @author usamimasanori
 *
 */
public class MockWheel implements Wheel {
	BaseRegulatedMotor motor_;
	int speed_;
	final int MAXIMUM_SPEED = 100;
	final int MINIMUM_SPEED = 0;
	
	@Override
	public void backward() {
		return;
	}
	
	@Override
	public void forward() {
		return;
	}
	
	@Override
	public int getMaximumSpeed() {
		return this.MAXIMUM_SPEED;
	}
	
	@Override
	public int getMinimumSpeed() {
		return this.MINIMUM_SPEED;
	}
	
	@Override
	public void setSpeed(int speed) {
		speed_ = speed;
	}
	
	@Override
	public int getSpeed() {
		return speed_;
	}
	
	@Override
	public void stop() {
		return;
	}
}
