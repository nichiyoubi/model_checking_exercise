/**
 * 
 */
package line_tracer;

import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.port.*;
import lejos.robotics.SampleProvider;

/**
 * @author usamimasanori
 *
 */
public class TouchSensorImpl extends EV3TouchSensor implements TouchSensor {
	SampleProvider provider_;
	float[] sample_;

	public TouchSensorImpl(Port port) {
		super(port);
		provider_ = this.getTouchMode();
		sample_ = new float[provider_.sampleSize()];
	}
	
	/* (non-Javadoc)
	 * @see line_tracer.TouchSensor#isDown()
	 */
	@Override
	public boolean isDown() {
		provider_.fetchSample(sample_, 0);
		return (sample_[0] > 0.0F);
	}

}
