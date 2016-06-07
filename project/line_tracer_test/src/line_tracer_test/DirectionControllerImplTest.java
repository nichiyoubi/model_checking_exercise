/**
 * 
 */
package line_tracer_test;

import static org.junit.Assert.*;
import org.junit.Test;

import line_tracer.*;

/**
 * @author usamimasanori
 *
 */
public class DirectionControllerImplTest {
	Wheel rightWheel_ = new MockWheel();
	Wheel leftWheel_ = new MockWheel();

	/**
	 * Test method for {@link line_tracer.DirectionControllerImpl#getDirection()}.
	 */
	@Test
	public void testGetDirection() {
		DirectionControllerImpl controller = new DirectionControllerImpl(rightWheel_, leftWheel_);
		controller.setDirection(100);
		assertEquals(100, controller.getDirection());
	}

}
