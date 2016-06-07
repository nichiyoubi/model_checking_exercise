package line_tracer_test;
/**
 * 
 */


import line_tracer.DirectionController;
import line_tracer.Wheel;

/**
 * @author usamimasanori
 *
 */
public class MockDirectionController implements DirectionController {

	/* (non-Javadoc)
	 * @see line_tracer.DirectionController#setWheel(line_tracer.Wheel, line_tracer.Wheel)
	 */
	@Override
	public void setWheel(Wheel right, Wheel left) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see line_tracer.DirectionController#setDirection(int)
	 */
	@Override
	public void setDirection(int direction) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see line_tracer.DirectionController#getDirection()
	 */
	@Override
	public int getDirection() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void stop() {
		
	}

}
