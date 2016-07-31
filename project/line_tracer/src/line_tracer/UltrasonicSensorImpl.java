/**
 * 
 */
package line_tracer;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.*;
import lejos.robotics.*;

/**
 * @author usamimasanori
 *
 */
public class UltrasonicSensorImpl extends EV3UltrasonicSensor implements
		UltrasonicSensor {
	private float threashold_ = 0.50F;
	private float THREASHOLD_MAX = 3.00F;
	private float THREASHOLD_MIN = 0;
	SampleProvider provider_;
	float[] sample_;

	public UltrasonicSensorImpl(Port port, float threashold) {
		super(port);
		setThreashold(threashold);
		provider_  = this.getDistanceMode();
		sample_ = new float[provider_.sampleSize()];
	}
	
	/* (non-Javadoc)
	 * @see line_tracer.UltrasonicSensor#getDistance()
	 */
	@Override
	public float getDistance() {
		provider_.fetchSample(sample_, 0);
		return sample_[0];
	}

	/* (non-Javadoc)
	 * @see line_tracer.UltrasonicSensor#doesExist()
	 */
	@Override
	public boolean doesExist() {
		float distance = getDistance();
		return (distance <= threashold_);
	}

	/* (non-Javadoc)
	 * @see line_tracer.UltrasonicSensor#setThreashold(float)
	 */
	@Override
	public void setThreashold(float threashold) {
		if ((threashold <= THREASHOLD_MAX) && (threashold >= THREASHOLD_MIN))
			threashold_ = threashold;
	}

	/* (non-Javadoc)
	 * @see line_tracer.UltrasonicSensor#getThreashold()
	 */
	@Override
	public float getThreashold() {
		return threashold_;
	}

}
