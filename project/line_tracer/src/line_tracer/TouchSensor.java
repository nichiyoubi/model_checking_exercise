/**
 * 
 */
package line_tracer;

/**
 * @author usamimasanori
 *
 */
public interface TouchSensor {

	/**
	 * @return boolean センサーボタンの押下状態（押下:true, 解放:false）
	 */
	boolean isDown();
}
