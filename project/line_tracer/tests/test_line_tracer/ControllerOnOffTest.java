/**
 * 
 */
package test_line_tracer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import line_tracer.*;

/**
 * @author usamimasanori
 *
 */
public class ControllerOnOffTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link line_tracer.ControllerOnOff#execute()}.
	 */
	@Test
	public void testExecute() {
		MockWheel rightWheel = new MockWheel();
		MockWheel leftWheel = new MockWheel();
//		MockDirectionController direction = new MockDirectionController();
		DirectionController direction = new DirectionControllerImpl(rightWheel, leftWheel);
		MockLightSensor light = new MockLightSensor();
		light.setThreashold(50);
//		direction.setWheel(rightWheel, leftWheel);
		
		ControllerOnOff controller = new ControllerOnOff(light, direction);
		
		light.setValue(100F);
		assertEquals(100D, light.getValue(), 100);
		assertTrue(controller.execute());
		assertEquals(-45, direction.getDirection());

		
		light.setValue(0F);
		assertEquals(0D, light.getValue(), 100);
		assertTrue(controller.execute());
		assertEquals(45, direction.getDirection());

		
		light.setValue(50F);
		assertEquals(50D, light.getValue(), 100);
		assertTrue(controller.execute());
		assertEquals(0, direction.getDirection());
	}
}
